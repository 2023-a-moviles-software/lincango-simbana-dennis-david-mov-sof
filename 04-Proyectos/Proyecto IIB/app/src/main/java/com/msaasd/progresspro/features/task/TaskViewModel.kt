package com.msaasd.progresspro.features.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msaasd.progresspro.repositories.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel() {

    fun getCreatedTasks(userId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getCreatedTasks(userId)
    }

    fun getPinnedTasks(userId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getPinnedTasks(userId)
    }

    fun getDoneTasks(userId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getDoneTasks(userId)
    }

    fun getTaskCount(userId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getTasksForUser(userId)
    }
}