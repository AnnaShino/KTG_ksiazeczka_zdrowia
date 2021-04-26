package com.example.ktg

//wymagane importy związane z działaniem aplikacji

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.system.exitProcess


class PanelGłówny: AppCompatActivity()
{

    // ponizszy fragment związany jest z funkcjonalnością bottomNavigation
    // wymaga storzenia dodatkowego folderu res/menu a w nim pliku konfiguracyjnego
    //wymaga pozwoleń w gradle/build.gradle

    lateinit var toolbar: ActionBar

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val i = Intent(this, PanelGłówny::class.java)
                    startActivity(i)
                    this.finish()
                }
                R.id.account -> {
                    val i = Intent(this, Dane::class.java)
                    startActivity(i)
                    this.finish()
                }
                R.id.chart -> {
                    val i = Intent(this, ChartFragment::class.java)
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
        setContentView(R.layout.panel_glowny)

        // druga część związana z bottomNavigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // przyciski funkcyjne
        val button1 = findViewById<Button>(R.id.button20)
        button1.setOnClickListener {
            val i = Intent(this, Wyniki::class.java)
            startActivity(i)
            this.finish()
        }

        val button2 = findViewById<Button>(R.id.button21)
        button2.setOnClickListener {
            val i = Intent(this, KTG::class.java)
            startActivity(i)
            this.finish()
        }

        val button3 = findViewById<Button>(R.id.button22)
        button3.setOnClickListener {
            val i = Intent(this, Konsultacja::class.java)
            startActivity(i)
            this.finish()
        }

        val button4 = findViewById<Button>(R.id.button23)
        button4.setOnClickListener {
            val i = Intent(this, WprowadzanieDanychWynikow::class.java)
            startActivity(i)
            this.finish()
        }

        val button5 = findViewById<Button>(R.id.button24)
        button5.setOnClickListener {
            val i = Intent(this, Istrukcja::class.java)
            startActivity(i)
            this.finish()
        }

    }

    //poniższy fragment odpowiada za wychodzenie z aplikacji za pomocą przycisku funkcyjnego
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
            "Kliknij przycisk POWRÓT ponownie aby wyjść z aplikacji",
            Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}

