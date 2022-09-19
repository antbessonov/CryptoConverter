package com.example.cryptoconverter.app

import android.app.Application
import com.example.cryptoconverter.di.DaggerApplicationComponent

class CoinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}