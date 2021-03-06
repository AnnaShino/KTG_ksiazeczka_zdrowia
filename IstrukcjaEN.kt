package com.example.ktg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.instrukcja_en.*
import kotlin.system.exitProcess

class IstrukcjaEN : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_en -> {
                    val i = Intent(this, PanelGłównyEN::class.java)
                    startActivity(i)
                    this.finish()
                }
                R.id.account_en -> {
                    val i = Intent(this, DaneEN::class.java)
                    startActivity(i)
                    this.finish()
                }
                R.id.chart_en -> {
                    val i = Intent(this, ChartFragmentEN::class.java)
                    startActivity(i)
                    this.finish()
                }
            }
            false
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        // poniższy fragment pozwala na usunięcie nieestetycznego górnego z nazwą layautu
        super.onCreate(savedInstanceState)
        window.setFeatureInt(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        this.supportActionBar!!.hide()
        setContentView(R.layout.instrukcja_en)

        val videoView: VideoView = videoView434

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        val button1 = findViewById<Button>(R.id.button430)
        button1.setOnClickListener {
            val UriWeb: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.pregnabit)
                videoView.setVideoURI(UriWeb)
            videoView.requestFocus()
            videoView.start()
        }

        val button2 = findViewById<Button>(R.id.button431)
        button2.setOnClickListener {
            val UriWeb: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.sono)
                videoView.setVideoURI(UriWeb)
            videoView.requestFocus()
            videoView.start()
        }

        val button3 = findViewById<Button>(R.id.button432)
        button3.setOnClickListener {
            val UriWeb: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.care)
            videoView.setVideoURI(UriWeb)
            videoView.requestFocus()
            videoView.start()
        }

        val button4 = findViewById<Button>(R.id.button433)
        button4.setOnClickListener {
            val UriWeb: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.doppler)
            videoView.setVideoURI(UriWeb)
            videoView.requestFocus()
            videoView.start()
        }

        // druga część związana z bottomNavigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView_en)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
            finish()
            exitProcess(0)
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(
                this,
                "Click the BACK button again to exit the application",
                Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}