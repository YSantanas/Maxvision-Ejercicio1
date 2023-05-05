package com.myss.maxvision

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {

    var idioma: String = "es"//Idioma predeterminado

    private  lateinit var switch: Switch
    private  lateinit var cambioMensaje: TextView
    private  lateinit var cambioTitulo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creamos las varibales que se usaran en los botones
        val btn_prueba: Button = findViewById(R.id.btn_probar)
        val btn_registrarse: Button = findViewById(R.id.btn_registro)
        //llamamos a nuestra escucha
        btn_prueba.setOnClickListener {

            val intent1: Intent= Intent(this, Pruebas::class.java)
            startActivity(intent1)
        }

        btn_registrarse.setOnClickListener {
            val intent2: Intent= Intent(this, Registro::class.java)
            intent2.putExtra("idioma", idioma) // agregar el valor de idioma al intent para que pueda ser usada por la otra actividad
            startActivity(intent2)
        }

        //Cambio de idioma
        switch= findViewById(R.id.id_cambio)
        cambioMensaje=findViewById(R.id.textCambio)
        cambioTitulo= findViewById(R.id.textoInicio)

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Snackbar.make(findViewById(android.R.id.content), "Cambio a Inglés", Snackbar.LENGTH_SHORT)
                    .setActionTextColor(ContextCompat.getColor(this, R.color.color_btn))
                    .setAnchorView(R.id.id_cambio)
                    .setBackgroundTint(ContextCompat.getColor(this, R.color.color_azul))
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                    .show()

                actualizarRecursos("en", btn_prueba, btn_registrarse)
                idioma = "en"
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Cambio a Español", Snackbar.LENGTH_SHORT)
                    .setActionTextColor(ContextCompat.getColor(this, R.color.color_btn))
                    .setAnchorView(R.id.id_cambio)
                    .setBackgroundTint(ContextCompat.getColor(this, R.color.color_rojo))
                    .setTextColor(ContextCompat.getColor(this, R.color.color_amarillo))
                    .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                    .show()

                actualizarRecursos("es", btn_prueba, btn_registrarse)
                idioma = "es"
            }
        }


    }

    fun actualizarRecursos(idioma:String, btn_prueba: Button, btn_registro:Button){
        val recursos = resources
        val displayMetrics= resources.displayMetrics
        val configuracion = resources.configuration

        configuracion.setLocale(Locale(idioma))
        recursos.updateConfiguration(configuracion,displayMetrics)
        configuracion.locale = Locale(idioma)
        recursos.updateConfiguration(configuracion,displayMetrics)


        btn_prueba.text = getString(R.string.text_btn_Prueba)
        btn_registro.text= getString(R.string.text_btn_Registro)
        switch.text = recursos.getString(R.string.language)
        cambioMensaje.text =recursos.getString(R.string.greetin)
        cambioTitulo.text=getString(R.string.inicio_Bienvenido)

    }

}