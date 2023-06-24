package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.presentation.view.CoinDetailFragment
import com.example.cryptoapp.presentation.view.CoinPriceListActivity
import com.example.cryptoapp.presentation.view.NewsCoinActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(activity: NewsCoinActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
