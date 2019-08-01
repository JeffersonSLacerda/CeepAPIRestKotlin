package com.rav.test.ceep.data.rest.service.rest

import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.data.rest.api.NoteApi
import com.rav.test.ceep.data.rest.service.BaseService

class NoteService: BaseService() {

    val service = createService(NoteApi::class.java)

    fun list(listener: FetchDataListener<ArrayList<Note>>){
        val call = service.list()
        fetchData(call, object: FetchDataListener<BaseArrayResponse<Note>>{
            override fun onFetchDataSuccess(dataObject: BaseArrayResponse<Note>) {
                listener.onFetchDataSuccess(dataObject.response!!)
            }

            override fun onFetchDataFail(message: String) {
                listener.onFetchDataFail(message)
            }

        })
    }
}