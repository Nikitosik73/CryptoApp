package com.example.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.workers.RefreshDataWorker
import com.example.cryptoapp.domain.entity.coin.CoinInfo
import com.example.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: CoinMapper,
    private val coinDao: CoinInfoDao
) : CoinRepository {

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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}