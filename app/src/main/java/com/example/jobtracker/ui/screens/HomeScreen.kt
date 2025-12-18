package com.example.jobtracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jobtracker.data.model.JobApplication
import com.example.jobtracker.ui.navigation.Routes
import com.example.jobtracker.viewmodel.AuthViewModel
import com.example.jobtracker.viewmodel.JobViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    jobViewModel: JobViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel()
) {
    val jobs by jobViewModel.jobs.collectAsState()
    val isLoading by jobViewModel.isLoading.collectAsState()
    val error by jobViewModel.errorMessage.collectAsState()

    var jobToDelete by remember { mutableStateOf<JobApplication?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Job Tracker") },
                actions = {
                    IconButton(
                        onClick = {
                            authViewModel.signOut()
                            navController.navigate(Routes.LOGIN) {
                                popUpTo(Routes.HOME) { inclusive = true }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.ADD_JOB) }
            ) {
                Text("+")
            }
        }
    ) { padding ->

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = error ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            jobs.isEmpty() -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No job applications yet",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tap + to add your first job",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(jobs) { job ->
                        JobItem(
                            job = job,
                            onEdit = {
                                navController.navigate("edit_job/${job.id}")
                            },
                            onNotes = {
                                navController.navigate("notes/${job.id}")
                            },
                            onDeleteRequest = {
                                jobToDelete = job
                            }
                        )
                    }
                }
            }
        }

        // 🔴 DELETE CONFIRMATION DIALOG
        if (jobToDelete != null) {
            AlertDialog(
                onDismissRequest = { jobToDelete = null },
                title = { Text("Delete Job") },
                text = { Text("Are you sure you want to delete this job application?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            jobViewModel.deleteJob(jobToDelete!!.id)
                            jobToDelete = null
                        }
                    ) {
                        Text("Delete", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { jobToDelete = null }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

/* -------------------- JOB ITEM -------------------- */

@Composable
private fun JobItem(
    job: JobApplication,
    onEdit: () -> Unit,
    onNotes: () -> Unit,
    onDeleteRequest: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onEdit() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(job.companyName, style = MaterialTheme.typography.titleMedium)
            Text(job.jobTitle)
            StatusChip(status = job.status)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // ✅ CLEAR NOTES BUTTON
                OutlinedButton(
                    onClick = onNotes,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text("Notes")
                }

                // ❌ DELETE
                TextButton(onClick = onDeleteRequest) {
                    Text(
                        text = "Delete",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

/* -------------------- STATUS CHIP -------------------- */

@Composable
fun StatusChip(status: String) {
    val color = when (status.lowercase()) {
        "applied" -> MaterialTheme.colorScheme.primary
        "interview" -> MaterialTheme.colorScheme.tertiary
        "offer" -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.outline
    }

    Surface(
        color = color.copy(alpha = 0.15f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = status,
            color = color,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}
