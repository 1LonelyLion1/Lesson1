package com.example.homeworkcomponentsandroid

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentA : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    /*

    private var intent: Intent? = null

    private  var tvText: TextView? = null
    private var mService: Messenger? = null
    val mMessenger =
        Messenger(IncomingHandler())
    private val sConn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mService = Messenger(service)
            try {
                val msg = Message.obtain(null, 1)
                msg.replyTo = mMessenger
                mService!!.send(msg)
            } catch (e: RemoteException) {
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mService = null
        }
    }

    private inner class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {

                CounterNotificationService.MSG_SET_PROGRESS_VALUE -> tvText?.setText("a")
            }
        }
    }
    */





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view: View =  inflater.inflate(R.layout.fragment_a, container, false)

        /*
        tvText = view.findViewById(R.id.tvCounter)

        val intent = Intent(context, CounterNotificationService::class.java)

        startServiceBtn.setOnClickListener {
            context?.startService(intent)
            context?.bindService(intent, sConn, Context.BIND_AUTO_CREATE)
        }

        stopServiceBtn.setOnClickListener { context?.stopService(intent) }

         */


        return view
    }




    companion object {
        const val MSG_SET_PROGRESS_VALUE = 2
        private var progress = 0

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentA.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentA().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        /*
        try {
            val msg = Message.obtain(null, 3)
            msg.replyTo = mMessenger
            mService!!.send(msg)
        } catch (e: RemoteException) {
        }
        context?.unbindService(sConn)

         */
    }




}