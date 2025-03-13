package clase2

data class Persona(val nombre:String, val edad:Int)
//class Persona(val nombre:String, val edad:Int)

fun main(){
    var persona = Persona("Mauricio", 23)
    var persona2 = Persona("Mauricio", 23)

    //Si es data class dara true si es class dara false
    print(persona == persona2)
}