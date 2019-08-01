package com.rav.test.ceep.ui.note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rav.test.ceep.R
import com.rav.test.ceep.data.model.Note
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class NoteListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val call = RetrofitInitializer().noteService().list()
        call.enqueue(object: Callback<List<Note>?>){
            override fun onResponse(call: Call<List<Note>?>?,
                                    response: Response<List<Note>?>?){
                response?.body()?.let{
                    val notes: List<Note>? = it
                }
            }
            override fun onFaillure(call : Call<List<Note>?>?,
                                    t: Throwable?){

            }
        }

        notes()

    }

    private fun notes(): List<Note>{
        return listOf(
            Note("Leitura",
                "Livro de Kotlin"),
            Note("Pesquisa",
                "Como criar testes automatizados em Kotlin"),
            Note("Estudo",
                "Padrão de desenvolvimento MVVM")
        )

//        val service = NoteService()
//        service.list(object: BaseService.FetchDataListener<ArrayList<Note>>{
//            override fun onFetchDataSuccess(dataObject: ArrayList<Note>) {
//                val recyclerView = note_list_recyclerview
//                recyclerView.adapter = NoteListAdapter(dataObject, this@NoteListActivity)
//                val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//                recyclerView.layoutManager = layoutManager
//            }
//
//            override fun onFetchDataFail(message: String) {
//                Toast.makeText(this@NoteListActivity, message, Toast.LENGTH_LONG).show()
//            }
//
//        })
    }

}