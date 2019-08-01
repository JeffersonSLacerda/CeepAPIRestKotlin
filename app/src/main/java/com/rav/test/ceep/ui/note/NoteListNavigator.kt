package com.rav.test.ceep.ui.note

import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.ui.base.BaseNavigator

interface NoteListNavigator : BaseNavigator {

    fun notes(notes: ArrayList<Note>)
    fun handlerError( message: String)
}