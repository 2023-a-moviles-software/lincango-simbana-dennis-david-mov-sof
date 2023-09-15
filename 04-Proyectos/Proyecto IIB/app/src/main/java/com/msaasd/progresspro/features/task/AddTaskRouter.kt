package com.msaasd.progresspro.features.task

import android.content.Context
import android.content.Intent
import com.msaasd.progresspro.features.base.BaseActivityRouter
import com.msaasd.progresspro.util.Constants

class AddTaskRouter: BaseActivityRouter {
    override fun intent(activity: Context): Intent {
        return Intent(activity, AddTaskActivity::class.java)
    }

    private fun intent(activity: Context, taskId: Int): Intent {
        val intent = Intent(activity, AddTaskActivity::class.java)
        intent.putExtra(Constants.ExtraDataConstants.TASK_ID, taskId)
        return intent
    }

    fun launch(activity: Context, taskId: Int) {
        val intent = intent(activity, taskId)
        activity.startActivity(intent)
    }
}