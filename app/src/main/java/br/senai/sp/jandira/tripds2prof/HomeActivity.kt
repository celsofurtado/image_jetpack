package br.senai.sp.jandira.tripds2prof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.tripds2prof.ui.theme.TripDS2TTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripDS2TTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    TripDS2TTheme {
        Greeting("Android")
    }
}