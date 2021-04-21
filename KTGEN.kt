package com.example.ktg

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate
import kotlin.system.exitProcess

class KTGEN: AppCompatActivity() {

    lateinit var toolbar: ActionBar

    lateinit var mr: MediaRecorder

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val i = Intent(this, PanelGłównyEN::class.java)
                    startActivity(i)
                    this.finish()
                }
                R.id.account -> {
                    val i = Intent(this, DaneEN::class.java)
                    startActivity(i)
                    this.finish()
                }
                R.id.chart -> {
                    val i = Intent(this, ChartFragmentEN::class.java)
                    startActivity(i)
                    this.finish()
                }
            }
            false
        }

    //REQUEST_CODE_ENABLE_BT
    private val REQUEST_CODE_ENABLE_BT: Int = 1;

    //REQUEST_CODE_DISCOVERABLE_BT
    private val REQUEST_CODE_DISCOVERABLE_BT: Int = 2;

    //BTadapter
    lateinit var BTadapter: BluetoothAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFeatureInt(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        this.supportActionBar!!.hide()
        setContentView(R.layout.ktg_en)

        // druga część związana z bottomNavigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView_en)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        // włączanie i wyłączanie dźwięku

        var ring = MediaPlayer.create(this, R.raw.ktg)

        findViewById<ImageButton>(R.id.play).isEnabled = true
        findViewById<ImageButton>(R.id.stop).isEnabled = false

        findViewById<ImageButton>(R.id.play).setOnClickListener {
            if (BTadapter.isEnabled) {
                ring.start()
                findViewById<ImageButton>(R.id.play).isEnabled = false
                findViewById<ImageButton>(R.id.stop).isEnabled = true
            }
            if (!BTadapter.isEnabled) {
                Toast.makeText(this, "No BT module running and no paired KTG device", Toast.LENGTH_LONG).show() }            }

        findViewById<ImageButton>(R.id.stop).setOnClickListener {
            if (BTadapter.isEnabled && ring.isPlaying) {
                ring.pause()
                findViewById<ImageButton>(R.id.play).isEnabled = true
                findViewById<ImageButton>(R.id.stop).isEnabled = false}
            if (!BTadapter.isEnabled){
                Toast.makeText(this, "No BT module running and no paired KTG device", Toast.LENGTH_LONG).show() }
        }


        // nagrywanie i zapisywanie dźwięku

        val localDateNow = LocalDate.now().toString()

        val path: String = filesDir.absolutePath + "$localDateNow.mp3"

        mr = MediaRecorder()

        findViewById<ImageButton>(R.id.record).isEnabled = false
        findViewById<ImageButton>(R.id.stoprecord).isEnabled = false


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),111)
        findViewById<ImageButton>(R.id.record).isEnabled = true

        findViewById<ImageButton>(R.id.record).setOnClickListener{
            mr.setAudioSource(MediaRecorder.AudioSource.DEFAULT)
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
            mr.setOutputFile(path)
            mr.prepare()
            mr.start()
            findViewById<ImageButton>(R.id.stoprecord).isEnabled = true
            findViewById<ImageButton>(R.id.record).isEnabled = false
        }

        findViewById<ImageButton>(R.id.stoprecord).setOnClickListener{
            mr.stop()
            findViewById<ImageButton>(R.id.record).isEnabled = true
            findViewById<ImageButton>(R.id.stoprecord).isEnabled = false
        }



        //init BTadapter
        BTadapter = BluetoothAdapter.getDefaultAdapter()
        findViewById<TextView>(R.id.statusBT).text = "Bluetooth is"

        //ikona zgodna ze statusem
        if (BTadapter.isEnabled) {
            findViewById<ImageView>(R.id.imageviewBT).setImageResource(R.drawable.ic_bluetoothon)
        } else {
            findViewById<ImageView>(R.id.imageviewBT).setImageResource(R.drawable.ic_bluetoothoff)
        }

        //włączenie BT
        findViewById<Button>(R.id.turnOnBT).setOnClickListener {
            if (BTadapter.isEnabled) {
                Toast.makeText(this, "ON NOW", Toast.LENGTH_LONG).show() }
            else {
                //włączenie BT
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_CODE_ENABLE_BT);
            }
        }
        //wyłączenie BT
        findViewById<Button>(R.id.turnOffBT).setOnClickListener {
            if (!BTadapter.isEnabled) {
                Toast.makeText(this, "OFF NOW", Toast.LENGTH_LONG).show()
            } else {
                //wyłączenie BT
                BTadapter.disable()
                findViewById<ImageView>(R.id.imageviewBT).setImageResource(R.drawable.ic_bluetoothoff)
                Toast.makeText(this, "Already turned off", Toast.LENGTH_LONG).show()
            }
        }
        //wykrywalność BT
        findViewById<Button>(R.id.wykrywalnoscBT).setOnClickListener {
            if (!BTadapter.isDiscovering) {
                Toast.makeText(this, "Device visible", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
                startActivityForResult(intent, REQUEST_CODE_DISCOVERABLE_BT)
            }
        }
        //lista urządzeń parowalnych
        findViewById<Button>(R.id.parowanieBT).setOnClickListener {
            if (BTadapter.isEnabled) {
                //lista urządzeń
                val urzadzenia = BTadapter.bondedDevices
                var lista = ""
                for (urzadzenie in urzadzenia) {
                    val nazwaU = urzadzenie.name
                    val adresU = urzadzenie
                    lista += ("Device : $nazwaU , $adresU , \n")
                }
                findViewById<TextView>(R.id.textviewBT).text = ("Paired devices \n" + "$lista")
            }
            else {
                Toast.makeText(this, "First, turn on Bluetooth", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_ENABLE_BT ->
                if (resultCode == RESULT_OK) {
                    findViewById<ImageView>(R.id.imageviewBT).setImageResource(R.drawable.ic_bluetoothon)
                    Toast.makeText(this, "Bluetooth turned on", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Bluetooth cannot be turned on", Toast.LENGTH_LONG).show()
                }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            findViewById<ImageButton>(R.id.record).isEnabled = true
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
