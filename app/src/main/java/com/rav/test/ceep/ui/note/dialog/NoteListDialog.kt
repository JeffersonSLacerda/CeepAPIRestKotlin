package com.rav.test.ceep.ui.note.dialog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.rav.test.ceep.R
import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.databinding.DialogNoteListBinding
import com.rav.test.ceep.ui.base.BaseDialog
import com.rav.test.ceep.ui.note.NoteListActivity
import com.rav.test.ceep.ui.note.NoteListViewModel
import kotlinx.android.synthetic.main.dialog_note_list.*

class NoteListDialog(var listener: NoteListDialogListener) : BaseDialog<DialogNoteListBinding, NoteListDialogViewModel>(), NoteListDialogNavigator {

    private var viewModel: NoteListDialogViewModel? = null

    private var note: Note? = null

    override fun getBindingVarible(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_note_list
    }

    override fun getViewModel(): NoteListDialogViewModel {
        viewModel = NoteListDialogViewModel()
        return viewModel!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("NoteDialog", "init")
        viewModel!!setNavigator(this)
        viewModel!!.init()

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        binding().setVariable(BR.note, note)
        binding().executePendingBindings()

    }

    override fun blank(): Boolean {
        if (form_note_title.text.isNullOrEmpty()) {
            Toast.makeText(context!!, "Campo de Titulo Obrigatório!", Toast.LENGTH_LONG).show()

            return true
        }

        if (form_note_description.text.isNullOrEmpty()) {
            Toast.makeText(context!!, "Campo de Descrição Obrigatório!",Toast.LENGTH_LONG).show()

            return true
        }
        return false
    }

    override fun sendNote() {

        if (!blank()) {

            val note = Note(form_note_title.text.toString(), form_note_description.text.toString())

            viewModel!!.sendData(note)

        }
    }
    override fun dismissDialog() {
        Log.d("NoteDialog", "cancel")
        dismiss()
    }


    override fun success(message: String) {

        Toast.makeText(context!!, message, Toast.LENGTH_LONG).show()

        listener.success(message)

        dismissDialog()
    }

    interface NoteListDialogListener{
        fun success(message: String)
    }

    override fun handlerError(message: String) {
        Toast.makeText(context!!, "Nota não adicionada", Toast.LENGTH_LONG).show()
    }

}