package com.myss.maxvision.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myss.maxvision.contenedor.ListaContenedor
import com.myss.maxvision.R


//se necesita recibir el provider
class adaptador_contenedor(private val miLista: List<ListaContenedor>) :RecyclerView.Adapter<holder_contenedor>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder_contenedor {
//los item de layout se usan aqui
        val inflar=LayoutInflater.from(parent.context)
        return holder_contenedor(inflar.inflate(R.layout.item_lista, parent, false))
    }

    override fun onBindViewHolder(holder: holder_contenedor, position: Int) {

        val item= miLista[position]
        holder.render(item)

    }

    override fun getItemCount(): Int = miLista.size



}