package com.example.evaluacionpractica

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainCamera : AppCompatActivity() {

    private var photoUri: Uri? = null  // Variable para la URI de la imagen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnReturn = findViewById<Button>(R.id.btn_backC)

        val uriString = intent.getStringExtra("photoUri")
        photoUri = Uri.parse(uriString)

        photoUri?.let {
            imageView.setImageURI(it)
        }

        btnSave.setOnClickListener {
            photoUri?.let {
                saveImageToGallery(it)
            } ?: run {
                Toast.makeText(this, "No hay imagen para guardar", Toast.LENGTH_SHORT).show()
            }
        }

        btnReturn.setOnClickListener {
            finish()
        }
    }

    private fun saveImageToGallery(imageUri: Uri) {
        val resolver = contentResolver
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val galleryUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if (galleryUri != null) {
            resolver.openOutputStream(galleryUri)?.use { outputStream ->
                contentResolver.openInputStream(imageUri)?.use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            Toast.makeText(this, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show()
        }
    }
}

