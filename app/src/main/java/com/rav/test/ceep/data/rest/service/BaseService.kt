package com.rav.test.ceep.data.rest.service

import com.google.gson.annotations.SerializedName
import com.rav.test.ceep.utils.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseService {
    private val BASE_URL = AppConstants.BASE_URL

    val NO_DATA_RESULT = "A consulta não retornou dados"

    private fun getRetrofitInstance(): Retrofit {
        var retrofit: Retrofit? = null
        try {
            val httpClient = OkHttpClient.Builder()

            val client =
                httpClient
                    .readTimeout(200, TimeUnit.SECONDS)
                    .connectTimeout(200, TimeUnit.SECONDS)
                    .build()

            if(retrofit == null){
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
        }
        catch (e: Exception){}

        return retrofit!!
    }

    fun <T: Any> createService(service: Class<T>): T{
        return getRetrofitInstance().create(service)
    }

    open fun <T>  fetchData(call: Call<T>, listener: FetchDataListener<T>){

        call.clone().enqueue(object : Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {

                try {
                    if (response?.code() == 200 && response.body() != null)
                        listener.onFetchDataSuccess(response.body()!!)
                    else
                        listener.onFetchDataFail(NO_DATA_RESULT)
                }
                catch (e : Exception){
                    listener.onFetchDataFail(NO_DATA_RESULT)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (t.message.isNullOrBlank() || t.message!!.contains("Failed to connect"))
                    listener.onFetchDataFail("Fail to connect a server.")
                else
                    listener.onFetchDataFail(NO_DATA_RESULT)
            }
        })

    }

    interface FetchDataListener<T>{

        fun onFetchDataSuccess(dataObject: T)
        fun onFetchDataFail(message: String)
    }

    class BaseArrayResponse<T>{

        @SerializedName("response")
        var response: ArrayList<T>? = null
    }

    class BaseObjectResponse<T>{

        @SerializedName("response")
        var response: T? = null
    }

    class BaseDefaultResponse{

        var message: String? = null
    }
}