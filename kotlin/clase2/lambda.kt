package clase2

fun main() {
    val numeros = listOf(1, 2, 3, 4, 5)
    val numeros2 = listOf(1, 2, 3, 4, 5, 6)

    val pares = numeros.filter { it % 2 == 0 }
    val cuadrado = numeros.map { (it * it) + it }

    println("Números pares: $pares")
    println("Valores al cuadrado más sí mismos: $cuadrado")
}
