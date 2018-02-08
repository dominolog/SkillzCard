package com.example.cubesoft.skillzcard.activity

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by cube on 08.02.18.
 */
open class BaseActivity : AppCompatActivity() {
    var disposables: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        disposables.dispose();
        super.onDestroy();
    }

    protected fun subscribe(s: Disposable) {
        disposables.add(s)
    }
}