package com.hillside.myapplication.retrofit.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hillside.myapplication.retrofit.Post
import com.hillside.myapplication.retrofit.ui.ui.theme.MyApplicationTheme
import com.hillside.myapplication.retrofit.util.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    GETData(mainViewModel = mainViewModel)
                }
            }
        }
    }

    @Composable
    fun EachRow(post: Post) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = post.body, modifier = Modifier.padding(10.dp), fontStyle = FontStyle.Italic)
        }
    }

    @Composable
    fun GETData(mainViewModel: MainViewModel) {
        when (val result = mainViewModel.response.value) {
            is ApiState.Success -> {
                LazyColumn {
                    items(result.data) { response ->
                        EachRow(response)
                    }
                }
            }
            is ApiState.Error -> {
                Text(text = "${result.msg}")
            }
            ApiState.Loading ->{
                CircularProgressIndicator()
            }
            ApiState.Empty ->{
                
            }
        }
    }
}
