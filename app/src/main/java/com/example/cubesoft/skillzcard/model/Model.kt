package com.example.cubesoft.skillzcard.model

/**
 * Created by cubesoft on 06.02.18.
 */
object Model {
    data class LoginResult(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)

    data class PopupResult(val query: Query)
    data class LoginRequest(val username: String, val password: String)

}