package com.example.odev0.Views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.odev0.models.Message
import com.example.odev0.navigator.Navhoster
import com.example.odev0.viewmodels.ChatViewModel

class ChatView {

    val viewModel = ChatViewModel()

    @Composable
    fun chatView(modifier: Modifier = Modifier,navController: NavController) {

        val imeState = rememberImeState()
        val scrollState = rememberScrollState()

        viewModel.first()

        LaunchedEffect(key1 = imeState.value) {
            if (imeState.value) {
                scrollState.scrollTo(scrollState.maxValue)
                Log.d("Ime", "Çalıştııı")
            }

        }

        val chatList = viewModel.chatList.observeAsState(initial = ArrayList())

        val prompt = remember {
            mutableStateOf("")
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
        ) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize()
                    .imePadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.05f)
                        .background(
                            Color.White,
                            RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Kararını vermeye hazır olduğunda yandaki butona dokun", fontSize = 10.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.5f)
                            .clickable {
                                navController.navigate(Navhoster.EndScreen)
                            }
                    )
                }
                Column(
                    Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .weight(1f)
                        .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                    LazyColumn(
                        modifier = Modifier
                    ) {
                        items(chatList.value.size) {
                            chatBox(message = chatList.value.get(it))

                            Spacer(modifier = Modifier.height(15.dp))

                        }
                    }
                }
                Row (
                    modifier = Modifier
                        .fillMaxHeight(if (imeState.value) 0.2f else 0.1f),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    TextField(
                        value = prompt.value,
                        onValueChange = { prompt.value = it },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxHeight(0.8f)
                            .fillMaxWidth(0.85f)
                            .padding(5.dp)
                    )

                    Image(
                        imageVector = Icons.Default.Send,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)
                            .padding(5.dp)
                            .clickable {
                                viewModel.toMessage(prompt.value)
                                prompt.value = ""
                            }
                    )
                }
            }
        }

    }
    @Composable
    fun chatBox(modifier: Modifier = Modifier,message: Message) {
        val modifier = if(message.fromMe)
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 15.dp,
                        bottomStart = 15.dp,
                        topEnd = 15.dp
                    )
                )
                .defaultMinSize(50.dp)
                .background(Color.White)
                .padding(8.dp)
        else
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 15.dp,
                        bottomEnd = 15.dp,
                        topEnd = 15.dp
                    )
                )
                .defaultMinSize(50.dp)
                .background(Color.White)
                .padding(8.dp)

        val boxArrangment = if (message.fromMe)
            Alignment.CenterEnd
        else
            Alignment.CenterStart

        Box(modifier = Modifier,
            contentAlignment = boxArrangment
        ){
            Box(
                modifier = modifier
            )
            {
                Text(text = message.prompt, fontSize = 12.sp, color = Color.Black)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun preview(modifier: Modifier = Modifier) {
        chatView(navController = rememberNavController())
    }
}