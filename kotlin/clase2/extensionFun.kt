package clase2

fun String.agregarExclamacion(): String {
    return this + "!!! Mauricio"
}

fun main() {
    var saludo: String = "Hola"
    var saludo2: String = "Hola 2"

    print(saludo.agregarExclamacion())
}
