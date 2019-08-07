package com.rav.test.ceep.data.rest.service

import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.data.rest.api.NoteApi

@Suppress("SENSELESS_COMPARISON")
class NoteService : BaseService() {

    private var service: NoteApi = createService(NoteApi::class.java)

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

    fun insert(note: Note, listener: FetchDataListener<Note>){

        val call = service.inset(note)

        fetchData(call, object : FetchDataListener<Note>{
            override fun onFetchDataSuccess(dataObject: Note) {
                listener.onFetchDataSuccess(dataObject)
            }

            override fun onFetchDataFail(message: String) {
                listener.onFetchDataFail(message)
            }

        })
    }
}