package com.rav.test.ceep.data.rest.service

import android.content.Context
import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.data.rest.api.NoteApi

@Suppress("SENSELESS_COMPARISON")
class NoteService(var context: Context): BaseService() {

    private var service: NoteApi

    init {
        service = createService(NoteApi::class.java)
    }

    fun listAll(listener: FetchDataListener<ArrayList<Note>>){

        val call = service.listAll()

        fetchData( call, object : FetchDataListener<ArrayList<Note>> {

            override fun onFetchDataSuccess(dataObject: ArrayList<Note>) {
                if ( dataObject != null)
                    listener.onFetchDataSuccess(dataObject)
                else
                    listener.onFetchDataFail(BaseService().NO_DATA_RESULT)
            }

            override fun onFetchDataFail(message: String) {
                listener.onFetchDataFail(BaseService().NO_DATA_RESULT)
            }
        })
    }
}