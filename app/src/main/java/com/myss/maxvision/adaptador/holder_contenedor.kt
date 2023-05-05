package com.myss.maxvision.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myss.maxvision.contenedor.ListaContenedor
import com.myss.maxvision.R
import com.bumptech.glide.Glide


class holder_contenedor(view:View):RecyclerView.ViewHolder(view) {
//se accede a los componentes

    val img_plantilla= view.findViewById<ImageView>(R.id.imagenC)
    val titulo_plantilla= view.findViewById<TextView>(R.id.tituloC)
    val descrip_plantilla= view.findViewById<TextView>(R.id.descripC)

    fun render (listaModelo: ListaContenedor){

        titulo_plantilla.text = listaModelo.titulo
        descrip_plantilla.text =listaModelo.descripcion
        img_plantilla.setImageResource(listaModelo.imagen)
    }

}