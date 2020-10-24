package com.example.homeworkcomponentsandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }


    fun onfromFragmentAtoFragmentBListner(view: View) {
        navController.navigate(R.id.action_fragmentA2_to_fragmentB)
    }

    fun onfromFragmentAtoFragmentCListner(view: View) {
        navController.navigate(R.id.action_fragmentA2_to_fragmentC)
    }

    fun onfromFragmentBtoFragmentAListner(view: View) {
        navController.navigate(R.id.action_fragmentB_to_fragmentA2)

    }
    fun onfromFragmentBtoFragmentCListner(view: View) {
        navController.navigate(R.id.action_fragmentB_to_fragmentC)
    }

    fun onfromFragmentCtoFragmentBListner(view: View) {
        navController.navigate(R.id.action_fragmentC_to_fragmentB)
    }
    fun onfromFragmentCtoFragmentAListner(view: View) {
        navController.navigate(R.id.action_fragmentC_to_fragmentA2)
    }


}