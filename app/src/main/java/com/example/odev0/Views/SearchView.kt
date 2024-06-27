package com.example.odev0.Views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.odev0.viewmodels.SearchViewModel
import com.example.odev0.internet.googleSearch.Response.Item
import com.example.odev0.navigator.Navhoster

class SearchView {

    val viewModel = SearchViewModel()

    @Composable
    fun searcView(navController: NavController) {

        var searchList= viewModel.getResponse.observeAsState(listOf<Item>())

        val searchField = remember {
            mutableStateOf("")
        }
        
        var animController = remember {
            mutableStateOf(false)
        }

        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.Cyan),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "Aşşağıda internet aramalarını kontrol edebilirsin")
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.08f),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    TextField(
                        value = searchField.value,
                        onValueChange = { searchField.value = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                        ),
                        shape = RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .fillMaxHeight(),
                        label = { Text(text = "İhtiyacınız olan şey nedir ?")}
                    )

                    Icon(imageVector = Icons.Default.Search, contentDescription ="",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(
                                Color.Yellow, RoundedCornerShape(
                                    topEnd = 5.dp,
                                    bottomEnd = 5.dp
                                )
                            )
                            .clickable {
                                viewModel.search(searchField.value)
                                animController.value = !animController.value
                            },
                    )
                }



                AnimatedVisibility(visible = animController.value) {
                    val anim = animateFloatAsState(targetValue = 0.9f)
                    val anim0 = animateFloatAsState(targetValue = 0.7f)
                    LazyColumn(
                        modifier = Modifier
                            .background(Color.LightGray, RoundedCornerShape(15.dp))
                            .fillMaxWidth(if (animController.value) anim.value else 0f)
                            .fillMaxHeight(if (animController.value) anim0.value else 0f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        items(searchList.value)
                        {
                            SearchCard(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxSize(0.9f),
                                title = it.title,
                                link = it.link)
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }

                Button(onClick = {
                    navController.navigate(Navhoster.ChatScreen)
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                    ) {
                    Text(
                        text = "Karar vermekte zorlanıyorsan yapay zeka'ya sormaya ne dersin ?",
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
            }
        }
    }

    @Composable
    fun SearchCard(
        modifier: Modifier = Modifier,
        title : String,
        link : String
    ) {
        val context = LocalContext.current
        Column(
            modifier = modifier
                .background(Color.LightGray)
                .height(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = title,modifier = Modifier
                .padding(2.dp)
                .background(Color.White, RoundedCornerShape(5.dp)),
                textAlign = TextAlign.Center
            )

            Button(onClick = { viewModel.toShareLink(link,context) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                ),
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text(text = "Siteye Gitmek İçin Tıklayınız")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun preview(){
        searcView(rememberNavController())
    }
}