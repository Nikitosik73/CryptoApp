package com.example.cryptoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

//    private val compositeDisposable = CompositeDisposable()
//
//    private var _newsList = MutableLiveData<List<Data>>()
//    val newsList: LiveData<List<Data>>
//        get() = _newsList
//
//    init {
//        loadNews()
//    }
//
//    private fun loadNews() {
//        val disposable = ApiFactory.apiService.getNewsCoin()
//            .map { it.data?: throw RuntimeException("Empty List") }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("NewsViewModel", "Success: $it")
//                _newsList.value = it
//            }, {
//                Log.d("NewsViewModel", "Failure: ${it.message}")
//            })
//        compositeDisposable.add(disposable)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.dispose()
//    }
}