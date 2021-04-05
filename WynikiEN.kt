package com.example.ktg

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileInputStream
import kotlin.system.exitProcess

class WynikiEN: AppCompatActivity()  {

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
        setContentView(R.layout.wyniki_en)

        // druga część związana z bottomNavigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView_en)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        var plik_zapisu = filesDir
        val wyniki = File(plik_zapisu, "wyniki")
        wyniki.createNewFile()
        val pokaz = FileInputStream(wyniki).bufferedReader().use { it.readText() }.toString()
        val textView: TextView = findViewById(R.id.textView3)
        textView.movementMethod = ScrollingMovementMethod()
        textView.text = pokaz
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
                "Click the BACK button again to exit the application ",
                Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}