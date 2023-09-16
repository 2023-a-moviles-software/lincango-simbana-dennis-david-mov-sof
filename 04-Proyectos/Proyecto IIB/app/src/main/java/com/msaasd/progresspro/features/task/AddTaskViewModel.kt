package com.msaasd.progresspro.features.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msaasd.progresspro.models.entities.Task
import com.msaasd.progresspro.models.entities.Subtask
import com.msaasd.progresspro.repositories.SubtaskRepository
import com.msaasd.progresspro.repositories.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val taskRepository: TaskRepository,
    private val subtaskRepository: SubtaskRepository
): ViewModel() {

    fun insertTask(task: Task) = viewModelScope.launch {
        taskRepository.insert(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.update(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.delete(task)
    }

    fun insertSubtask(subtask: Subtask) = viewModelScope.launch {
        subtaskRepository.insert(subtask)
    }

    fun updateSubtask(subtask: Subtask) = viewModelScope.launch {
        subtaskRepository.update(subtask)
    }

    fun deleteSubtask(subtask: Subtask) = viewModelScope.launch {
        subtaskRepository.delete(subtask)
    }

    fun getTaskWithSubtasks(taskId: Int) = viewModelScope.launch {
        taskRepository.getTaskWithSubtasks(taskId)
    }

    fun getPendingSubtasksForTask(taskId: Int) = viewModelScope.launch {
        subtaskRepository.getPendingSubtasksForTask(taskId)
    }

    fun getSubtaskById(subtaskId: Int) = viewModelScope.launch {
        subtaskRepository.getSubtaskById(subtaskId)
    }

    fun getSubtasksForTask(taskId: Int) = viewModelScope.launch {
        subtaskRepository.getSubtasksForTask(taskId)
    }
}