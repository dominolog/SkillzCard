package com.example.cubesoft.skillzcard.api

import com.example.cubesoft.skillzcard.model.Model
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by cubesoft on 06.02.18.
 */
interface ExampleWebService {
    @POST("login")
    public fun login(@Body request: Model.LoginRequest):
            Observable<Model.LoginResult>


    @POST("popup")
    public fun popup(@Body values: Map<String, String>):
            Observable<Model.PopupResult>

}