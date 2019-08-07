package com.rav.test.ceep.ui.note.dialog

import com.rav.test.ceep.ui.base.BaseNavigator

interface NoteListDialogNavigator : BaseNavigator{

    fun blank() : Boolean

    fun success(message: String)

    fun sendNote()

    fun dismissDialog()

    fun handlerError(message: String)
}
