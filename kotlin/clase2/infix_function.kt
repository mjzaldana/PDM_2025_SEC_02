package clase2

//Dato extra que no funciono en clase debido a la expresion "Math"
infix fun Int.potencia(exponente: Int): Int {
    return Math.pow(this.toDouble(), exponente.toDouble()).toInt()
}

fun main(){
    val resultado2 = 2 potencia 3
    println(resultado2)
}
