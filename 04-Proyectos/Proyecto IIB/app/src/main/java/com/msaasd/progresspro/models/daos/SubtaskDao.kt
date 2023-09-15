package com.msaasd.progresspro.models.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.msaasd.progresspro.models.entities.Subtask

@Dao
interface SubtaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subtask: Subtask)

    @Update
    suspend fun update(subtask: Subtask)

    @Delete
    suspend fun delete(subtask: Subtask)

    @Transaction
    @Query("SELECT * FROM subtasks WHERE subtask_id = :subtaskId")
    suspend fun getSubtaskById(subtaskId: Int): LiveData<Subtask>

    @Transaction
    @Query("SELECT * FROM subtasks WHERE task_id = :taskId")
    suspend fun getSubtasksForTask(taskId: Int): LiveData<List<Subtask>>

    @Transaction
    @Query("SELECT * FROM subtasks WHERE task_id = :taskId ")
    suspend fun getPendingSubtasksForTask(taskId: Int): LiveData<List<Subtask>>
}