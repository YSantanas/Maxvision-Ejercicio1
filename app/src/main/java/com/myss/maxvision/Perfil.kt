package com.myss.maxvision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent

class Perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        val intent = intent
        //recibiendo datos

        var nombrePerfil= intent.getStringExtra("nombreR")
        var apellidoPerfil= intent.getStringExtra("apellidosR")
        var correoPerfil= intent.getStringExtra("correoR")
        var cuentaPerfil= intent.getStringExtra("cuentaR")
        var SpinCarrera= intent.getStringExtra("nombreSpinnerR")
        var SpinDias= intent.getIntExtra("nombreSpinnerDias",0)
        var SpinMes= intent.getIntExtra("nombreSpinnerMes",0)
        var SpinAnnio= intent.getIntExtra("nombreSpinnerAnnio",0)


        val NombreCompleto= findViewById<TextView>(R.id.id_resultadoNombre)

        val Resultado3= findViewById<TextView>(R.id.id_resultadoCorreo)
        val Resultado4= findViewById<TextView>(R.id.id_resultadoNumCuenta)
        val Resultado5= findViewById<TextView>(R.id.id_resultadoCarrera)//aun no se pasa
        val fecha= findViewById<TextView>(R.id.id_resultadoFecha)//aun no se pasa
        val Resultado9= findViewById<TextView>(R.id.id_resultadoEdad)
        val Resultado10= findViewById<TextView>(R.id.id_resultado_horoscopo1)
        val Resultado11= findViewById<TextView>(R.id.id_resultado_horoscopo2)
        val Resultado12= findViewById<TextView>(R.id.id_resultado_elemento)
        val ImagenCarrera= findViewById<ImageView>(R.id.id_img_carrera)
        val ImagenHoroscopo= findViewById<ImageView>(R.id.id_img_horoscopo2)
        val ImagenHoroscopoChino= findViewById<ImageView>(R.id.id_img_horoscopoChino)
        val ImagenHoroscopoElemento= findViewById<ImageView>(R.id.id_img_horoscopoChinoElemento)


        NombreCompleto.text="${nombrePerfil} ${apellidoPerfil}"
        Resultado3.text=": ${correoPerfil}"
        Resultado4.text=": ${cuentaPerfil}"
        Resultado5.text=": ${SpinCarrera}"
        fecha.text=": ${SpinDias} / ${SpinMes} /${SpinAnnio}"


        //--------------------------------
        //Codigo de la imagen de la carrera
        //--------------------------------

// Obtener el identificador de la imagen correspondiente
        val idImagenCarrera = obtenerImagenCarrera(SpinCarrera.toString(),intent)

// Mostrar la imagen en el ImageView
        if (idImagenCarrera != null) {
            ImagenCarrera.setImageResource(idImagenCarrera)
        }



        //------------------->>>>>>>>>>> CÓDIGO DE LA EDAD
        //------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        val dia: Int = intent.getIntExtra("nombreSpinnerDias",0)
        val mes: Int = intent.getIntExtra("nombreSpinnerMes",0)
        val annio: Int = intent.getIntExtra("nombreSpinnerAnnio",0)


        if (dia != 0 && mes != 0 && annio != 0) {// no ser nulo


            println("\n ----> Introduce tu día de nacimiento: \n")
            if (dia in 1..31) {
                println("Tu dia es ${dia}")

            } else {
                println("El valor no es valido")
            }

            println("\n ----> Introduce tu mes de nacimiento: \n")


            if (mes in 1..12) {// el mes debe estar en este rango además de no ser nulo
                println("Tu mes es ${mes}")


            } else {
                println("El valor no es valido")
            }



            println("\n ----> Introduce tu año de nacimiento: \n")


            if (annio in 1900..2023) {
                println("Tu año es ${annio}")

                //Si el valor es valido cotejaremos la respuesta

            } else {
                println("El valor no es valido")
            }


            val cadenaEdad=edades(dia,mes,annio) //recupera la variable edadCadena
            Resultado9.text= cadenaEdad

//Aries,Tauro ,Géminis,Cáncer,Leo,Virgo,Libra,Escorpio,Sagitario,Capricornio,Acuario,Piscis
            val horoscopo= horoscopo(dia,mes)
            Resultado10.text= horoscopo
            //--------------------------
            //------IMAGEN DEL HOROSCOPO
            //--------------------------

// Obtener el identificador de recurso de la imagen correspondiente
            val idImagen = obtenerImagenHoroscopo(horoscopo)

// Mostrar la imagen en el ImageView
            if (idImagen != null) {
                ImagenHoroscopo.setImageResource(idImagen)
            }

            //chinoHoroscopo(annio)
            val horoscopoChino= chinoHoroscopo(annio, intent)
            Resultado11.text= horoscopoChino

            //----------- IMAGEN DEL HOROSCOPO CHINO

            // Obtener el identificador de recurso de la imagen correspondiente
            val idImagen2 = obtenerImagenHoroscopoChino(horoscopoChino,intent)

// Mostrar la imagen en el ImageView
            if (idImagen2 != null) {
                ImagenHoroscopoChino.setImageResource(idImagen2)
            }

            val horoscopoChinoElemento= elementoChino(annio)
            Resultado12.text= horoscopoChinoElemento


            //----------- IMAGEN DEL HOROSCOPO CHINO CON ELEMENTOS

            // Obtener el identificador de recurso de la imagen correspondiente
            val idImagen3 = obtenerImagenElemento(horoscopoChinoElemento)

// Mostrar la imagen en el ImageView
            if (idImagen3 != null) {
                ImagenHoroscopoElemento.setImageResource(idImagen3)
            }


        } else {
            println("El valor es NULO")
        }

    }

}

//----------------------------------------------
//-------------- Edad ---------------------
//----------------------------------------------
fun edades(dia:Int,mes:Int,annio:Int): String{

    //-----------------
    //validando DIAS
    //-----------------

    //---------- ENERO
    if (dia in 1..31 && mes==1) {
        println("VALIDADO ENERO")

        val resultado = validado(dia,mes,annio).toString()
        return resultado


    }

    //-------------- FEBRERO---COMPRANDO TAMBIEN QUE SEA BISIESTO
    else if(dia in 1..28 && mes==2 || dia==29 && annio%4==0 && annio%100!=0 && annio%400!=0 || annio%4==0 && annio%100==0 && annio%400==0){
        println("VALIDADO FEBRERO")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }
    //-------------- MARZO
    else if(dia in 1..31 && mes==3){
        println("VALIDADO MARZO")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }
    //-------------- Abril
    else if(dia in 1..29 && mes==4){
        println("VALIDADO ABRIL")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }
    //-------------- Mayo
    else if(dia in 1..31 && mes==5){
        println("VALIDADO MAYO")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }
    //-------------- JUNIO
    else if(dia in 1..30 && mes==6){
        println("VALIDADO JUNIO")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }
    //-------------- Julio
    else if(dia in 1..31 && mes==7){
        println("VALIDADO JULIO")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }

    //-------------- Agosto
    else if(dia in 1..31 && mes==8 ){
        println("VALIDADO AGOSTO")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }
    //-------------- Septiembre
    else if(dia in 1..30 && mes==9){
        println("VALIDADO SEPTIEMBRE")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }
    //-------------- Octubre
    else if(dia in 1..31 && mes==10){
        println("VALIDADO OCTUBRE")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }
    //-------------- Noviembre
    else if(dia in 1..30 && mes==11){
        println("VALIDADO NOVIEMBRE")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }

    //-------------- Diciembre
    else if(dia in 1..30 && mes==11){
        println("VALIDADO DICIEMBRE")
        val resultado = validado(dia,mes,annio).toString()
        return resultado
    }


    else{
        println("FECHA NO VALIDA-->NO se puede calcular la edad")
    }


    println("\n*********************")


    return "ok"
}

fun validado(dia:Int,mes:Int,annio:Int) : Int{
    //Si se cumplió la validación entonces entramos a esta función de manera interna.
    //

    //____________________________________
    var fechaNacString= "${dia}/${mes}/${annio}"
    lateinit var fechaNacDate:Date
    try {
        fechaNacDate=SimpleDateFormat("dd/MM/yyyy").parse(fechaNacString)
    }
    catch (e:Exception){
        println("Humano estupido te dije un fecha valida ${e}")}

    var fechaActual=Date(System.currentTimeMillis())
    var diferenciaFechasMili=fechaActual.getTime()-fechaNacDate.getTime()
    var segundos=diferenciaFechasMili/1000
    var minutos=segundos/60
    var horas=minutos/60
    var dias=horas/24
    var edad=dias/365
    println("Humano tu edad es de: ${edad}")
    var edadCadena: Int= edad.toInt()
    return edadCadena
}
//horoscopo normal
// los doce signos
fun horoscopo(dia: Int, mes: Int): String {
    println("\n ***** Horoscopo ***** \n")

    //---------- Acuario
    if (dia in 20..31 && mes==1 || dia in 1..18 && mes==2) {
        val signo: String = "Acuario"
        return signo
    }

    //-------------- Piscis
    else if (dia in 19..29 && mes == 2 || dia in 1..20 && mes == 3) {
        val signo: String = "Piscis"
        return signo
    }
    //-------------- Aries
    else if (dia in 21..31 && mes == 3 || dia in 1..20 && mes == 4) {
        val signo: String = "Aries"
        return signo
    }
    //-------------- Tauro
    else if (dia in 21..30 && mes == 4 || dia in 1..21 && mes == 5) {
        val signo: String = "Tauro"
        return signo
    }
    //-------------- Geminis
    else if (dia in 22..31 && mes == 5 || dia in 1..21 && mes == 6) {
        val signo: String = "Geminis"
        return signo
    }
    //-------------- Cancer
    else if (dia in 22..31 && mes == 6 || dia in 1..22 && mes == 7) {
        val signo: String = "Cancer"
        return signo
    }
    //-------------- Leo
    else if (dia in 22..31 && mes == 7 || dia in 1..23 && mes == 8) {
        val signo: String = "Leo"
        return signo
    }
    //-------------- Virgo
    else if (dia in 24..31 && mes == 8 || dia in 1..23 && mes == 9) {
        val signo: String = "Virgo"
        return signo
    }
    //-------------- Libra
    else if (dia in 24..30 && mes == 9 || dia in 1..23 && mes == 10) {
        val signo: String = "Libra"
        return signo
    }
    //-------------- Escorpion
    else if (dia in 24..31 && mes == 10 || dia in 1..22 && mes == 11) {
        val signo: String = "Escorpio"
        return signo
    }
    //-------------- Sagitario
    else if (dia in 23..30 && mes == 11 || dia in 1..21 && mes == 12) {
        val signo: String = "Sagitario"
        return signo
    }
    //-------------- Capricornio
    else if (dia in 22..31 && mes == 12 || dia in 1..19 && mes == 1) {
        val signo: String = "Capricornio"
        return signo
    }

    return "ERROR FATAL en los horoscopos"
}

//-------------------------
//--------------Horoscopo CHINO -----------------
// //-------------------------
fun chinoHoroscopo(annio:Int, intent: Intent): String {
    println("\n ***** Horoscopo CHINO ***** \n")
    // recibir el valor de idioma desde el intent y asignarlo a una variable local

    val idioma = intent.getStringExtra("idioma")

    //esta función recibe un parametro

    val opcionesCarreras = if (idioma == "es") {

        val HoroscoChin = mapOf(
            "Rata" to arrayOf(1900, 1912, 1924, 1936, 1948, 1960, 1972, 1984, 1996, 2008, 2020),
            "Buey" to arrayOf(1901, 1913, 1925, 1937,1949, 1961, 1973, 1985, 1997, 2009, 2021),
            "Tigre" to arrayOf(1902, 1914, 1926, 1938, 1950, 1962, 1974, 1986, 1998, 2010, 2022),
            "Conejo" to arrayOf(1903, 1915, 1927, 1939, 1951, 1963, 1975, 1987, 1999, 2023),
            "Dragon" to arrayOf(1904, 1916, 1928, 1940, 1952, 1964, 1976, 1988, 2000, 2012, 2024),
            "Serpiente" to arrayOf(1905, 1917, 1929, 1941, 1953, 1965, 1977, 1989, 2001, 2013, 2025),
            "Caballo" to arrayOf(1906, 1918, 1930, 1942, 1954, 1966, 1978, 1990, 2002, 2014, 2026),
            "Cabra" to arrayOf(1907, 1919, 1931, 1943, 1955, 1967, 1979, 1991, 2003, 2015, 2027),
            "Mono" to arrayOf(1908, 1920, 1932, 1944, 1956, 1968, 1980, 1992, 2004, 2016, 2028),
            "Gallo" to arrayOf(1909, 1921, 1933, 1945, 1957, 1969, 1981, 1993, 2005, 2017, 2029),
            "Perro" to arrayOf(1910, 1922, 1934, 1946, 1958, 1970, 1982, 1994, 2006, 2018, 2030),
            "Cerdo" to arrayOf(1911, 1923, 1935, 1947, 1971, 1983, 1995, 2007, 2019, 2031)
        )
        val buscar = HoroscoChin.entries.find { it.value.contains(annio) }
        if (buscar != null) {
            val animalChino = buscar.key
            println("Tu horoscopo chino es: $animalChino")
            return animalChino.toString()
        } else {
            println("El valor $annio no se encuentra en el mapa")
        }
    }
    else {
        val HoroscoChin = mapOf(
            "Rat" to arrayOf(1900, 1912, 1924, 1936, 1948, 1960, 1972, 1984, 1996, 2008, 2020),
            "Ox" to arrayOf(1901, 1913, 1925, 1937, 1949, 1961, 1973, 1985, 1997, 2009, 2021),
            "Tiger" to arrayOf(1902, 1914, 1926, 1938, 1950, 1962, 1974, 1986, 1998, 2010, 2022),
            "Rabbit" to arrayOf(1903, 1915, 1927, 1939, 1951, 1963, 1975, 1987, 1999, 2023),
            "Dragon" to arrayOf(1904, 1916, 1928, 1940, 1952, 1964, 1976, 1988, 2000, 2012, 2024),
            "Snake" to arrayOf(1905, 1917, 1929, 1941, 1953, 1965, 1977, 1989, 2001, 2013, 2025),
            "Horse" to arrayOf(1906, 1918, 1930, 1942, 1954, 1966, 1978, 1990, 2002, 2014, 2026),
            "Goat" to arrayOf(1907, 1919, 1931, 1943, 1955, 1967, 1979, 1991, 2003, 2015, 2027),
            "Monkey" to arrayOf(1908, 1920, 1932, 1944, 1956, 1968, 1980, 1992, 2004, 2016, 2028),
            "Rooster" to arrayOf(1909, 1921, 1933, 1945, 1957, 1969, 1981, 1993, 2005, 2017, 2029),
            "Dog" to arrayOf(1910, 1922, 1934, 1946, 1958, 1970, 1982, 1994, 2006, 2018, 2030),
            "Pig" to arrayOf(1911, 1923, 1935, 1947, 1971, 1983, 1995, 2007, 2019, 2031)
        )
        val buscar = HoroscoChin.entries.find { it.value.contains(annio) }
        if (buscar != null) {
            val animalChino = buscar.key
            println("Tu horoscopo chino es: $animalChino")
            return animalChino.toString()
        } else {
            println("El valor $annio no se encuentra en el mapa")
        }
    }





    return "Error horoscopoChino"
}

fun elementoChino(annio:Int): String {
    //sacar el elemento del signo
    //para esto se debe mirar el ultimo digito

    val elemento = annio % 10
    println("El  numero del elemento es: $elemento")

    //metal
    if(elemento in 0..1){
        return "Metal"
    }
    //Agua
    else if(elemento in 2..3){
        return "Agua"
    }
    //Madera
    else if(elemento in 4..5){
        return "Madera"
    }
    //Fuego
    else if(elemento in 6..7){
        return "Fuego"
    }
    //Tierra
    else if(elemento in 8..9){
        return "Tierra"
    }

    return "Error en los elementos"
}


fun obtenerImagenHoroscopo(signo: String): Int {

    val imagenesHoroscopos = mapOf(
        "Aries" to R.drawable.aries,
        "Tauro" to R.drawable.tauro,
        "Geminis" to R.drawable.geminis,
        "Cancer" to R.drawable.cancer,
        "Leo" to R.drawable.leo,
        "Virgo" to R.drawable.virgo,
        "Libra" to R.drawable.libra,
        "Escorpio" to R.drawable.escorpio,
        "Sagitario" to R.drawable.sagitario,
        "Capricornio" to R.drawable.capricornio,
        "Acuario" to R.drawable.acuario,
        "Piscis" to R.drawable.piscis
    )

    return imagenesHoroscopos[signo] ?: R.drawable.default_image // Si no se encuentra el signo, devolvemos una imagen por defecto
}

fun obtenerImagenHoroscopoChino(signo: String,intent: Intent): Int {
    val idioma = intent.getStringExtra("idioma")
    val opcionesCarreras = if (idioma == "es") {

        val imagenesHoroscopos = mapOf(
            "Rata" to R.drawable.rata,
            "Buey" to R.drawable.buey,
            "Tigre" to R.drawable.tigre,
            "Conejo" to R.drawable.conejo,
            "Dragon" to R.drawable.dragon,
            "Serpiente" to R.drawable.serpiente,
            "Caballo" to R.drawable.caballo,
            "Cabra" to R.drawable.cabra,
            "Mono" to R.drawable.mono,
            "Gallo" to R.drawable.gallo,
            "Perro" to R.drawable.perro,
            "Cerdo" to R.drawable.cerdo
        )

        return imagenesHoroscopos[signo] ?: R.drawable.default_image // Si no se encuentra el signo, devolvemos una imagen por defecto
    }
    else {
        val imagenesHoroscopos = mapOf(
            "Rat" to R.drawable.rata,
            "Ox" to R.drawable.buey,
            "Tiger" to R.drawable.tigre,
            "Rabbit" to R.drawable.conejo,
            "Dragon" to R.drawable.dragon,
            "Snake" to R.drawable.serpiente,
            "Horse" to R.drawable.caballo,
            "Goat" to R.drawable.cabra,
            "Monkey" to R.drawable.mono,
            "Rooster" to R.drawable.gallo,
            "Dog" to R.drawable.perro,
            "Pig" to R.drawable.cerdo
        )
        return imagenesHoroscopos[signo] ?: R.drawable.default_image // Si no se encuentra el signo, devolvemos una imagen por defecto
    }
}
//obtenerImagenElemento

fun obtenerImagenElemento(elemento: String): Int {
    val imagenesHoroscopos = mapOf(
        "Metal" to R.drawable.metal,
        "Agua" to R.drawable.agua,
        "Madera" to R.drawable.madera,
        "Fuego" to R.drawable.fuego,
        "Tierra" to R.drawable.tierra
    )

    return imagenesHoroscopos[elemento] ?: R.drawable.default_image // Si no se encuentra el signo, devolvemos una imagen por defecto
}

//-----------------------------------------
// -----------  Carreras Imagenes ---------
//-----------------------------------------

fun obtenerImagenCarrera(carrera: String,intent:Intent): Int {
    val idioma = intent.getStringExtra("idioma")

    val opcionesCarreras = if (idioma == "es") {


    val imagenesCarreras = mapOf(
        "Ing. Aeroespacial" to R.drawable.aeroespacial,
        "Ing. Civil" to R.drawable.civil,
        "Ing. Geomatica" to R.drawable.geomatica,
        "Ing. Ambiental" to R.drawable.ambiental,
        "Ing. Geofísica" to R.drawable.geofisica,
        "Ing. Geológica" to R.drawable.geologica,
        "Ing. Petrolero" to R.drawable.petrolera,
        "Ing. Minas y Metalurgista" to R.drawable.minas,
        "Ing. Computación" to R.drawable.computacion,
        "Ing. Eléctrica" to R.drawable.electrica,
        "Ing. Telecomunicaciones" to R.drawable.telecomunacion,
        "Ing. Mecánica" to R.drawable.mecanica,
        "Ing. Industrial" to R.drawable.industrial,
        "Ing. Mecatrónica" to R.drawable.mecatronica,
        "Ing. Sistemas Biomédicos" to R.drawable.biomedicos
    )

    return imagenesCarreras[carrera] ?: R.drawable.default_image // Si no se encuentra la carrera, devolvemos una imagen por defecto
}else{
        val imagenesCarreras = mapOf(
            "Eng. Aerospace" to R.drawable.aeroespacial,
            "Eng. Civil" to R.drawable.civil,
            "Eng. Geomatics" to R.drawable.geomatica,
            "Eng. Environmental" to R.drawable.ambiental,
            "Eng. Geophysics" to R.drawable.geofisica,
            "Eng. Geological" to R.drawable.geologica,
            "Eng. Petroleum" to R.drawable.petrolera,
            "Eng. Mining and Metallurgical" to R.drawable.minas,
            "Eng. Computer" to R.drawable.computacion,
            "Eng. Electrical" to R.drawable.electrica,
            "Eng. Telecommunications" to R.drawable.telecomunacion,
            "Eng. Mechanical" to R.drawable.mecanica,
            "Eng. Industrial" to R.drawable.industrial,
            "Eng. Mechatronics" to R.drawable.mecatronica,
            "Eng. Biomedical Systems" to R.drawable.biomedicos

        )
        return imagenesCarreras[carrera] ?: R.drawable.default_image // Si no se encuentra la carrera, devolvemos una imagen por defecto

    }

}

