package com.example.meuscontatos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meuscontatos.R
import com.example.meuscontatos.model.Contato
import kotlinx.android.synthetic.main.activity_contato_activity.*

class ContatoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato_activity)

        val novoContato = Contato(
            nomeContatoTv.text.toString(),
            telefoneContatoTv.text.toString(),
            emailContatoTv.text.toString()
        )

        val retornoIntent = Intent()
        retornoIntent.putExtra(MainActivity.Extras.EXTRA_CONTATO, novoContato)
        setResult(RESULT_OK, retornoIntent)
        finish()
    }
}