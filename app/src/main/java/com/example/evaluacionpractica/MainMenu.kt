package com.example.evaluacionpractica

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainMenu : AppCompatActivity() {

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

        btnCam.setOnClickListener {
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