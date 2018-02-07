package com.example.cubesoft.skillzcard

import android.app.Application

import com.example.cubesoft.skillzcard.api.ExampleWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by cubesoft on 06.02.18.
 */
class SkillzCardApplication : Application() {

    companion object {
        fun create(): ExampleWebService {

            val interceptor = HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build();

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("http://example.com/public/")
                    .client(client)
                    .build()

            return retrofit.create(ExampleWebService::class.java)
        }
    }

    val webService by lazy {
        create()
    }

    @Override
    public override fun onCreate() {
        super.onCreate()

        beginLogin("aaa", "bb");

    }

    private fun beginLogin(email: String, password: String) {
        var disposable = webService.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> showResult(result.query.searchinfo.totalhits) },
                        { error -> showError(error.message) }
                )
    }

    private fun showResult(totalhits: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showError(message: String?) {}
}