package com.example.meuscontatos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meuscontatos.R
import com.example.meuscontatos.model.Contato
import kotlinx.android.synthetic.main.activity_contato.*

class ContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)

       salvarBt.setOnClickListener{
            val novoContato = Contato(
                nomeContatoEt.text.toString(),
                telefoneContatoEt.text.toString(),
                emailContatoEt.text.toString()
            )

           val retornoIntent = Intent()
           retornoIntent.putExtra(MainActivity.Extras.EXTRA_CONTATO, novoContato)
           setResult(RESULT_OK, retornoIntent)
           finish()
        }
    }
}