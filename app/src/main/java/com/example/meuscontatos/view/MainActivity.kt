package com.example.meuscontatos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meuscontatos.R
import com.example.meuscontatos.adapter.ContatosAdapter
import com.example.meuscontatos.adapter.OnContatoClickListener
import com.example.meuscontatos.controller.ContatoController
import com.example.meuscontatos.model.Contato
import com.example.meuscontatos.view.MainActivity.Extras.EXTRA_CONTATO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnContatoClickListener {

    private val NOVO_CONTATO_REQUEST_CODE = 0
    object Extras{
        val EXTRA_CONTATO = "EXTRA_CONTATO"
    }

    private lateinit var contatosList : MutableList<Contato>

    private lateinit var contatosAdapter: ContatosAdapter

    private lateinit var contatosLayoutManager : LinearLayoutManager

    private lateinit var contatosController : ContatoController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contatosController = ContatoController(this)
        contatosList = contatosController.buscaContatos()

        contatosLayoutManager = LinearLayoutManager(this)

        contatosAdapter = ContatosAdapter(contatosList, this)

        listaContatosRv.adapter = contatosAdapter
        listaContatosRv.layoutManager = contatosLayoutManager
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NOVO_CONTATO_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val novoContato = data.getParcelableExtra<Contato>(EXTRA_CONTATO)
            if (novoContato != null) {
                contatosController.insereContato(novoContato)

                contatosList.add(novoContato)
                contatosAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == R.id.novoContatoMi){
            val novoContatoIntent = Intent(this, ContatoActivity::class.java)
            startActivityForResult(novoContatoIntent, NOVO_CONTATO_REQUEST_CODE)
            true
        }
        else
            false



    fun onContatoClick(position: Int) {
        val contato : Contato = contatosList[position]
        Toast.makeText(this, contato.nome, Toast.LENGTH_SHORT).show()
    }

    override fun onClickListener(position: Int) {
        val contato : Contato = contatosList[position]
        Toast.makeText(this, contato.nome, Toast.LENGTH_SHORT).show()
    }
}