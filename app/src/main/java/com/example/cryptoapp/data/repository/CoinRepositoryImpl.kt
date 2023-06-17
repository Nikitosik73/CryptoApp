package com.example.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.domain.entity.CoinInfo
import com.example.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinDao = AppDatabase.getInstance(application).coinInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        val coinNames = coinDao.getCoinNamesList()
        return coinNames.switchMap { listDbModels ->
            MutableLiveData<List<CoinInfo>>().apply {
                value = listDbModels.map { dbModel ->
                    mapper.mapDbModelToEntity(dbModel)
                }
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        val coinInfo = coinDao.getInfoAboutCoin(fromSymbol)
        return coinInfo.switchMap { dbModel ->
            MutableLiveData<CoinInfo>().apply {
                value = mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {// получаем список популярных валют
                val topCoinsNames = apiService.getTopCoinsInfo(limit = 50)
                // преобразуем их в строку
                val fromSymbols = mapper.mapNamesListToString(topCoinsNames)
                // получим информацию об этих валютах
                val jsonContainer = apiService.getFullPriceList(fSyms = fromSymbols)
                // преобразуем json в dto
                val listDto = mapper.mapJsonContainerToDto(jsonContainer)
                // преобразуем dto в dbModel
                val listDbModel = listDto.map { dto ->
                    mapper.mapDtoToDbModel(dto)
                }
                coinDao.insertPriceList(listDbModel)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}