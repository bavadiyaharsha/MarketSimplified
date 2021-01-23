package com.example.marketsimplified.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketsimplified.ApiInterface
import com.example.marketsimplified.MarketSimplifiedApplication
import com.example.marketsimplified.model.LocalRepoResp
import com.example.marketsimplified.model.LocalRepoRespItem
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var errormsg = MutableLiveData<String>()
    var repolist = MutableLiveData<ArrayList<LocalRepoRespItem>>()

    fun getLocalRepo() {
        isLoading.value=true

        val userDataService = MarketSimplifiedApplication.instance?.requestQueue?.create(
            ApiInterface::class.java
        )

        val call: Call<LocalRepoResp> = userDataService!!.repositories()
        call.enqueue(object : Callback<LocalRepoResp> {
            override fun onResponse(
                call: Call<LocalRepoResp?>?,
                response: Response<LocalRepoResp?>
            ) {
                try {
                    if (response.isSuccessful) {
                        val jobsRes = response.body()
                        if(jobsRes!=null){
                            repolist.value=jobsRes as ArrayList<LocalRepoRespItem>
                        }

                    } else {
                        val errorbody= response.errorBody()

                    }
                    isLoading.value = false
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<LocalRepoResp>, t: Throwable) {
                errormsg.value = t!!.message
                isLoading.value = false
            }
        })
    }


}