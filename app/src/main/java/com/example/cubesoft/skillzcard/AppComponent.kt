package com.example.cubesoft.skillzcard

import android.app.Application
import com.example.cubesoft.skillzcard.activity.LoginActivity
import com.example.cubesoft.skillzcard.activity.PopupActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by cube on 07.02.18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: Application)
    fun inject(activity: LoginActivity)
    fun inject(popupActivity: PopupActivity)
}