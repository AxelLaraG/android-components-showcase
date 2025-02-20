package com.example.evaluacionpractica

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainMenu : AppCompatActivity() {

    private lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    private var photoUri: Uri? = null
    private var isFlashOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)


        val btnInfo = findViewById<ImageButton>(R.id.btn_info)
        val btnFlash = findViewById<ImageButton>(R.id.btn_lantern)
        val btnExit = findViewById<ImageButton>(R.id.btn_exit)
        val btnTV = findViewById<ImageButton>(R.id.btn_tv)
        val btnGallery = findViewById<ImageButton>(R.id.btn_gallery)
        val btnTabs = findViewById<ImageButton>(R.id.btn_tab)
        val btnCam = findViewById<ImageButton>(R.id.btn_cam)

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && photoUri != null) {
                // Iniciar la actividad de la cámara con la foto capturada
                val intent = Intent(this, MainCamera::class.java)
                intent.putExtra("photoUri", photoUri.toString())
                startActivity(intent)
            }
        }

        btnTabs.setOnClickListener {
            val intent = Intent(this, MainTabs::class.java)
            startActivity(intent)
        }

        btnCam.setOnClickListener {
            capturePhoto()
        }

        btnInfo.setOnClickListener{
            showPopUp(it)
        }

        btnFlash.setOnClickListener{
            flashOn()
        }

        btnExit.setOnClickListener{
            exitConfirmation()
        }

        btnTV.setOnClickListener {
            val intent = Intent(this, MainTV::class.java)
            startActivity(intent)
        }

        btnGallery.setOnClickListener {
            val intent = Intent(this, MainGallery::class.java)
            startActivity(intent)
        }

    }

    private fun capturePhoto() {
        val photoFile = createImageFile()
        val uri = FileProvider.getUriForFile(
            this,
            "com.example.evaluacionpractica.fileprovider",
            photoFile
        )

        photoUri = uri

        photoUri?.let {
            cameraLauncher.launch(it)
        }
    }


    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir!!)
    }

    private fun flashOn (){
        val camera = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val camId  = camera.cameraIdList[0]

        try {
            isFlashOn = !isFlashOn
            camera.setTorchMode(camId, isFlashOn)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun showPopUp(view:View){
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.activity_popup,null)

        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        val btnClose = popupView.findViewById<Button>(R.id.btnCloseP)

        btnClose.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(view, android.view.Gravity.CENTER, 0, 0)
    }

    private fun exitConfirmation(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Salir de la app?")
        builder.setMessage("¿Seguro que desea salir de la app?")

        builder.setPositiveButton("Sí") { _, _ ->
            Toast.makeText(this, "Terminando Aplicación!", Toast.LENGTH_SHORT).show()
            android.os.Handler().postDelayed({
                finishAffinity()
                System.exit(0)
            },2000)
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}