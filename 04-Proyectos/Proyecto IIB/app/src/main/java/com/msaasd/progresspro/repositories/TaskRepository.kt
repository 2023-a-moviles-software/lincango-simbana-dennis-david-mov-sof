package com.msaasd.progresspro.repositories

import androidx.lifecycle.LiveData
import com.msaasd.progresspro.models.daos.TaskDao
import com.msaasd.progresspro.models.entities.Task
import com.msaasd.progresspro.models.entities.TaskState
import com.msaasd.progresspro.models.entities.relations.TaskWithSubtasks

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun getTaskById(taskId: Int): LiveData<Task> {
        return taskDao.getTaskById(taskId)
    }

    suspend fun getTasksForUser(userId: Int): LiveData<List<Task>> {
        return taskDao.getTasksForUser(userId)
    }

    suspend fun getTaskWithSubtasks(taskId: Int): LiveData<List<TaskWithSubtasks>> {
        return taskDao.getTaskWithSubtasks(taskId)
    }

    suspend fun getCreatedTasks(userId: Int, createdState: TaskState = TaskState.CREATED): LiveData<List<Task>> {
        return taskDao.getCreatedTasks(userId, createdState)
    }

    suspend fun getDoneTasks(userId: Int, doneState: TaskState = TaskState.DONE): LiveData<List<Task>> {
        return taskDao.getDoneTasks(userId, doneState)
    }

    suspend fun getPinnedTasks(userId: Int): LiveData<List<Task>> {
        return taskDao.getPinnedTasks(userId)
    }
}
