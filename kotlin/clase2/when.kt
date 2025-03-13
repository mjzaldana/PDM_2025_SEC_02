package clase2

fun main(){
    val numero: Int = 3

    //Uso de when equivalente a switch case
    /*
    when(numero){
        1 -> print("Uno")
        2 -> print("Dos")
        3 -> print("Tres")
    }
    */

    //Uso de when para asignar un valor a una variable
    var mensaje:String = when(numero){
        1 -> "Uno"
        2 -> "Dos"
        3 -> "Tres"
        else-> "Otro"
    }

    print(mensaje)
}