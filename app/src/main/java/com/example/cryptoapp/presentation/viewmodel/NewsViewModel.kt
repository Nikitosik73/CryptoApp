package com.example.cryptoapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.model.news.Data
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _newsList = MutableLiveData<List<Data>>()
    val newsList: LiveData<List<Data>>
        get() = _newsList

    init {
        loadNews()
    }

    private fun loadNews() {
        val disposable = ApiFactory.apiService.getNewsCoin()
            .map { it.data?: throw RuntimeException("Empty List") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("NewsViewModel", "Success: $it")
                _newsList.value = it
            }, {
                Log.d("NewsViewModel", "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}