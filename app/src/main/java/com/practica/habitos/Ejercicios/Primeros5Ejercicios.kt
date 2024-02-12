package com.practica.habitos.Ejercicios

import kotlin.math.sqrt

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
 * Escribe un programa que imprima los 50 primeros números de la sucesión
 * de Fibonacci empezando en 0.
 * - La serie Fibonacci se compone por una sucesión de números en
 *   la que el siguiente siempre es la suma de los dos anteriores.
 *   0, 1, 1, 2, 3, 5, 8, 13...
 */
fun finobacci(){
    var n0 = 0
    var n1 = 1
    for (i in 0..50){
        print(n0)
        val fib = n0+n1
        n0 = n1
        n1 = fib
    }
}
///fibonacci recursivo
fun fiboRecur(
    n: Int
):Int{
    if(n<=1)
        return n
    return fiboRecur(n-1) + fiboRecur(n-2)
}

/*
 * Escribe un programa que se encargue de comprobar si un número es o no primo.
 * Hecho esto, imprime los números primos entre 1 y 100.
 */
fun esPrimo(num:Int):Boolean{
    if(num < 2)///los numeros primos son solo numeros naturales
        return false

    for(i in 2..(num-1)){
        if(num % i == 0 )///si la division de numeros a partir de 2 hasta num-1 da 0 significa que es divisor y por lo tanto no es primo
            return false
    }
    return true
}

/*
 * Crea una única función (importante que sólo sea una) que sea capaz
 * de calcular y retornar el área de un polígono.
 * - La función recibirá por parámetro sólo UN polígono a la vez.
 * - Los polígonos soportados serán Triángulo, Cuadrado y Rectángulo.
 * - Imprime el cálculo del área de un polígono de cada tipo.
 */

fun calcularAreaPoligono(poligono : Poligono):Float{
    return poligono.calcularArea()
}
abstract class Poligono///puede ser una interface tranquilamente
{
    abstract fun calcularArea():Float
}

class Triangulo(
    private val a:Float,
    private val b:Float,
    private val c:Float
) : Poligono()
{
    fun esValido():Boolean{
        if(a+b>c)
            if(b+c>a)
                if(a + c >b)
                    return true
        return false
    }
    override fun calcularArea(): Float {
        val semiperimetro = (a+b+c)/2
        ///calcular el area de un triangulo
        return sqrt((semiperimetro)*(semiperimetro-a)*(semiperimetro-b)*(semiperimetro-c))
    }

}
class Cuadrado(
    private val lado:Float
) : Poligono()
{
    override fun calcularArea(): Float {
        return lado.times(lado)
    }

}
class Rectangulo(
    private val base : Float,
    private val altura: Float
) : Poligono()
{
    override fun calcularArea(): Float {
        return base.times(altura)
    }

}

