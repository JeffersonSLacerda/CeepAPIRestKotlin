package com.rav.test.ceep.ui.note

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rav.test.ceep.R
import com.rav.test.ceep.data.model.Note
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val recyclerView = note_list_recyclerview
        recyclerView.adapter = NoteListAdapter(notes(), this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    private fun notes(): List<Note> {
        return listOf(
            Note("Leitura",
                "Livro de Kotlin"),
            Note("Pesquisa",
                "Como criar testes automatizados em Kotlin"),
            Note("Estudo",
                "Padrão de desenvolvimento MVVM")
        )

    }

}