package com.rav.test.ceep.ui.note

import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.data.rest.service.BaseService
import com.rav.test.ceep.data.rest.service.NoteService
import com.rav.test.ceep.ui.base.BaseViewModel

class NoteListViewModel: BaseViewModel() {

    private lateinit var navigator: NoteListNavigator

    fun init(){
        navigator = getNavigator() as NoteListNavigator

        fetchNotes()
    }

    private fun fetchNotes() {

        val service = NoteService(getContext())

        service.listAll(object : BaseService.FetchDataListener<ArrayList<Note>>{
            override fun onFetchDataSuccess(dataObject: ArrayList<Note>) {

                navigator.notes(dataObject)
            }

            override fun onFetchDataFail(message: String) {
                navigator.handlerError(message)
            }
        })
    }
}