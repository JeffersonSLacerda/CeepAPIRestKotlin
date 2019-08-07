package com.rav.test.ceep.ui.note.dialog

import android.widget.Toast
import com.rav.test.ceep.BR.note
import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.data.rest.service.BaseService
import com.rav.test.ceep.data.rest.service.NoteService
import com.rav.test.ceep.ui.base.BaseViewModel
import com.rav.test.ceep.ui.note.NoteListActivity
import com.rav.test.ceep.ui.note.NoteListViewModel

class NoteListDialogViewModel : BaseViewModel() {

    private lateinit var navigator: NoteListDialogNavigator

    fun init(){
        navigator = getNavigator() as NoteListDialogNavigator
    }

    fun sendNoteClick(){
        navigator.sendNote()
    }

    fun sendData(note: Note){

        val service = NoteService()
        service.insert(note, object : BaseService.FetchDataListener<Note> {

            override fun onFetchDataSuccess(dataObject: Note) {

                dataObject.title = note.title
                dataObject.description = note.description

                navigator.success("Nota Adicionada!")
            }

            override fun onFetchDataFail(message: String) {
                navigator.handlerError(message)
            }

        })

    }

    fun closeClick(){
        navigator.dismissDialog()
    }
}