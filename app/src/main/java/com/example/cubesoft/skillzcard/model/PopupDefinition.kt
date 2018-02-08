package com.example.cubesoft.skillzcard.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cube on 08.02.18.
 */

class PopupDefinition {

    class Field {
        @SerializedName("@unit")
        var unit: Int;
    }

    class Unit {
        @SerializedName("@id")
        var id: Int;

        @SerializedName("@description")
        var description: String;

        @SerializedName("@id")
        var id: Int;
    }

    @SerializedName("fields")
    lateinit var fields: List<Field>;

    @SerializedName("units")
    lateinit var units: List<Unit>;


}