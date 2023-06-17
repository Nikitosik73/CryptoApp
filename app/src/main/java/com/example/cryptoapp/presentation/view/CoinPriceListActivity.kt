package com.example.cryptoapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.presentation.adapter.CoinPriceAdapter
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.presentation.viewmodel.CoinViewModel

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel
    private lateinit var priceAdapter: CoinPriceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()

        priceAdapter.onClickCoinPriceInfo = {
            val intent = CoinDetailActivity.newIntent(
                this@CoinPriceListActivity,
                it.fromSymbol
            )
            startActivity(intent)
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.coinInfoList.observe(this) {
            Log.d("Test_of_load_data", it.toString())
            priceAdapter.submitList(it)
        }

        binding.buttonTest.setOnClickListener {
            val intent = NewsCoinActivity.newIntent(this@CoinPriceListActivity)
            startActivity(intent)
        }
    }

    private fun initAdapter() {
        priceAdapter = CoinPriceAdapter(this)
        binding.recyclerViewCoinInfo.adapter = priceAdapter
    }
}