package com.example.tasktodayapppam

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodayapppam.ui.theme.TaskTodayAppPamTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent(drawerState = DrawerState(initialValue = DrawerValue.Closed))
        }
    }
}

@Composable
fun MainScreenContent(drawerState: DrawerState) {
    val scaffoldState = rememberScaffoldState( drawerState = drawerState)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "TaskTodayApp")},
                navigationIcon = {
                    IconButton(onClick = {
                        CoroutineScope(Dispatchers.Default).launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu")
                    }
                }
            )
        },
        drawerBackgroundColor = Color.Red,
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
            //DRAWER HEADER
            Box(
                modifier = Modifier
                    .background(Color.Magenta)
                    .height(16.dp)
            ){
                Text(text = "Opcoes!!!")
            }
            //DRAWER CONTENT
            Column() {
                Text(text = "Opcao de Menu 1")
                Text(text = "Opcao de Menu 2")
                Text(text = "Opcao de Menu 3")
            }
        },
        content = {
            paddingValues -> Log.i("paddinValues", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier.fillMaxWidth())
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskname = "Preparar Aula LazyList/LazyColum",
                    taskDetails = "E bem melhor usar lazylist ao inves de colum",
                    deadEndDate = Date()
                )
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskname = "Prova de Matematica",
                    taskDetails = "Estudar Calculo captulo 1 e 2",
                    deadEndDate = Date())
            }
        },
        bottomBar = {
            BottomAppBar(
                content = {Text("asdf") }
            )
        }
    ) //Scaffold
} //fun

@Composable
fun MySearchField(modificador: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisar Tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        }
    )
} //fun MySearchField

@Composable
fun MyTaskWidget(
    modificador: Modifier,
    taskname: String,
    taskDetails: String,
    deadEndDate: Date
    ){
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Icons of a pendent task"
            )
            Text(
                text = dateFormatter.format(deadEndDate),
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 12.sp
            )
        }//Column Icone
        Column(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Black)
                .padding(3.dp)
        ) {
            Text(
                text = taskname,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = taskDetails,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }//Column
    }//Row
    Spacer(modifier = Modifier.height(16.dp))
}//fun MyTaskWidget

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContent(drawerState = DrawerState(DrawerValue.Closed))
}