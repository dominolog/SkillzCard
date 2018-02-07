package com.example.cubesoft.skillzcard

import android.app.Application
import com.example.cubesoft.skillzcard.api.ExampleWebService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by cube on 07.02.18.
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication() = app

    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient? {
        val interceptor = HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }


    @Provides
    @Singleton
    fun providesWebService(): ExampleWebService? {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl(BuildConfig.WEB_SERVICE_URL)
                .build()
        return return retrofit.create(ExampleWebService::class.java);
    }

}