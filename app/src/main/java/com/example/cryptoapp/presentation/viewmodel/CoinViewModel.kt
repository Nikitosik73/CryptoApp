package com.example.cryptoapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.network.model.coininfo.CoinInfoDto
import com.example.cryptoapp.data.network.model.coininfo.CoinInfoJsonContainerDto
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = database.coinPriceInfoDao().getPriceList()

    fun getDetailInfo(fSym: String): LiveData<CoinInfoDto> {
        return database.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    /*
        Создаём блок инициализации для того,
        чтобы в момент создания viewmodel
        данные подгружались автоматически
        и не нужно было вызывать метод loadData()
        в ручную
     */
    init {
        loadData()
    }

    private fun loadData() {
        // заргужаем данные популярных валют
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
            // превращаем данные о валютах одну строку
            .map { it.names?.map { it.coinName?.name }?.joinToString(",")?: "" }
            // выводим полную ифнформация о популярных валютах запустив метод getFullPriceList
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            // парсим наш gson и конвертируем его в CoinPriceInfo
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("Test_of_load_data", "Success: $it")
                database.coinPriceInfoDao().insertPriceList(it)
            }, {
                Log.d("Test_of_load_data", "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        jsonContainerDto: CoinInfoJsonContainerDto
    ): List<CoinInfoDto> {
        val result = ArrayList<CoinInfoDto>()
        val jsonObject = jsonContainerDto.coinPriceJsonObject?: return result
        // выбираем все ключи в нашем gson
        val coinKeySet = jsonObject.keySet()
        // проходимся по всем ключам
        for (coinKey in coinKeySet) {
            // получаем вложенный gson
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            // выбираем все ключи у вложенного gson
            val currencyKeySet = currencyJson.keySet()
            // проходимся по ним
            for (currencyKey in currencyKeySet) {
                // Конвертируем в CoinPriceInfo
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

//    class Factory(val application: Application) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//            if (modelClass.isAssignableFrom(CoinViewModel::class.java)) {
//                return CoinViewModel(application) as T
//            }
//            throw IllegalArgumentException("Unable to construct viewmodel")
//        }
//    }
}