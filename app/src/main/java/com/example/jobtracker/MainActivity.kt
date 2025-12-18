package com.example.jobtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jobtracker.ui.navigation.NavGraph
import com.example.jobtracker.ui.theme.JobTrackerTheme
import com.example.jobtracker.viewmodel.AuthViewModel
import com.google.firebase.FirebaseApp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 Initialize Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            JobTrackerTheme {
                val authViewModel: AuthViewModel = viewModel()
                val isLoggedIn by authViewModel.isAuthenticated.collectAsState()

                NavGraph(isLoggedIn = isLoggedIn)
            }
        }
    }
}
