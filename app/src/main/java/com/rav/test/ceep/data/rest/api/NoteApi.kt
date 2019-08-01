package com.rav.test.ceep.data.rest.api

import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.data.rest.service.BaseService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NoteApi {

//    @GET("/notes")
//    fun listNotes(): Call<BaseService.BaseArrayResponse<Note>>

    @GET("notes")
    fun list(): Call<List<Note>>
}