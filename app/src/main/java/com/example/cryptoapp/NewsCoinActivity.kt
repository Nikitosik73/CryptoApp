package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.adapter.NewsAdapter
import com.example.cryptoapp.api.ApiFactory
import com.example.cryptoapp.databinding.ActivityNewsCoinBinding
import com.example.cryptoapp.pojo.news.NewsResponse
import com.example.cryptoapp.viewmodel.NewsViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsCoinActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityNewsCoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter(this)
        binding.recyclerViewNews.adapter = newsAdapter
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]


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

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, NewsCoinActivity::class.java)
        }
    }
}