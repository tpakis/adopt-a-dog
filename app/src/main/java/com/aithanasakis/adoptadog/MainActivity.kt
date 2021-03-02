package com.aithanasakis.adoptadog

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.aithanasakis.adoptadog.ui.NavigationGraph
import com.aithanasakis.adoptadog.ui.theme.MyApplicationTheme

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
               MyApp(viewModel)
            }
        }
    }
}

@Composable
fun MyApp(viewModel: MainViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        NavigationGraph(mainViewModel = viewModel)
    }
}