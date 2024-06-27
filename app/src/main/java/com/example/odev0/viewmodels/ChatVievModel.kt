package com.example.odev0.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odev0.internet.geminiai.Gemini
import com.example.odev0.models.Message
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
class ChatViewModel : ViewModel() {

    var chatList: MutableLiveData<ArrayList<Message>> = MutableLiveData(ArrayList())

    var resultList = arrayListOf<Message>()

    var sayac = 0
    fun first(){
        var response = Message(false, "selam bana istediğin her şeyi sorabilirsin")
        if (sayac == 0){
            resultList.add(response)
            chatList.value = ArrayList(resultList)
            sayac ++
        }
    }
    fun toMessage(prompt: String) {
        val askToAi = Message(true, prompt)
        var response = Message(false, "selam")

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                response = Gemini.GetResponse(prompt)!!
            }
            resultList.add(askToAi)
            resultList.add(response)
            println(response)
            chatList.value = ArrayList(resultList)
        }
    }
}
