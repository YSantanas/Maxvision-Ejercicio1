package com.myss.maxvision

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import android.os.Handler
import android.util.Patterns


class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
//recibir variable del main

        // recibir el valor de idioma desde el intent y asignarlo a una variable local
        val idioma = intent.getStringExtra("idioma")

        //VARIABLES QUE SE VAN A USAR
        val botonEnviarR : Button= findViewById(R.id.boton_enviarR)

        // Variables de los id
        val nombreRegistro= findViewById<TextView>(R.id.id_nombreRegistro)
        val nombreApellidosR= findViewById<TextView>(R.id.id_apellidosRegistro)
        val nombreCorreoR= findViewById<TextView>(R.id.id_CorreoRegistro)
        val nombreCuentaR= findViewById<TextView>(R.id.id_numCuentaRegistro)

        //---- SPINNER1 -----

        val SpinnerCarreras= findViewById<Spinner>(R.id.id_Spinner_Carreras)

        //---- Arreglo para el SPINNER ----

// ---- Arreglo para el SPINNER ----
        val opcionesCarreras = if (idioma == "es") {
            arrayOf("Ing. Aeroespacial", "Ing. Civil", "Ing. Geomatica", "Ing. Ambiental","Ing. Geofísica","Ing. Geológica ","Ing. Petrolero","Ing. Minas y Metalurgista","Ing. Computación","Ing. Eléctrica","Ing. Telecomunicaciones","Ing. Mecánica","Ing. Industrial","Ing. Mecatrónica","Ing. Sistemas Biomédicos")
        } else {
            arrayOf("Eng. Aerospace", "Eng. Civil", "Eng. Geomatics", "Eng. Environmental", "Eng. Geophysics", "Eng. Geological", "Eng. Petroleum", "Eng. Mining and Metallurgical", "Eng. Computer", "Eng. Electrical", "Eng. Telecommunications", "Eng. Mechanical", "Eng. Industrial", "Eng. Mechatronics", "Eng. Biomedical Systems")
        }
        // ---- se debe usar adaptador para el SPINNER
        SpinnerCarreras.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesCarreras)

        //--------------------- FIN SPINNER 1 ------------------------

        //---------------------- SPINNER2 -------------------------

        val SpinnerDias= findViewById<Spinner>(R.id.id_Spinner_Dia)

        //---- Arreglo para el SPINNER ----

        val opcionesDias = (1..31).toList()
        // ---- se debe usar adaptador para el SPINNER
        SpinnerDias.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, opcionesDias)


        //--------------------- FIN SPINNER 2 ------------------------

        //---------------------- SPINNER3 -------------------------

        val SpinnerMes= findViewById<Spinner>(R.id.id_Spinner_Mes)

        //---- Arreglo para el SPINNER ----

        val opcionesMes = arrayOf(1, 2, 3, 4,5,6,7,8,9,10,11,12)
        // ---- se debe usar adaptador para el SPINNER
        SpinnerMes.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, opcionesMes)

        //--------------------- FIN SPINNER 3 ------------------------

        //---------------------- SPINNER3 -------------------------

        val SpinnerAnnio= findViewById<Spinner>(R.id.id_Spinner_Annio)

        //---- Arreglo para el SPINNER ----

        val opcionesAnnio = (1950..2023).toList()
        // ---- se debe usar adaptador para el SPINNER
        SpinnerAnnio.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, opcionesAnnio)

        //--------------------- FIN SPINNER 3 ------------------------


        botonEnviarR.setOnClickListener{


            val valorSeleccionadoDia = SpinnerDias.selectedItem.toString().toInt()
            val valorSeleccionadoMes = SpinnerMes.selectedItem.toString().toInt()
            val valorSeleccionadoAn = SpinnerAnnio.selectedItem.toString().toInt()


            //Variables que se deben analizar
            val nombre = nombreRegistro.text.toString()
            val apellidos = nombreApellidosR.text.toString()
            val correo = nombreCorreoR.text.toString()
            val cuenta = nombreCuentaR.text.toString()

            //------------ FIN de variables a analizar -----

            //lista de errores que se deben traducir

            val errorNombre = resources.getString(R.string.error_nombre)
            val errorApellido = resources.getString(R.string.error_apellido)
            val errorCorreo = resources.getString(R.string.error_correo)
            val errorNum = resources.getString(R.string.error_num)

            //----------------------------

            if (!validarCadena(nombre)) {
                //Toast.makeText(this, "El nombre no es válido", Toast.LENGTH_SHORT).show()
                Snackbar.make(botonEnviarR, errorNombre, Snackbar.LENGTH_SHORT).show()

            } else if (!validarCadena(apellidos)){
                //Toast.makeText(this, "Los apellidos no son válidos", Toast.LENGTH_SHORT).show()
                Snackbar.make(botonEnviarR, errorApellido, Snackbar.LENGTH_SHORT).show()
            }
            else if (!validarCorreo(correo)) {
                Snackbar.make(botonEnviarR, errorCorreo, Snackbar.LENGTH_SHORT).show()
            }
            else if (!validarNumeroCuenta(cuenta)) {
                Snackbar.make(botonEnviarR, errorNum, Snackbar.LENGTH_SHORT).show()
            }
            else {

            //se manda la informacion a la activity de perfiles
                val intent= Intent(this, Perfil::class.java)
                //pasamos los datos

                intent.putExtra("nombreR", nombreRegistro.text.toString())
                intent.putExtra("apellidosR", nombreApellidosR.text.toString())
                intent.putExtra("correoR", nombreCorreoR.text.toString())
                intent.putExtra("cuentaR", nombreCuentaR.text.toString())
                intent.putExtra("nombreSpinnerR", SpinnerCarreras.selectedItem.toString())//no estoy segura con el tipo
                intent.putExtra("nombreSpinnerDias", valorSeleccionadoDia)
                intent.putExtra("nombreSpinnerMes", valorSeleccionadoMes)
                intent.putExtra("nombreSpinnerAnnio", valorSeleccionadoAn)
                intent.putExtra("idioma", idioma)//se vuelve a mandar el idioma

                startActivity(intent)
                Snackbar.make(botonEnviarR, "El registro se ha completado exitosamente", Snackbar.LENGTH_SHORT).show()

            }

        }

    }
}

//Funcion de validaciones

fun validarCadena(texto: String): Boolean {
    val patron = Regex("[a-zA-Z]+( [a-zA-Z]+)*") // Permitimos un nombre y un apellido (opcional)
    return patron.matches(texto)
}

fun validarCorreo(correo: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(correo).matches()
}

fun validarNumeroCuenta(numero: String): Boolean {
    return numero.length == 9
}
