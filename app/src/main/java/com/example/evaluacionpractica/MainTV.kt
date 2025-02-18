package com.example.evaluacionpractica

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainTV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_television)

        val video1 = findViewById<ImageButton>(R.id.video1)
        val video2 = findViewById<ImageButton>(R.id.video2)
        val video3 = findViewById<ImageButton>(R.id.video3)
        val btnReturn = findViewById<Button>(R.id.btn_back)

        video1.setImageBitmap(getVideoThumbnail(R.raw.duck))
        video2.setImageBitmap(getVideoThumbnail(R.raw.patrick))
        video3.setImageBitmap(getVideoThumbnail(R.raw.dance))

        video1.setOnClickListener {
            playVideo(R.raw.duck)
        }

        video2.setOnClickListener {
            playVideo(R.raw.patrick)
        }

        video3.setOnClickListener {
            playVideo(R.raw.dance)
        }

        btnReturn.setOnClickListener {
            finish()
        }
    }

    private fun getVideoThumbnail(videoResource: Int): Bitmap? {
        val retriever = MediaMetadataRetriever()
        val fileDescriptor = resources.openRawResourceFd(videoResource)

        return try {
            retriever.setDataSource(fileDescriptor.fileDescriptor, fileDescriptor.startOffset, fileDescriptor.length)
            retriever.frameAtTime
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            retriever.release()
            fileDescriptor.close()
        }
    }

    private fun playVideo(video:Int){
        val intent = Intent (this, VideoPlayer::class.java)
        intent.putExtra("video_resource", video)
        startActivity(intent)
    }
}