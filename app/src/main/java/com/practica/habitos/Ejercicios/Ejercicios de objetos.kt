package com.practica.habitos.Ejercicios

import kotlin.math.sqrt

/*Un librero tiene muchos libros clasificados en 26 categorías etiquetadas A, B,... Z. Cada libro tiene un código c de 3, 4, 5 o
más caracteres. El primer carácter de un código es una letra mayúscula que define la categoría del libro.

En el stock del librero cada código c va seguido de un espacio y de un número entero positivo n (int n >= 0) que indica la cantidad de
libros de este código en stock.

Por ejemplo, un extracto de una lista de existencias podría ser:

L = {"ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"}.
o
L = ["ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"] o ....
Se le dará una lista de existencias (por ejemplo: L) y una lista de categorías en letras mayúsculas, por ejemplo:

METRO = {"A", "B", "C", "W"}
o
M = ["A", "B", "C", "W"] o ...
y tu tarea es encontrar todos los libros de L con códigos pertenecientes a cada categoría de M y sumar su cantidad según cada categoría.

Para las listas L y M del ejemplo hay que devolver la cadena (en Haskell/Clojure/Racket/Prolog una lista de pares):

(A: 20) - (B: 114) - (C: 50) - (W: 0)
donde A, B, C, W son las categorías, 20 es la suma del libro único de la categoría A, 114 la suma correspondiente a "BKWRK" y "BTSQZ",
50 corresponde a "CDXEF" y 0 a la categoría 'W' ya que no hay ningún código que comience con W.

Si L o M están vacías, la cadena de retorno es "" (Clojure/Racket/Prolog debería devolver una matriz/lista vacía en su lugar).

Notas:
En los códigos de resultado y sus valores están en el mismo orden que en M.
Consulte "Pruebas de muestras" para la devolución
 */
object StockList {
    fun stockSummary(lstOfArt: Array<String>, lstOfCat: Array<String>): String {
        // your code
        //vamos a crear el map
        val mapOfArt = mutableMapOf<String,Int>().withDefault { 0 }
        for(stock in lstOfArt){
            val (codigo,cantidadStr) = stock.split(" ")
            val categoria = codigo.first().toString()
            val cant = cantidadStr.toInt()
            if(categoria in lstOfCat)
                mapOfArt[categoria] = mapOfArt.getValue(categoria) + cant

        }
        return lstOfCat.joinToString(separator = " - "){ categoria->
            "($categoria : ${mapOfArt.getValue(categoria)})"
        }
    }
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