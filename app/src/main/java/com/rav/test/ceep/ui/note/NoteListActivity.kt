package com.rav.test.ceep.ui.note

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rav.test.ceep.BR
import com.rav.test.ceep.R
import com.rav.test.ceep.data.model.Note
import com.rav.test.ceep.databinding.ActivityNoteListBinding
import com.rav.test.ceep.databinding.DialogNoteListBinding
import com.rav.test.ceep.ui.base.BaseActivity
import com.rav.test.ceep.ui.note.adapter.NoteListAdapter
import com.rav.test.ceep.ui.note.dialog.NoteListDialog
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity: BaseActivity<ActivityNoteListBinding, NoteListViewModel>(), NoteListNavigator {
    private var viewModel: NoteListViewModel? = null
    private var adapter: NoteListAdapter? = null

    override fun getBindVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_note_list
    }

    override fun getViewModel(): NoteListViewModel {
        viewModel = NoteListViewModel()
        return viewModel!!
    }

    override fun notes(notes: ArrayList<Note>) {
        adapter = NoteListAdapter(notes, this)
        note_list_recyclerview.adapter = adapter
    }

    override fun handlerError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel!!.setNavigator(this)
        viewModel!!.init()

        val recyclerView = note_list_recyclerview
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    /*override fun noteListDialog(){
        NoteListDialog(NoteListDialog).show(supportFragmentManager, "")
    }*/

    override fun openDialog(view: View) {
        val dialog = NoteListDialog(
            object : NoteListDialog.NoteListDialogListener {
                override fun success(message: String) {
                    viewModel!!.fetchNotes()
                }
            })

        dialog.show(supportFragmentManager, "")
    }
}
