package com.example.ktg

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


class PanelGłównyEN: AppCompatActivity() {

    // ponizszy fragment związany jest z funkcjonalnością bottomNavigation
    // wymaga storzenia dodatkowego folderu res/menu a w nim pliku konfiguracyjnego
    //wymaga pozwoleń w gradle/build.gradle
    //wymaga folderu z elementami typu Fragments
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
        setContentView(R.layout.panel_glowny_en)

        // druga część związana z bottomNavigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView_en)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val button1 = findViewById<Button>(R.id.button10)
        button1.setOnClickListener {
            val i = Intent(this, WynikiEN::class.java)
            startActivity(i)
            this.finish()
        }
        
        val button2 = findViewById<Button>(R.id.button11)
        button2.setOnClickListener {
            val i = Intent(this, KTGEN::class.java)
            startActivity(i)
            this.finish()
        }

        val button3 = findViewById<Button>(R.id.button12)
        button3.setOnClickListener {
            val i = Intent(this, KonsultacjaEN::class.java)
            startActivity(i)
            this.finish()
        }


        val button5 = findViewById<Button>(R.id.button14)
        button5.setOnClickListener {
            val i = Intent(this, IstrukcjaEN::class.java)
            startActivity(i)
            this.finish()
        }
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
