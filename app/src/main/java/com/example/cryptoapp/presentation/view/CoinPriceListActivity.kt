package com.example.cryptoapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.presentation.adapter.CoinPriceAdapter
import com.example.cryptoapp.presentation.app.CoinApp
import com.example.cryptoapp.presentation.viewmodel.CoinViewModel
import com.example.cryptoapp.presentation.viewmodelfactory.ViewModelFactory
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinPriceListBinding
    private lateinit var priceAdapter: CoinPriceAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CoinViewModel by viewModels { viewModelFactory }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()

        priceAdapter.onClickCoinPriceInfo = {
            if (isOnePaneToMode()) {
                launchScreenActivity(it.fromSymbol)
            } else {
                launchScreenFragment(it.fromSymbol)
            }
        }

        viewModel.coinInfoList.observe(this) {
            Log.d("Test_of_load_data", it.toString())
            priceAdapter.submitList(it)
        }

        binding.buttonTest.setOnClickListener {
            val intent = NewsCoinActivity.newIntent(this@CoinPriceListActivity)
            startActivity(intent)
        }
    }

    private fun launchScreenFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun launchScreenActivity(fromSymbol: String) {
        CoinDetailActivity.newIntent(this, fromSymbol).apply {
            startActivity(this)
        }
    }

    private fun isOnePaneToMode() = binding.fragmentContainer == null

    private fun initAdapter() {
        priceAdapter = CoinPriceAdapter(this)
        binding.recyclerViewCoinInfo.adapter = priceAdapter
    }
}