package com.example.odev0.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odev0.internet.googleSearch.Response.Item
import com.example.odev0.internet.googleSearch.searchApi
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    var getResponse = MutableLiveData<List<Item>>()

    fun search(query :String){
        viewModelScope.launch {
            val result = searchApi.getSearchApi().getSearch(query = query)
            if (result.isSuccessful){

                val sonuclist = result.body()

                getResponse.value = sonuclist?.items

                Log.d("Başarılı" , result.body()?.items.toString())
            }
            if (!result.isSuccessful){
                Log.e("Hata" , result.errorBody().toString())
            }
        }
    }

    fun toShareLink(url : String,context : Context){
        val intent = Intent(Intent.ACTION_VIEW , Uri.parse(url))
        startActivity(context,intent,null)
    }
}