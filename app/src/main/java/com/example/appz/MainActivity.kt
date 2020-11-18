package com.example.appz

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
/*


      ********PROFESSOR NÃO CONSEGUI FAZER A PARTE PARA EXCLUIR O REGISTRO DA LISTA AO CLICAR E SEGURAR, ACRETIDO
              QUE SEJA ALGO MAIS OU MENOS QUE NEM ESSE CÓDIGO MAS ESTÁ DANDO ERRO NO lstAtividades.remove ***********************




        lstAtividades.setOnItemLongClickListener { paret, view, position, id ->
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle("Atenção!")
            dialog.setMessage("Deseja realmente excluir?")
            dialog.setPositiveButton("Sim") { _: DialogInterface, _: Int ->
                var atividade = lstAtividades.getItemAtPosition(position) as Atividade

                AppZDatabase.getInstance(this, )!!.atividadeDao().excluir(atividade)
                dialog.setNegativeButton("Não", null)
                carregarLista("nome", "ASC")
            }

            dialog.show()
            return@setOnItemLongClickListener true

        }


 */

        lstAtividades.setOnItemClickListener { parent, view, position, id ->

            val atividadeClicada = lstAtividades.getItemAtPosition(position) as Atividade

            val i = Intent(this, DadosActivity::class.java)

            i.putExtra("atividade", atividadeClicada)


            startActivity(i)



            }

        var tipos = resources.getStringArray(R.array.tipos)

        var tp = arrayListOf<String>()

        tp.add("Todos")
        tp.addAll(tipos)


        val adp = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tp)

        spnFiltro.adapter = adp

        spnFiltro.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected( parent: AdapterView<*>, view: View, position: Int, id: Long) {

                filtrarLista(spnFiltro.selectedItem.toString())

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menuNormal){

            carregarLista("normal")

        }

        if(item.itemId == R.id.menuData){

            carregarLista("data")

        }

        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()

        carregarLista("normal")
    }

    private fun filtrarLista(tipo: String) {
        val atividadeDAO = AppZDatabase.getInstance(this)?.atividadeDao()

        val listaAtividades: List<Atividade>

        if(tipo.equals("Todos")) {

            listaAtividades = atividadeDAO!!.listar()

        } else {

            listaAtividades = atividadeDAO!!.listarPorTipo(tipo)
        }


        val adp = ArrayAdapter<Atividade>(this, android.R.layout.simple_list_item_1, listaAtividades)

        lstAtividades.adapter = adp
    }

    private fun carregarLista(ordem: String) {
        val atividadeDAO = AppZDatabase.getInstance(this)?.atividadeDao()

        val listaAtividades: List<Atividade>

        if(ordem.equals("normal")) {
            listaAtividades = atividadeDAO!!.listar()

        }

        else if(ordem.equals("data")) {
            listaAtividades = atividadeDAO!!.listarData()

        }

        else{

            listaAtividades = atividadeDAO!!.listar()
        }

        val adp = ArrayAdapter<Atividade>(this, android.R.layout.simple_list_item_1, listaAtividades)

        lstAtividades.adapter = adp
    }
}

