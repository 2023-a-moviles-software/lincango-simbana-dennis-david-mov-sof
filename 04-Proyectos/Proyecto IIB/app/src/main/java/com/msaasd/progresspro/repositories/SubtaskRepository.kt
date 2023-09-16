package com.msaasd.progresspro.repositories

import androidx.lifecycle.LiveData
import com.msaasd.progresspro.models.daos.SubtaskDao
import com.msaasd.progresspro.models.entities.Subtask

class SubtaskRepository(private val subtaskDao: SubtaskDao) {

    suspend fun insert(subtask: Subtask) {
        subtaskDao.insert(subtask)
    }

    suspend fun update(subtask: Subtask) {
        subtaskDao.update(subtask)
    }

    suspend fun delete(subtask: Subtask) {
        subtaskDao.delete(subtask)
    }

    suspend fun getSubtaskById(subtaskId: Int): LiveData<Subtask> {
        return subtaskDao.getSubtaskById(subtaskId)
    }

    suspend fun getSubtasksForTask(taskId: Int): LiveData<List<Subtask>> {
        return subtaskDao.getSubtasksForTask(taskId)
    }

    suspend fun getPendingSubtasksForTask(taskId: Int): LiveData<List<Subtask>> {
        return subtaskDao.getPendingSubtasksForTask(taskId)
    }
}
