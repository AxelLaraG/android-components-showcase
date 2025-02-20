package com.example.evaluacionpractica

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Progress : Fragment() {

    private lateinit var progressBar: ProgressBar
    private var progress = 0
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.frag_progress, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        startProgress()
        return view
    }

    private fun startProgress() {
        coroutineScope.launch {
            while (progress < 100) {
                delay(500)
                progress += 5
                progressBar.progress = progress
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}