package clase2

//SCOPE FUNCTIONS
fun main(){
    var name: String = "Mauricio"
    var persona1 = Persona(name, 23)

    //let: me permite ejecutar instrucciones para un contexto dado
    persona1?.let {
        print("La persona es ${it.nombre}")
    }
}