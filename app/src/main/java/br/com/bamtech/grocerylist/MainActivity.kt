package br.com.bamtech.grocerylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.bamtech.grocerylist.presentation.grocery.GroceryRoute
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GroceryListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GroceryRoute(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}