package com.example.evaluacionpractica

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainGallery : AppCompatActivity() {

    private lateinit var imageList: RecyclerView
    private lateinit var floatBtn: FloatingActionButton
    private lateinit var btnBack: Button
    private val selectedImages = mutableListOf<Uri>()

    @SuppressLint("NotifyDataSetChanged")
    private val pickImagesLauncher = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        if (uris.size in 10..15) {
            selectedImages.clear()
            selectedImages.addAll(uris)
            imageList.adapter?.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "Debe seleccionar entre 10 y 15 imágenes", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        imageList = findViewById(R.id.imageList)
        floatBtn = findViewById(R.id.floatBtn)
        btnBack = findViewById(R.id.btn_backG)

        imageList.layoutManager = LinearLayoutManager(this)
        imageList.adapter = ImageAdapter(selectedImages)

        floatBtn.setOnClickListener {
            pickImagesLauncher.launch("image/*")
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}