package com.example.jobtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobtracker.data.model.JobApplication
import com.example.jobtracker.data.repository.JobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JobViewModel : ViewModel() {

    private val repository = JobRepository()

    private val _jobs = MutableStateFlow<List<JobApplication>>(emptyList())
    val jobs: StateFlow<List<JobApplication>> = _jobs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        observeJobs()
    }

    private fun observeJobs() {
        viewModelScope.launch {
            _isLoading.value = true

            repository.getJobs().collect { jobList ->
                _jobs.value = jobList

                // 🔥 STOP LOADING ON FIRST EMISSION
                _isLoading.value = false
            }
        }
    }


    fun addJob(job: JobApplication) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.addJob(job)
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to add job"
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun updateJob(job: JobApplication) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.updateJob(job)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteJob(jobId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.deleteJob(jobId)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
