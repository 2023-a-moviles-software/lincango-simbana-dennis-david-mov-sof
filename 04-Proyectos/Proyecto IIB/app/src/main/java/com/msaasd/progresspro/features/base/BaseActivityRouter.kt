package com.msaasd.progresspro.features.base

import android.content.Context
import android.content.Intent

interface BaseActivityRouter {
    fun intent(activity: Context): Intent

    fun launch(activity: Context) = activity.startActivity(intent(activity))
}