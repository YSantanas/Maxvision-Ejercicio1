package com.myss.maxvision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myss.maxvision.adaptador.adaptador_contenedor
import com.myss.maxvision.contenedor.ContenedorProvider

class Pruebas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebas)
        initRecyclerView()
    }

    fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.contenedorPlantilla)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptador_contenedor(ContenedorProvider(this).miLista)
    }

}