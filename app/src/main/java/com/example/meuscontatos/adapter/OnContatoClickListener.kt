package com.example.meuscontatos.adapter

interface OnContatoClickListener {

    // Funções adicionadas para ContextMenu
    fun onEditarMenuItemClick(position: Int)
    fun onRemoverMenuItemClick(position: Int)
    fun onContatoClick(position: Int)
}