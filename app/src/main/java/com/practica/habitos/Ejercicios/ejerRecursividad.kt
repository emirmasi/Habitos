package com.practica.habitos.Ejercicios
///hacer un fibonacci recursivo
fun fiboRecur(
    n: Int
):Int{
    if(n<=1)
        return n
    return fiboRecur(n-1) + fiboRecur(n-2)
}



fun main(){
    /*val texto = "anita lava la tina "
    println("el texto $texto  ${if(esPalindromo(texto)) "palindromo" else "no es palindromo"}")*/
    val numeroAmnstrong  = 370
    println("$numeroAmnstrong es ${if (esNumeroAmstrong(numeroAmnstrong)) "amstrong " else "no es amnstrong"}")
}

fun factorialRecursivo(n : Int):Int{
    return if(n == 0){
        1
    }else{
        n * factorialRecursivo(n-1)
    }
}
fun exponente(x : Int, n : Int):Int{
    return if(n == 0)
         1
    else
         x * exponente(x,n-1)
}
fun mostrarHola2(cad: String) {
    if (cad.isNotEmpty()) {
        mostrarHola2(cad.substring(1))
        println(cad[0])
    }
}
