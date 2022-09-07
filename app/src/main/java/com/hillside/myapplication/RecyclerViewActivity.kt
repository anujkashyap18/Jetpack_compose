package com.hillside.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hillside.myapplication.ui.theme.MyApplicationTheme
import com.hillside.myapplication.utils.User
import com.hillside.myapplication.utils.dummyData
import javax.sql.DataSource

class RecyclerViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RecyclerView(dummyData())
                }
//            }
        }
    }

    @Composable
    fun EachRow(user: User){
        Card(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
            shape = RoundedCornerShape(CornerSize(12.dp)),
            elevation = 2.dp
        ) {
            Row(modifier = Modifier.padding(6.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                        .clip(RoundedCornerShape(CornerSize(10.dp)))
                )
                Text(text = user.description, modifier = Modifier.padding(8.dp))
            }
        }
    }

    @Composable
    fun RecyclerView(user:List<User>){

        //Lazy column use for vertical listing and row column for horizontally
        LazyColumn{
            items(user){user->
                EachRow(user)
            }
        }
    }
}
