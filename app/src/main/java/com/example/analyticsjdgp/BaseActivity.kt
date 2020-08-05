package com.example.analyticsjdgp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.huawei.agconnect.crash.AGConnectCrash
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsInstance
import com.huawei.hms.analytics.HiAnalyticsTools

abstract  class BaseActivity :AppCompatActivity(){

    lateinit var huaweiAnalyticsInstance:HiAnalyticsInstance
    lateinit var crashInstance:AGConnectCrash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HiAnalyticsTools.enableLog()
        crashInstance=AGConnectCrash.getInstance()
        crashInstance.enableCrashCollection(true)
        huaweiAnalyticsInstance=HiAnalytics.getInstance(applicationContext)
    }

}