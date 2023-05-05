package com.myss.maxvision.contenedor

import android.content.Context
import com.myss.maxvision.R

class ContenedorProvider(private val context: Context) {

    var miLista = listOf<ListaContenedor>(
        ListaContenedor(
            context.getString(R.string.herramienta_horoscopo),
            context.getString(R.string.herramientas_descripcionh),
            R.drawable.horoscopo
        ),
        ListaContenedor(
            context.getString(R.string.herramienta_horoscopochino),
            context.getString(R.string.herramientas_descripcionchino),
            R.drawable.horoscopochino
        ),
        ListaContenedor(
            context.getString(R.string.herramientas_numerologia),
            context.getString(R.string.herramientas_descripcionnum),
            R.drawable.numerologia
        ),
        ListaContenedor(
            context.getString(R.string.herramienta_color),
            context.getString(R.string.herramientas_descripcioncolor),
            R.drawable.color
        ),
        ListaContenedor(
            context.getString(R.string.herramienta_oscuro),
            context.getString(R.string.herramientas_descripcionoscuro),
            R.drawable.oscuro
        )
    )

}