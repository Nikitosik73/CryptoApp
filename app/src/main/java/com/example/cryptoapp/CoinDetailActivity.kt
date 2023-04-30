package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.cryptoapp.viewmodel.CoinViewModel
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinDetailBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol?: "").observe(this, Observer {
            Log.d("Detail_Info", it.toString())
            with(binding) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowDay
                tvMaxPrice.text = it.highDay
                tvLastMarket.text = it.lastMarket
                tvUpdate.text = it.getFormattedTime()
                tvCoin.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.getFullImageURL()).into(ivCoinLogo)
            }
        })
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}