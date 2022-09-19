package com.example.cryptoconverter.di

import android.app.Application
import com.example.cryptoconverter.presentation.CoinConvertActivity
import com.example.cryptoconverter.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(activity: CoinConvertActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}