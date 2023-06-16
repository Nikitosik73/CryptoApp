package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.database.model.CoinInfoDbModel
import com.example.cryptoapp.data.network.model.coininfo.CoinInfoDto
import com.example.cryptoapp.data.network.model.coininfo.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.network.model.coinname.CoinNamesListDto
import com.example.cryptoapp.domain.entity.CoinInfo
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        highDay = dto.highDay,
        lowDay = dto.lowDay,
        lastUpdate = dto.lastUpdate,
        lastMarket = dto.lastMarket,
        imageUrl = dto.imageUrl
    )

    fun mapJsonContainerToDto(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val listDto = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return listDto
        val coinKeySet = jsonObject.keySet()
        for(coinKey in coinKeySet) {
            val currencyJsonObject = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJsonObject.keySet()
            for (currencyKey in currencyKeySet) {
                val coinInfo = Gson().fromJson(
                    currencyJsonObject.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                listDto.add(coinInfo)
            }
        }
        return listDto
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map { nameDto ->
            nameDto.coinName?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        lastUpdate = dbModel.lastUpdate,
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl
    )
}