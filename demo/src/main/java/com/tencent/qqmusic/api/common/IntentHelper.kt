package com.tencent.qqmusic.api.common

import android.content.BroadcastReceiver
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter

object IntentHelper {
    private const val ACTION_VERIFICATION = "callback_verify_notify"
    fun broadcastVerify(context: ContextWrapper, ret: String?) {
        val intent = Intent()
        intent.action = ACTION_VERIFICATION
        intent.putExtra("ret", ret)
        context.sendBroadcast(intent)
    }

    fun registerVerify(context: ContextWrapper, broadcastReceiver: BroadcastReceiver) {
        val filter = IntentFilter()
        filter.addAction(ACTION_VERIFICATION)
        context.registerReceiver(broadcastReceiver, filter)
    }
}