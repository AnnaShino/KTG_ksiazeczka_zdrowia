package com.example.ktg

import android.content.Intent
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.wprowadzanie.*
import java.io.File
import java.time.LocalDate
import kotlin.system.exitProcess

@RequiresApi(VERSION_CODES.O)

class WprowadzanieDanychWynikow : AppCompatActivity() {

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
        setContentView(R.layout.wprowadzanie)

        // druga część związana z bottomNavigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


//automatycznie przypisywana data i godzina dodanych danych

        var data = LocalDate.now()

        var plik_zapisu = filesDir

        val wyniki = File(plik_zapisu, "wyniki")
        wyniki.createNewFile()

        // wyniki

        val button2 = findViewById<Button>(R.id.button321)
        button2.setOnClickListener {
            val M1 = editText41.text.toString()
            val M2 = editText42.text.toString()
            val M3 = editText43.text.toString()
            val M4 = editText44.text.toString()
            val BM1 = editText45.text.toString()
            val BM2 = editText46.text.toString()
            val BM3 = editText47.text.toString()
            val BM4 = editText48.text.toString()
            val BM5 = editText49.text.toString()
            val BM6 = editText411.text.toString()

            wyniki.appendText("DATA/DATE $data\n\n")
            wyniki.appendText("MORFOLOGIA\n")
            if (M1 == "Hb") {
                wyniki.appendText("Hb ---\n")
            }
            else {wyniki.appendText("Hb $M1\n")}
            if (M2 == "Ht") {
                wyniki.appendText("Ht ---\n")
            }
            else {wyniki.appendText("Ht $M2\n")}
            if (M3 == "E") {
                wyniki.appendText("E ---\n")
            }
            else {wyniki.appendText("E $M3\n")}
            if (M4 == "L") {
                wyniki.appendText("L ---\n")
            }
            else {wyniki.appendText("L $M4\n")}
            wyniki.appendText("MOCZ\n")
            if (BM1 == "CW") {
                wyniki.appendText("CW ---\n")
            }
            else {wyniki.appendText("CW $BM1\n")}
            if (BM2 == "B") {
                wyniki.appendText("B ---\n")
            }
            else {wyniki.appendText("B $BM2\n")}
            if (BM3 == "C") {
                wyniki.appendText("C ---\n")
            }
            else {wyniki.appendText("C $BM3\n")}
            if (BM4 == "E") {
                wyniki.appendText("E ---\n")
            }
            else {wyniki.appendText("E $BM4\n")}
            if (BM5 == "L") {
                wyniki.appendText("L ---\n")
            }
            else {wyniki.appendText("L $BM5\n")}
            if (BM6 == "Bakterie") {
                wyniki.appendText("Bakterie ---\n")
            }
            else {wyniki.appendText("Bakterie $BM6\n")}
            Toast.makeText(
                this,
                "DANE WPROWADZONE POPRAWNIE!",
                Toast.LENGTH_SHORT
            ).show()
        }


            // dane podstawowe
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

            val button1 = findViewById<Button>(R.id.button123)
            button1.setOnClickListener {
                val imie = editText1.text.toString()
                val nazwisko = editText2.text.toString()
                val urodziny = editText3.text.toString()
                val narodowosc = editText4.text.toString()
                val wzrost = editText5.text.toString()
                val waga = editText6.text.toString()
                val leki = editText7.text.toString()
                val uczulenia = editText8.text.toString()
                val grupa = editText9.text.toString()
                val sos = editText10.text.toString()
                val badanie = editText11.text.toString()

                if (imie != "Imię") {
                    imiePL.delete()
                    imiePL.createNewFile()
                    imiePL.appendText("IMIĘ/NAME\n$imie")
                }
                if (nazwisko != "Nazwisko") {
                    nazwiskoPL.delete()
                    nazwiskoPL.createNewFile()
                    nazwiskoPL.appendText("NAZWISKO/LASTNAME\n$nazwisko")
                }
                if (urodziny != "Data Urodzenia") {
                    dataPL.delete()
                    dataPL.createNewFile()
                    dataPL.appendText("DATA URODZENIA/BIRTHDAY [DD-MM-YYYY]\n$urodziny")
                }
                if (narodowosc != "Narodowość") {
                    narodowoscPL.delete()
                    narodowoscPL.createNewFile()
                    narodowoscPL.appendText("NARODOWOSC/NATIONALITY\n$narodowosc")
                }
                if (wzrost != "Wzrost") {
                    wzrostPL.delete()
                    wzrostPL.createNewFile()
                    wzrostPL.appendText("WZROST/HEIGHT [CM]\n$wzrost")
                }
                if (waga != "Waga") {
                    wagaPL.delete()
                    wagaPL.createNewFile()
                    wagaPL.appendText("WAGA/WEIGHT [KG]\n$waga")
                }
                if (leki != "Leki przyjmowane na stałe") {
                    lekiPL.delete()
                    lekiPL.createNewFile()
                    lekiPL.appendText("LEKI/MEDICINES\n$leki")
                }
                if (uczulenia != "Uczulenia") {
                    uczuleniaPL.delete()
                    uczuleniaPL.createNewFile()
                    uczuleniaPL.appendText("UCZULENIA/ALLERGIES\n$uczulenia")
                }
                if (grupa != "Grupa krwii") {
                    grupaPL.delete()
                    grupaPL.createNewFile()
                    grupaPL.appendText("GRUPA KRWI/BLOOD GROUP\n$grupa")
                }
                if (sos != "Numer kontaktowy SOS") {
                    sosPL.delete()
                    sosPL.createNewFile()
                    sosPL.appendText("NUMER SOS/EMERGENCY NUMBER\n$sos")
                }
                if (badanie != "Kolejne badanie DD/MM/RRRR") {
                    badaniePL.delete()
                    badaniePL.createNewFile()
                    badaniePL.appendText("KONTROLA/CONTROL\n$badanie")
                }
                Toast.makeText(
                    this,
                    "DANE WPROWADZONE POPRAWNIE!",
                    Toast.LENGTH_SHORT
                ).show()
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
                "Kliknij przycisk POWRÓT ponownie aby wyjść z aplikacji",
                Toast.LENGTH_SHORT
            ).show()

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        }
    }
