package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cryptoapp.adapter.NewsAdapter
import com.example.cryptoapp.api.ApiFactory
import com.example.cryptoapp.databinding.ActivityNewsCoinBinding
import com.example.cryptoapp.pojo.news.NewsResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsCoinActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityNewsCoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter(this)
        binding.recyclerViewNews.adapter = newsAdapter

        val disposable = ApiFactory.apiService.getNewsCoin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data?: throw RuntimeException("Empty List") }
            .subscribe({
//                Log.d("NewsCoinActivity", "Success: $it")
                newsAdapter.submitList(it)
            }, {
                Log.d("NewsCoinActivity", "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, NewsCoinActivity::class.java)
        }
    }
}