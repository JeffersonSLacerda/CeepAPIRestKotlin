package com.rav.test.ceep.data.rest.api

import com.rav.test.ceep.data.model.Note
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteApi {

    @GET("notes")
    fun listAll(): Call<ArrayList<Note>>

    @POST("notes")
    fun inset(@Body note: Note): Call<Note>
}