package o3.lepifyo.analizador

import o3.lepifyo.Programa
import o3.lepifyo.analizador.Gravedad.Gravedad
import o3.lepifyo.contexto.{Contexto, ContextoAnalizador}
import o3.lepifyo.expresiones.Expresion


class Regla(error : String, gravedad : Gravedad, checker: (Expresion, ContextoAnalizador, Programa) => Boolean) {

  def resolver(expresion : Expresion, contexto : ContextoAnalizador, programa : Programa): List[Problema] ={
    if (checker(expresion, contexto, programa)){
      new Problema(error, gravedad, expresion) :: List()
    } else {
      List()
    }
  }
}

object Gravedad extends Enumeration{
  type Gravedad = Value
  val Error, Advertencia= Value
}
