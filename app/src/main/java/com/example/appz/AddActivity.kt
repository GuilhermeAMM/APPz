package com.example.appz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val tipos = resources.getStringArray(R.array.tipos)

        val adp = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tipos)

        spnTipo.adapter = adp

        btnSalvar.setOnClickListener{

            val atividadeDao = AppZDatabase.getInstance(this)?.atividadeDao()

            val titulo = edtTitulo.text.toString()
            val descricao = edtDescricao.text.toString()
            val data = edtData.text.toString()
            val tempo = edtTempo.text.toString()
            val tipo = spnTipo.selectedItem.toString()


            var distancia = edtDistancia.text.toString().toDouble()
            if(tipo.equals("Academia"))
                distancia = 0.0;

            val a = Atividade(titulo, descricao, data, tempo, tipo, distancia )

            atividadeDao?.salvar(a)

            Toast.makeText(this, "Atividade Salva", Toast.LENGTH_SHORT).show()


        }
    }
}