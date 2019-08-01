package com.rav.test.ceep.data.rest.api

import com.rav.test.ceep.data.model.Note
import retrofit2.Call
import retrofit2.http.GET

interface NoteApi {

    @GET("notes")
    fun listAll(): Call<ArrayList<Note>>
}