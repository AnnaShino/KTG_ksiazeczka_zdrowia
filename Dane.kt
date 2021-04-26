package com.example.ktg

//wymagane importy związane z działaniem aplikacji
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileInputStream
import kotlin.system.exitProcess

class Dane: AppCompatActivity()  {

    //inicjalizacja paska i działanie przycisków
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        
        // poniższy fragment pozwala na usunięcie nieestetycznego górnego z nazwą layautu
        super.onCreate(savedInstanceState)
        window.setFeatureInt(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        this.supportActionBar!!.hide()
        setContentView(R.layout.dane)



        // tworzenie i odczyt baz danych
        var plik_zapisu = filesDir
        val imiePL = File(plik_zapisu, "imiePL")
        imiePL.createNewFile()
        val nazwiskoPL = File(plik_zapisu, "nazwiskoPL")
        nazwiskoPL.createNewFile()
        val narodowoscPL = File(plik_zapisu, "narodowoscPL")
        narodowoscPL.createNewFile()
        val wzrostPL = File(plik_zapisu, "wzrostPL")
        wzrostPL.createNewFile()
        val wagaPL = File(plik_zapisu, "wagaPL")
        wagaPL.createNewFile()
        val lekiPL = File(plik_zapisu, "lekiPL")
        lekiPL.createNewFile()
        val uczuleniaPL = File(plik_zapisu, "uczulenia")
        uczuleniaPL.createNewFile()
        val grupaPL = File(plik_zapisu, "grupaPL")
        grupaPL.createNewFile()
        val sosPL = File(plik_zapisu, "sosPL")
        sosPL.createNewFile()
        val dataPL = File(plik_zapisu, "dataPL")
        dataPL.createNewFile()
        val badaniePL = File(plik_zapisu, "badaniePL")
        badaniePL.createNewFile()

        val pokaz1 = FileInputStream(imiePL).bufferedReader().use { it.readText()}.toString()
        val pokaz2 = FileInputStream(nazwiskoPL).bufferedReader().use { it.readText()}.toString()
        val pokaz3 = FileInputStream(dataPL).bufferedReader().use { it.readText()}.toString()
        val pokaz4 = FileInputStream(narodowoscPL).bufferedReader().use { it.readText()}.toString()
        val pokaz5 = FileInputStream(wzrostPL).bufferedReader().use { it.readText()}.toString()
        val pokaz6 = FileInputStream(wagaPL).bufferedReader().use { it.readText()}.toString()
        val pokaz7 = FileInputStream(lekiPL).bufferedReader().use { it.readText()}.toString()
        val pokaz8 = FileInputStream(uczuleniaPL).bufferedReader().use { it.readText()}.toString()
        val pokaz9 = FileInputStream(grupaPL).bufferedReader().use { it.readText()}.toString()
        val pokaz10 = FileInputStream(sosPL).bufferedReader().use { it.readText()}.toString()
        val pokaz11= FileInputStream(badaniePL).bufferedReader().use { it.readText()}.toString()

        val textView1: TextView = findViewById(R.id.textView51)
        textView1.text = pokaz1
        val textView2: TextView = findViewById(R.id.textView52)
        textView2.text = pokaz2
        val textView3: TextView = findViewById(R.id.textView53)
        textView3.text = pokaz3
        val textView4: TextView = findViewById(R.id.textView54)
        textView4.text = pokaz4
        val textView5: TextView = findViewById(R.id.textView55)
        textView5.text = pokaz5
        val textView6: TextView = findViewById(R.id.textView56)
        textView6.text = pokaz6
        val textView7: TextView = findViewById(R.id.textView57)
        textView7.text = pokaz7
        val textView8: TextView = findViewById(R.id.textView58)
        textView8.text = pokaz8
        val textView9: TextView = findViewById(R.id.textView59)
        textView9.text = pokaz9
        val textView10: TextView = findViewById(R.id.textView60)
        textView10.text = pokaz10
        val textView11: TextView = findViewById(R.id.textView61)
        textView11.text = pokaz11

        // druga część związana z bottomNavigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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

