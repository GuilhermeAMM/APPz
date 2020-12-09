package com.example.appz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter



class ListaAdapter(private val itens: List<Atividade>): BaseAdapter(){
   overide fun getview(position: Int, convertView: View?, parent: ViewGroup?) View{

        val item = itens[position]
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflater(R.layout.item_lista, parent, attachToRoot false)

        view.tipo_atividade.text = item.tipo
        view.titulo_atividade.text = item.titulo
        view.desc_atividade.text = item.descricao
        view.data_atividade.text = item.data

        return view

    }

    override fun getItem(position: Int): Any {
        return itens[position]
    }

    override fun getItemId(position: Int) Long {
        return itens[position].id.toLong()
    }

    override fun getCount(): Int {
        return itens.size
    }

}
