package com.example.cubesoft.skillzcard

import android.app.Application

/**
 * Created by cubesoft on 06.02.18.
 */
class SkillzCardApplication : Application(){

    private var appComponent: AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build();

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this);

    }


    public fun getAppComponent() : AppComponent {
        return appComponent;
    }




}