package com.example.myapplicationindividual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplicationindividual.ui.theme.MyApplicationIndividualTheme
import com.example.myapplicationindividual.ui.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationIndividualTheme {
                MainScreen()
            }
        }
    }
}
