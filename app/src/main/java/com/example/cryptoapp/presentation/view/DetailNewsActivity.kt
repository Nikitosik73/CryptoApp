package com.example.cryptoapp.presentation.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityDetailNewsBinding
import com.example.cryptoapp.data.model.news.Data
import com.squareup.picasso.Picasso

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(NEWS)) {
            finish()
            return
        }

        val news = (intent.getSerializableExtra(NEWS)) as Data
        Log.d("test_id", "News: $news")

        with(binding) {
            with(news) {
                val templateString = resources.getString(R.string.title_news)
                val templateBodyString = resources.getString(R.string.body_news)
                Picasso.get().load(imageUrl).into(imageViewNews)
                textViewNameNews.text = sourceInfo?.name
                textViewTitle.text = String.format(templateString, title)
                textViewBody.text = String.format(templateBodyString, body)
                buttonNewsPage.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(guid)
                    }
                    startActivity(intent)
                }
            }

        }
    }

    companion object {

        private const val NEWS = "news"

        fun newIntent(context: Context, news: Data): Intent {
            return Intent(context, DetailNewsActivity::class.java).apply {
                putExtra(NEWS, news)
            }
        }
    }
}