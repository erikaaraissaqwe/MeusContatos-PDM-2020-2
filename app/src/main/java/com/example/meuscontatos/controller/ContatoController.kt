package com.example.meuscontatos.controller

import com.example.meuscontatos.model.Contato
import com.example.meuscontatos.model.ContatoDao
import com.example.meuscontatos.model.ContatoFirebase
import com.example.meuscontatos.view.MainActivity

class ContatoController(mainActivity: MainActivity) {
    val contatoDao: ContatoDao
    init {
        //contatoDao = ContatoSqlite(mainActivity)
        //contatoDao = ContatoSharedPreferences(mainActivity)
        contatoDao = ContatoFirebase()
    }

    fun insereContato(contato: Contato) = contatoDao.createContato(contato)
    fun buscaContato(nome: String) = contatoDao.readContato(nome)
    fun buscaContatos() = contatoDao.readContatos()
    fun atualizaContato(contato: Contato) = contatoDao.updateContato(contato)
    fun removeContato(nome: String) = contatoDao.deleteContato(nome)
}