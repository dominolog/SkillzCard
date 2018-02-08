package com.example.cubesoft.skillzcard.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.example.cubesoft.skillzcard.R
import com.example.cubesoft.skillzcard.SkillzCardApplication
import com.example.cubesoft.skillzcard.api.ExampleWebService
import com.example.cubesoft.skillzcard.model.Model
import com.example.cubesoft.skillzcard.model.PopupDefinition
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_popup.*
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import javax.inject.Inject

class PopupActivity : BaseActivity(), OnItemSelectedListener {

    @Inject
    lateinit var webService: ExampleWebService

    private var popDef: PopupDefinition? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)
        (application as SkillzCardApplication).getAppComponent().inject(this);

        val gson = Gson();
        popDef = gson.fromJson<com.example.cubesoft.skillzcard.model.PopupDefinition>(readIos(resources.assets.open("testPopup.json")),
                PopupDefinition::class.java)
        setupFields("EU");

        save.setOnClickListener { view ->
            onSave();
        }

        unit.setOnItemSelectedListener  (this);

    }

    private fun onSave() {

        doSave();

    }

    private fun doSave(values: List<String> ) {
        subscribe(webService.popup(values)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({ showProgress(true) })
                .doOnDispose({ showProgress(false) })
                .subscribe(Consumer<Model.PopupResult>{

                }, Consumer {

                }, Action {

                }));

    }

    private fun showProgress(show: Boolean) {}

    override fun onItemSelected(view: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item
        var aUnit = view.adapter.getItem(position);
        setupFields(aUnit as String)

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    private fun setupFields(theUnit: String) {


        inputs.removeAllViews();
        for (field in popDef!!.fields) {

            val child = LayoutInflater.from(this).inflate(R.layout.layout_input_field, inputs, false);
            val input: TextInputEditText = child.findViewById<TextInputEditText>(R.id.input);


            var hint = field.key;

            val unit = findUnit(field.value.unit, popDef!!.units);
            if ( unit != null ) {
                val unitDefinition = findUnitDefinition(theUnit, unit.unitDefinition);
                if (unitDefinition != null) {
                    hint += " (" + unitDefinition.std + ")";
                }
            }

            input.setHint(hint);


            inputs.addView(child);
        }
    }

    private fun findUnitDefinition(name: String, unitDefinitions: List<PopupDefinition.UnitDefinition>): PopupDefinition.UnitDefinition? {
        for (unitDefinition in unitDefinitions) {
            if (unitDefinition.name == name) {
                return unitDefinition;
            }
        }
        return null;
    }

    private fun findUnit(id: Int, units: List<PopupDefinition.Unit>): PopupDefinition.Unit? {
        for (unit in units) {
            if (unit.id == id) {
                return unit;
            }
        }
        return null;
    }


    fun readIos(input: InputStream): String {
        val buffer = CharArray(1024)
        val out = StringBuilder()
        try {
            InputStreamReader(input, "UTF-8").use({ input ->
                while (true) {
                    val rsz = input.read(buffer, 0, buffer.size)
                    if (rsz < 0)
                        break
                    out.append(buffer, 0, rsz)
                }
            })
        } catch (ex: UnsupportedEncodingException) {
            /* ... */
        } catch (ex: IOException) {
            /* ... */
        }

        return out.toString()
    }

    companion object {
        fun createIntent(context: Context) : Intent {
            return Intent(context, PopupActivity::class.java);
        }
    }

}
