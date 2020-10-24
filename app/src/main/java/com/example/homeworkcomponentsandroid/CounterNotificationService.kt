package com.example.homeworkcomponentsandroid

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import androidx.core.app.NotificationCompat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


class CounterNotificationService : Service() {
    private val channelId = "my_channel"
    private var mManager: NotificationManager? = null
    private var mBuilder: NotificationCompat.Builder? = null
    private var channel: NotificationChannel? = null
    private var mScheduledExecutorService: ScheduledExecutorService? = null
    val mMessenger =
        Messenger(IncomingHandler())

    override fun onBind(intent: Intent): IBinder? {
        return mMessenger.binder
    }

    private class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_REGISTER_CLIENT -> mClient.add(
                    msg.replyTo
                )

                MSG_UNREGISTER_CLIENT -> mClient.remove(
                    msg.replyTo
                )
                else -> super.handleMessage(msg)
            }
        }
    }

    private fun sendMessageToUI(value: Int) {
        for (i in mClient.indices.reversed()) {
            try {
                 mClient[i].send(
                    Message.obtain(
                        null,
                        MSG_SET_PROGRESS_VALUE,
                        value,
                        0
                    )
                )
            } catch (e: RemoteException) {
                mClient.removeAt(i)
            }
        }
    }

    private val notificationBuilder: NotificationCompat.Builder
        private get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (mManager!!.getNotificationChannel(channelId) == null) {
                channel = NotificationChannel(
                    channelId,
                    "Users channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                mManager!!.createNotificationChannel(channel!!)
            }
            NotificationCompat.Builder(this, channelId)
        } else NotificationCompat.Builder(this)

    private fun getNotification(contentText: String): Notification {
        return mBuilder!!.setContentText(contentText).build()
    }

    override fun onCreate() {
        mScheduledExecutorService = Executors.newScheduledThreadPool(1)
        mManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mBuilder = notificationBuilder
        mBuilder!!.setContentTitle("Count service notification")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
        startForeground(123, getNotification("start notification"))
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        startForeground(123, getNotification("start notification"))
        mScheduledExecutorService!!.scheduleAtFixedRate({

                progress += 5
                sendMessageToUI(progress)


        }, 1000, 200, TimeUnit.MILLISECONDS)
        return START_STICKY
    }

    override fun onDestroy() {
        mScheduledExecutorService!!.shutdownNow()
        mManager!!.cancel(123)
    }

    companion object {
        const val MSG_REGISTER_CLIENT = 1
        const val MSG_SET_PROGRESS_VALUE = 2
        const val MSG_UNREGISTER_CLIENT = 3
        const val MSG_SHOW_TOAST = 1
        private var progress = 0
        private val mClient = ArrayList<Messenger>()
    }
}