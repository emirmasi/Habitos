package com.practica.habitos.Ejercicios

/*
 * Crea un programa que invierta el orden de una cadena de texto
 * sin usar funciones propias del lenguaje que lo hagan de forma automática.
 * - Si le pasamos "Hola mundo" nos retornaría "odnum aloH"
 */

fun invertirCadenas(cadena:String):String{
    var cadenaInvertida:String =""
    var tamCadena = cadena.length

    for (i in 0..tamCadena-1){
        cadenaInvertida += cadena[tamCadena-1-i]
    }
    return cadenaInvertida
}

fun main(){
   /* println(invertirCadenas("Hola mundo"))
    val palabra = "Mundo"
    val texto = "Hola mundo como mund mundo mundo mund"
    println("cantidad de $palabra repetida: ${contadorDePalabras(texto,palabra)}")

    */
    //val numero  = 28
    //print("el $numero en binario es ${convertirABinario(numero)}")
    val expresion = "{[a*(b-c)}"
    print("la expreion : $expresion esta ${if (comprobarExpresion(expresion)) "balanceada" else "no balanceada"}")
}

/*
 * Crea un programa que cuente cuantas veces se repite cada palabra
 * y que muestre el recuento final de todas ellas.
 * - Los signos de puntuación no forman parte de la palabra.
 * - Una palabra es la misma aunque aparezca en mayúsculas y minúsculas.
 * - No se pueden utilizar funciones propias del lenguaje que
 *   lo resuelvan automáticamente.
 */

fun contadorDePalabras(texto:String, palabra:String):Int{
    ///tengo que recorrer el texto y verificar que
    ///for para el texto
    val tamPalabra = palabra.length
    val tamTexto = texto.length
    var cantidadPalabrasRepetidas = 0
    var i = 0
    /// el primer while es hasta tamTexto - tamPalabra porque no hay que seguir si no va a cumplir con el tamño
    while ( i < tamTexto - tamPalabra){
        var j = 0
        var esRepetida = true
        while (j<tamPalabra && esRepetida){
            if (texto[i+j].toLowerCase() != palabra[j].toLowerCase())
                esRepetida = false
            j++
        }
        if(esRepetida){
            cantidadPalabrasRepetidas++
            i+=tamPalabra
        }else{
            i++
        }
    }
    return cantidadPalabrasRepetidas
}

/*
 * Crea un programa se encargue de transformar un número
 * decimal a binario sin utilizar funciones propias del lenguaje que lo hagan directamente.
 */
fun convertirABinario(numero:Int):Long{
    var numeroBinario = ""
    var residuo :Int
    var cociente:Int = numero
    while (cociente != 0){
        residuo = cociente%2
        cociente /= 2
        numeroBinario += if(residuo != 0){
            "1"
        }else{
            "0"
        }
    }
    return numeroBinario.reversed().toLong()
}
/*
 * Crea un programa que comprueba si los paréntesis, llaves y corchetes
 * de una expresión están equilibrados.
 * - Equilibrado significa que estos delimitadores se abren y cieran
 *   en orden y de forma correcta.
 * - Paréntesis, llaves y corchetes son igual de prioritarios.
 *   No hay uno más importante que otro.
 * - Expresión balanceada: { [ a * ( c + d ) ] - 5 }
 * - Expresión no balanceada: { a * ( c + d ) ] - 5 }
 */

fun comprobarExpresion(expresion:String):Boolean{
    ///las expresiones puedn utilizar {},[],()
    ///recorro toda la cadena si encuentro alguna de estas expresiones
    ///si esta algun {},[],() debe estar cerrado al final
    // el segundo debe estar x delante del ultimo y asi sucesivamente
    //para que este de forma correcta
    var i = 0
    val tamExpresion = expresion.length
    var j = tamExpresion-1
    var esBalanceada = true
    var sigo = true
    val listaDeExpresones = listOf('{','[','(')
    val listaDeexpresionesCerradas = listOf('}',']',')')
    while(i != j && esBalanceada){
        if(expresion[i] in listaDeExpresones){
            ///vamos a buscar en el otro
            ///aca hay un problema el index esta mal
            var posicion = listaDeExpresones.indexOf(expresion[i])

            while (i != j && sigo){
                if(expresion[j] != listaDeexpresionesCerradas[posicion])
                    j--
                else {
                    sigo = false
                    j-- //asi cominezo desde el otro
                }

            }
            if(j == i)
                esBalanceada = false

            sigo = true
        }
        i++
    }
    return esBalanceada
}