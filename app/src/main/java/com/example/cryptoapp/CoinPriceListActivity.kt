package com.example.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.adapter.CoinInfoAdapter
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.pojo.priceinfo.CoinPriceInfo
import com.example.cryptoapp.viewmodel.CoinViewModel

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinCLickListener = object : CoinInfoAdapter.OnCoinCLickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("Test_onClick", coinPriceInfo.fromSymbol)
            }

        }
        binding.recyclerViewCoinInfo.adapter = adapter
        viewModel = ViewModelProvider(
            this,
        )[CoinViewModel::class.java]

        viewModel.priceList.observe(this, Observer {
            Log.d("Test_of_load_data", it.toString())
            adapter.coinInfoList = it
        })
    }
}