package com.example.cubesoft.skillzcard.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.cubesoft.skillzcard.R
import com.example.cubesoft.skillzcard.SkillzCardApplication
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_popup.*

class PopupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)
        (application as SkillzCardApplication).getAppComponent().inject(this);


        val gson = Gson();

        gson.fromJson<>()


        save.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }


    companion object {
        fun createIntent(context: Context) : Intent {
            return Intent(context, PopupActivity::class.java);
        }
    }

}
