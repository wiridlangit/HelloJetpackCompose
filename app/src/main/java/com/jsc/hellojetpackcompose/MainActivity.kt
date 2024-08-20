package com.jsc.hellojetpackcompose

import android.graphics.Bitmap
import com.jsc.hellojetpackcompose.ui.screen.AddItemScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jsc.hellojetpackcompose.ui.screen.DetailScreen
import com.jsc.hellojetpackcompose.ui.screen.MainScreen
import com.jsc.hellojetpackcompose.ui.screen.AboutProfileScreen
import com.jsc.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme

data class Item(val name: String, val description: String, val image: Bitmap? = null )

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloJetpackComposeTheme {
                val navController = rememberNavController()
                val itemList = remember { mutableStateListOf<Item>() }

                val sampleName = listOf(
                    Item("Andre", "Andre adalah seorang aktor, presenter, dan komedian Indonesia yang terkenal dengan karakternya di acara komedi Opera Van Java dan sebagai vokalis band Stinky pada tahun 90-an."),
                    Item("Desta", "Desta adalah mantan drummer dari grup musik Club 80's, yang kemudian menjadi presenter, aktor, dan komedian di berbagai acara televisi populer seperti Tonight Show."),
                    Item("Parto", "Parto adalah anggota grup lawak Patrio yang terkenal dengan perannya di berbagai acara komedi Indonesia, terutama Opera Van Java dan Pesbukers."),
                    Item("Wendy", "Wendy adalah seorang komedian yang memulai kariernya bersama grup lawak Cagur dan kini aktif sebagai presenter di berbagai acara televisi."),
                    Item("Komeng", "Komeng adalah komedian senior Indonesia yang terkenal dengan gaya lawaknya yang spontan dan jenaka. Dia dikenal lewat acara-acara komedi seperti Spontan dan Opera Van Java."),
                    Item("Raffi Ahmad", "Raffi adalah aktor, presenter, dan pengusaha sukses di Indonesia. Dia dikenal sebagai salah satu selebritas paling populer dan aktif di dunia hiburan, terutama lewat acara Pesbukers dan Rumah Mama Amy."),
                    Item("Andhika Pratama", "Andhika adalah aktor, penyanyi, dan presenter yang dikenal lewat berbagai acara televisi, termasuk sebagai presenter tetap di Dahsyat dan Indonesian Idol."),
                    Item("Vincent", "Vincent adalah mantan bassis grup musik Club 80's yang kini dikenal sebagai presenter dan komedian, terutama di acara Tonight Show bersama Desta."),
                    Item("Sule", "Sule adalah komedian, aktor, dan presenter yang dikenal lewat gaya lawaknya yang unik dan perannya di acara komedi Opera Van Java. Dia juga sukses dengan program komedinya sendiri, Ini Talkshow."),
                    Item("Cak Lontong", "Cak Lontong adalah komedian yang terkenal dengan gaya lawakannya yang cerdas dan menggunakan humor logika. Dia dikenal lewat berbagai acara televisi seperti Stand Up Comedy dan Waktu Indonesia Bercanda.")
                )

                itemList.addAll(sampleName)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationComponent(navController, itemList, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavController, itemList: MutableList<Item>, modifier: Modifier = Modifier) {
    NavHost(navController = navController as NavHostController, startDestination = "main_screen") {
        composable("main_screen") {
            MainScreen(navController, itemList, modifier)
        }
        composable("detail_screen/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
            val item = index?.let { itemList.getOrNull(it) }
            DetailScreen(navController, item) {
                item?.let { itemList.remove(it) }
                navController.popBackStack()
            }
        }
        composable("about_profile") {
            AboutProfileScreen(navController)
        }
        composable("add_item_screen") {
            AddItemScreen(navController, itemList)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    HelloJetpackComposeTheme {
        val navController = rememberNavController()
        val itemList = remember { mutableStateListOf<Item>() }
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavigationComponent(navController, itemList, modifier = Modifier.padding(innerPadding))
        }
    }
}
