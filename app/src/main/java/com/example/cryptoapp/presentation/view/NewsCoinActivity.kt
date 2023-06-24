package com.example.cryptoapp.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.presentation.adapter.NewsAdapter
import com.example.cryptoapp.databinding.ActivityNewsCoinBinding
import com.example.cryptoapp.presentation.app.CoinApp
import com.example.cryptoapp.presentation.viewmodel.CoinViewModel
import com.example.cryptoapp.presentation.viewmodel.NewsViewModel
import com.example.cryptoapp.presentation.viewmodelfactory.ViewModelFactory
import javax.inject.Inject

class NewsCoinActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityNewsCoinBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityNewsCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()

        viewModel.newsList.observe(this) {
            newsAdapter.submitList(it)
        }

        newsAdapter.onCLickNewsListener = {
            val intent = DetailNewsActivity.newIntent(
                this@NewsCoinActivity,
                it
            )
            startActivity(intent)
        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter(this)
        binding.recyclerViewNews.adapter = newsAdapter
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, NewsCoinActivity::class.java)
        }
    }
}