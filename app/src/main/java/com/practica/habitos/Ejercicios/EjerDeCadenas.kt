package com.practica.habitos.Ejercicios

import kotlin.math.abs

/*1)
 * Escribe un programa que muestre por consola (con un print) los
 * números de 1 a 100 (ambos incluidos y con un salto de línea entre
 * cada impresión), sustituyendo los siguientes:
 * - Múltiplos de 3 por la palabra "fizz".
 * - Múltiplos de 5 por la palabra "buzz".
 * - Múltiplos de 3 y de 5 a la vez por la palabra "fizzbuzz".
 */

fun fizzbuzz(){
    ///es un codigo legible y mejor estrucuturado
    val fizzDivisor = 3
    val buzzDivisor = 5
    for(i in 1..100){
        val isFizz = i % fizzDivisor == 0
        val isBuzz = i % buzzDivisor == 0
        when{
            isBuzz && isFizz-> println("fizzbuzz")
            isFizz ->println("fizz")
            isBuzz-> println("buzz")
            else->println(i)
        }
    }
}

/*
 * Escribe una función que reciba dos palabras (String) y retorne
 * verdadero o falso (Bool) según sean o no anagramas.
 * - Un Anagrama consiste en formar una palabra reordenando TODAS
 *   las letras de otra palabra inicial.
 * - NO hace falta comprobar que ambas palabras existan.
 * - Dos palabras exactamente iguales no son anagrama.
 */

///en koltin las cadenas son inmutables
fun esAnagrama(
    palabra1:String,
    palabra2:String)
        :Boolean{
    ///if la letra de la primera palabra se encuentra en la seg palabra lo reemplaza con un ´+´ para que no vuelva a repetir
    ///primero verifamos que ambas palabras no estan vacias
    val caracterDeDiferencia = '+'
    if(palabra1.isEmpty() || palabra2.isEmpty())
        return false

    for (letra in palabra1){//"listen y silent son anagramas
        if (letra in palabra2) {
            palabra2.replace(letra, caracterDeDiferencia)
        } else {
            return false
        }
    }
    for (letra in palabra2){///significa que la segunda palabra es mas larga que la primera
        if(caracterDeDiferencia != letra)
            return false
    }
    ///hay que verificar que la segunda palabra fue recorrido por completo

    return true
}
fun esAnagramaOptimizado(
    primeraPalabra:String,
    segundaPalabra: String
):Boolean
{
    ///primero verificamos que no sean cadenas vacias
    if(primeraPalabra.isEmpty() || segundaPalabra.isEmpty()) return false

    ///luego para que no diferenciemos entre mayus o minus , lo pasamos ambos a minus
    val priPalaToLower = primeraPalabra.toLowerCase()
    val segPalabraToLoew = segundaPalabra.toLowerCase()

    ///creamos un map que vaya contado la cantida de caracteres que hay en la primera palabra
    val contadorDeCaracteres  = mutableMapOf<Char,Int>()

    ///recorremos la primera palabra y contamos los caracteres
    for(letra in priPalaToLower) {
        contadorDeCaracteres.getOrDefault(letra,0)+1
    }
    ///recorremos la segunda palabra y por cada letra le descontamos al contador de caracteres
    for(letra in segPalabraToLoew){
        contadorDeCaracteres[letra] = contadorDeCaracteres.getOrDefault(letra,0)-1
    }
    ///recorremos todo el map de y si estan todas en 0 significa que es un anagrama
    return contadorDeCaracteres.all{
        it.value == 0
    }
}

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
    var j = expresion.length-1
    var esBalanceada = true
    ///hacer un mapa de estas expresiones
    val listaDeExpresiones = listOf('{','[','(')
    val listaDeExpresionesCerradas = listOf('}',']',')')
    while(i != j){
        if(expresion[i] in listaDeExpresiones){
            ///vamos a buscar en el otro
            ///aca hay un problema el index esta mal
            val posicion = listaDeExpresiones.indexOf(expresion[i])
            var sigo = true
            while (i != j && sigo){
                if(expresion[j] == listaDeExpresionesCerradas[posicion])
                    sigo = false
                j--
            }
        }
        i++
    }
    if(j == i)
        esBalanceada = false

    return esBalanceada
}

/*
 * Crea una función que reciba dos cadenas como parámetro (str1, str2)
 * e imprima otras dos cadenas como salida (out1, out2).
 * - out1 contendrá todos los caracteres presentes en la str1 pero NO
 *   estén presentes en str2.
 * - out2 contendrá todos los caracteres presentes en la str2 pero NO
 *   estén presentes en str1.
 */
fun restaDeCadenas(str1:String,str2:String){
    val out1 = restar(str1,str2)
    val out2 = restar(str2,str1)

    println("str1 - str2 = $out1")
    println("str2 - str1 = $out2")
}
fun restar(str1:String,str2:String):String{
    var out1 = ""


    for (letra in str1){
        if(letra !in str2){
            out1+=letra
        }
    }
    return out1
}
/*
 * Escribe una función que reciba un texto y retorne verdadero o
 * falso (Boolean) según sean o no palíndromos.
 * Un Palíndromo es una palabra o expresión que es igual si se lee
  * de izquierda a derecha que de derecha a izquierda.
 * NO se tienen en cuenta los espacios, signos de puntuación y tildes.
 * Ejemplo: Ana lleva al oso la avellana.
 */
fun esPalindromo(texto : String):Boolean{
    ///kotlin no trabaja con punteros
    var i = 0
    var j = texto.length - 1
    while(i < j ){
        while (i < j && texto[i].lowercaseChar() == ' ')
            i++
        while (i < j && texto[j].lowercaseChar() == ' ')
            j--
        if(texto[i].lowercaseChar() != texto[j].lowercaseChar())
            return false
        i++
        j--
    }
    return true
}
/*
 * Crea una función que calcule y retorne cuántos días hay entre dos cadenas
 * de texto que representen fechas.
 * - Una cadena de texto que representa una fecha tiene el formato "dd/MM/yyyy".
 * - La función recibirá dos String y retornará un Int.
 * - La diferencia en días será absoluta (no importa el orden de las fechas).
 * - Si una de las dos cadenas de texto no representa una fecha correcta se
 *   lanzará una excepción.
 */
//si es absoluto no importa la si la fecha 1 es menor o mayor a la fecha 2

class Fecha(
     private var dia:Int,
     private var mes:Int,
     private var anio:Int
){
    constructor(fecha: String) : this(0,0,0) {
        val partesFecha = fecha.split('/')
        require(partesFecha.size == 3)

        this.dia = partesFecha[0].toInt()
        this.mes = partesFecha[1].toInt()
        this.anio = partesFecha[2].toInt()

    }
    fun getDia():Int = this.dia
    fun getMes():Int = this.mes
    fun getAnio():Int = this.anio
}
fun cantidadDeDiasEntreDosFechas(fecha1: String,fecha2: String): Int{
    var cantDiasFecha1: Int = 0
    var cantDiasFecha2:Int = 0
    val fecha1Int = Fecha(fecha1)
    val fecha2Int = Fecha(fecha2)
    val anioBase = 1601 ///ponemos el año base cen 1601 calendario gregoranio

    val cantAniosFecha1:Int = fecha1Int.getAnio() - anioBase
    val cantAniosFecha2:Int = fecha2Int.getAnio() - anioBase

    cantDiasFecha1 += (cantAniosFecha1 * 365 + cantAniosFecha1/4 - cantAniosFecha1/100 + cantAniosFecha1/400)
    cantDiasFecha2 += (cantAniosFecha2 * 365 + cantAniosFecha2/4 - cantAniosFecha2/100 + cantAniosFecha2/400)

    if(fecha1Int.getMes() > 1)
        for(mes in 1..(fecha1Int.getMes()-1))
            cantDiasFecha1+= cantidadDeDiasXMes(mes,fecha1Int.getAnio())
    if(fecha2Int.getMes() > 1)
        for(mes in 1..(fecha2Int.getMes()-1))
            cantDiasFecha2+= cantidadDeDiasXMes(mes,fecha2Int.getAnio())

    cantDiasFecha1+=fecha1Int.getDia()
    cantDiasFecha2+=fecha2Int.getDia()

    return abs(cantDiasFecha1 - cantDiasFecha2)
}
fun cantidadDeDiasXMes(mes : Int,anio: Int): Int{
    val cantidadDeDiasXMes = listOf<Int>(
        31,28,31,20,31,30,31,31,30,31,30,31
    )
    return if(mes == 2){
         cantidadDeDiasXMes[mes-1] + esBisiesto(anio)
    }else {
        cantidadDeDiasXMes[mes-1]
    }

}

fun esBisiesto(anio: Int): Int {
    return if((anio % 400 == 0 || anio % 100 == 0) && (anio % 4 == 0)) 1 else 0
}

/*Escriba una función que tome una cadena de una o más palabras y devuelva la misma cadena,
 pero con todas las palabras que tengan cinco o más letras invertidas (como el nombre de este Kata).
 Las cadenas pasadas consistirán únicamente en letras y espacios. Los espacios se incluirán únicamente
 cuando esté presente más de una palabra.

Ejemplos:

"Hola compañeros guerreros" --> "Hola wollef sroirraw"
"Esto es una prueba --> "Esto es una prueba"
"Esta es otra prueba" --> "Esta es la prueba de Rehtona

 */
fun spinWords(sentence: String): String {

    val cadenaPartida = sentence.split(' ')
    var resultado = StringBuilder()

    for(palabra in cadenaPartida){
        if(palabra.all{ it.isLetter()} && palabra.length>=5)
            resultado.append(palabra.reversed()).append(" ")
        else
            resultado.append(palabra).append(" ")
    }

    return resultado.trim().toString()
}


fun main(){
    val fecha1 = "22/12/2024"
    val fecha2 = "20/12/2024"

    println("cantidad de dias de diferencia(2) = ${cantidadDeDiasEntreDosFechas(fecha1,fecha2)}")
}

/*Dado un conjunto de números, devuelve el inverso aditivo de cada uno.
Todo lo positivo se vuelve negativo y lo negativo se vuelve positivo.

invertir([1,2,3,4,5]) == [-1,-2,-3,-4,-5]
invertir([1,-2,3,-4,5]) == [-1,2,-3,4,-5]
invertir([]) == []
 */

////
