package com.example.cubesoft.skillzcard.api

import com.example.cubesoft.skillzcard.model.Model
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by cubesoft on 06.02.18.
 */
interface ExampleWebService {
    @POST("login")
    public fun login(@Query("email") email: String,
                      @Query("password") password: String):
            Observable<Model.LoginResult>


    @POST("popup")
    public fun popup(@Query("values") email: List<String>):
            Observable<Model.PopupResult>

}