package com.example.meuscontatos.adapter

interface OnContatoClickListener {

    fun onClickListener(position: Int){

    }

   abstract fun onContatoClick(position: Int)
}