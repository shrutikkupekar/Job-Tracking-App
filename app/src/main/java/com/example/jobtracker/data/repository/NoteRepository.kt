package com.example.jobtracker.data.repository

import com.example.jobtracker.data.model.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class NoteRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun userId(): String =
        auth.currentUser?.uid ?: throw IllegalStateException("User not logged in")

    private fun notesCollection() =
        firestore.collection("users")
            .document(userId())
            .collection("notes")

    // ---------------- CREATE ----------------
    suspend fun addNote(note: Note) {
        val docRef = notesCollection().document()
        val noteWithId = note.copy(id = docRef.id)
        docRef.set(noteWithId).await()
    }

    // ---------------- DELETE ----------------
    suspend fun deleteNote(noteId: String) {
        notesCollection()
            .document(noteId)
            .delete()
            .await()
    }

    // ---------------- READ (REALTIME) ----------------
    fun getNotesForJob(jobId: String): Flow<List<Note>> = callbackFlow {
        val listener = notesCollection()
            .whereEqualTo("jobId", jobId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val notes = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(Note::class.java)?.copy(id = doc.id)
                } ?: emptyList()

                trySend(notes)
            }

        awaitClose { listener.remove() }
    }
}
