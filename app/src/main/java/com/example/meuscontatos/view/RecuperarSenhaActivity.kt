package com.example.meuscontatos.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.meuscontatos.databinding.ActivityRecuperarSenhaBinding
import com.example.meuscontatos.utils.AutenticadorFirebase

class RecuperarSenhaActivity : AppCompatActivity() {
    // View binding
    private lateinit var activityRecuperarSenhaBinding: ActivityRecuperarSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instanciando view binding
        activityRecuperarSenhaBinding = ActivityRecuperarSenhaBinding.inflate(layoutInflater)

        setContentView(activityRecuperarSenhaBinding.root)
    }

    fun onClick(view: View) {
        if (view == activityRecuperarSenhaBinding.enviarEmailBt) {
            val email = activityRecuperarSenhaBinding.emailRecuperacaoSenhaEt.text.toString()
            if (email.isNotBlank() && email.isNotEmpty()) {
                AutenticadorFirebase.firebaseAuth.sendPasswordResetEmail(email)
                Toast.makeText(this, "E-mail de recuperação de senha enviado para $email.", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}