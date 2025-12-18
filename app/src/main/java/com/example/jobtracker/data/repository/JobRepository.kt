package com.example.jobtracker.data.repository

import com.example.jobtracker.data.model.JobApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class JobRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun getUserId(): String {
        return auth.currentUser?.uid ?: "DEV_USER"
    }


    private fun jobsCollection() =
        firestore.collection("users")
            .document(getUserId())
            .collection("jobApplications")

    // CREATE
    suspend fun addJob(job: JobApplication) {
        val doc = jobsCollection().document()
        val newJob = job.copy(id = doc.id, userId = getUserId())
        doc.set(newJob).await()
    }

    // UPDATE
    suspend fun updateJob(job: JobApplication) {
        jobsCollection()
            .document(job.id)
            .set(job)
            .await()
    }

    // DELETE
    suspend fun deleteJob(jobId: String) {
        jobsCollection()
            .document(jobId)
            .delete()
            .await()
    }

    // READ (REAL-TIME)
    fun getJobs(): Flow<List<JobApplication>> = callbackFlow {
        // 🔥 EMIT INITIAL STATE
        trySend(emptyList())

        val listener = jobsCollection()
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val jobs = snapshot?.documents
                    ?.mapNotNull { it.toObject(JobApplication::class.java) }
                    ?: emptyList()

                trySend(jobs)
            }

        awaitClose { listener.remove() }
    }

}
