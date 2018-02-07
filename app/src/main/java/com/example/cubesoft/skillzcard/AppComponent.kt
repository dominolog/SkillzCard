package com.example.cubesoft.skillzcard

import android.app.Application
import dagger.Component
import javax.inject.Singleton

/**
 * Created by cube on 07.02.18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: Application)
}