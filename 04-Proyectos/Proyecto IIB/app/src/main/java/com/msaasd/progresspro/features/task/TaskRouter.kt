package com.msaasd.progresspro.features.task

import android.content.Context
import android.content.Intent
import com.msaasd.progresspro.features.base.BaseActivityRouter
import com.msaasd.progresspro.util.Constants

class TaskRouter: BaseActivityRouter {
    override fun intent(activity: Context): Intent {
        return Intent(activity, TaskActivity::class.java)
    }

    private fun intent(activity: Context, userId: Int): Intent {
        val intent = Intent(activity, AddTaskActivity::class.java)
        intent.putExtra(Constants.ExtraDataConstants.USER_ID, userId)
        return intent
    }

    fun launch(activity: Context, userId: Int) {
        val intent = intent(activity, userId)
        activity.startActivity(intent)
    }
}