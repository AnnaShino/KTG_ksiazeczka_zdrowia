package com.example.ktg

//wymagane importy związane z działaniem aplikacji

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.ktg.R.id.weryfikacja_button
import java.util.concurrent.Executor
import kotlin.system.exitProcess

class BiometryczneLogowanie : AppCompatActivity() {

    // inicjalizacje biometryki
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        
        // poniższy fragment pozwala na usunięcie nieestetycznego górnego z nazwą layautu
        super.onCreate(savedInstanceState)
        window.setFeatureInt(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        this.supportActionBar!!.hide()
        setContentView(R.layout.biometryczne)

        //inicjalizacja biometrii
        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(applicationContext, "Błąd Weryfikacji : $errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(applicationContext, "Weryfikacja udana!", Toast.LENGTH_SHORT).show()
                val i = Intent(applicationContext, PanelGłówny::class.java)
                startActivity(i)
                finish()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(applicationContext, "Weryfikacja nieudana.", Toast.LENGTH_SHORT).show()
            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Autoryzacja Biometryczna")
                .setSubtitle("Logowanie przy pomocy odcisku palca")
                .setNegativeButtonText("..").build()




        // autoryzacja
        val button2 = findViewById<Button>(weryfikacja_button)
        button2.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
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
