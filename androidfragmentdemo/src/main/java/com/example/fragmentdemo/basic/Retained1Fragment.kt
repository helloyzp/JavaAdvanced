package com.example.fragmentdemo.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentdemo.LifeCycleFragment
import com.example.fragmentdemo.R

class Retained1Fragment:LifeCycleFragment() {

    lateinit var data: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

}