package com.example.mapmarker.baseComponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity
    <VB: ViewBinding, VM: ViewModel>: AppCompatActivity() {

    abstract val viewModel: VM
    lateinit var binding: VB

    abstract fun subscribe()
    abstract fun setup()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getInflatedLayout(layoutInflater))
        setup()
        subscribe()
    }

    abstract fun attachBinding(layoutInflater: LayoutInflater): VB

    private fun getInflatedLayout(inflater: LayoutInflater): View {
        binding = attachBinding(inflater)
        return binding.root
    }

}