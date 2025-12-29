package com.nazwakhayla.sekaiprofileviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nazwakhayla.sekaiprofileviewer.ui.theme.SekaiProfileViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SekaiProfileViewerTheme {
                App()
            }
        }
    }
}