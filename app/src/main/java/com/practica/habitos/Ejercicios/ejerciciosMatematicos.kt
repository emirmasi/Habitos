package com.practica.habitos.Ejercicios

import kotlin.math.sqrt


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
/*
 * Escribe una función que calcule si un número dado es un número de Armstrong
 * (o también llamado narcisista).
 * Si no conoces qué es un número de Armstrong, debes buscar información
 * al respecto.
 */

fun esNumeroAmstrong(num: Int):Boolean{
    ///tengo que buscar la cantidad de digitos
    /// si divido por 10 el numero el resto me queda para buscar el exponente
    val cantDigitos : Int =  cantidadDeDigitos(num)
    var cociente :Int = num
    var acum  = 0
    while (cociente != 0){
        val resto = cociente % 10
        acum+= exponente(resto,cantDigitos)
        cociente /= 10
    }
    return acum == num
}

fun cantidadDeDigitos(num: Int): Int {
    var cantDigitos = 0
    var resultado :Int = num
    while (resultado != 0){
        cantDigitos++
        resultado /= 10
    }
    return cantDigitos
}

