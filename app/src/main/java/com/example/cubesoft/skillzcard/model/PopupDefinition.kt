package com.example.cubesoft.skillzcard.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cube on 08.02.18.
 */

data class PopupDefinition(@SerializedName("fields") val fields: Map<String, Field>,
                           @SerializedName("units") var units: List<Unit>) {

    class Field(@SerializedName("@unit")
                val unit: Int) {
    }

    class Unit(@SerializedName("@id") val id: Int,
               @SerializedName("@description") val description: String,
               @SerializedName("unit-definition") val unitDefinition: List<UnitDefinition>) {
    }


    class UnitDefinition(@SerializedName("@name") val name: String,
                         @SerializedName("@std") val std: String,
                         @SerializedName("unit-conversion") val unitConversion: List<UnitConversion>) {
    }

    class UnitConversion(@SerializedName("@convToCM") val convToCM: String,
               @SerializedName("@convFromCM") val convFromCM: String) {
    }



}