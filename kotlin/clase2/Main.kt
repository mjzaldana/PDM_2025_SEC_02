package clase2

fun main() {
    var name: String? = null

    // Uso de ?. para mostrar null en caso de que name sea null
    println("Hola ${name?.length}")

    // Uso de ?: (Elvis operator) para dar un valor por defecto
    println("Hola ${name?.length ?: "desconocido"}")

    // Uso de !! para lanzar una excepcion si asi se requiere
    if (name != null) {
        println("Hola ${name!!.length}")
    } else {
        println("El nombre es nulo, no se puede calcular su longitud.")
    }
}
