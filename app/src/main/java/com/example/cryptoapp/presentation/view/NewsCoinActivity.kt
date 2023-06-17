package com.example.cryptoapp.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.presentation.adapter.NewsAdapter
import com.example.cryptoapp.databinding.ActivityNewsCoinBinding
import com.example.cryptoapp.presentation.viewmodel.NewsViewModel

class NewsCoinActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityNewsCoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

//        viewModel.newsList.observe(this) {
//            newsAdapter.submitList(it)
//        }

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