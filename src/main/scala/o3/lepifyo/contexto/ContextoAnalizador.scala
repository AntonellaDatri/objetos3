package o3.lepifyo.contexto

import o3.lepifyo.expresiones.Expresion
import o3.lepifyo.valores.Valor

class ContextoAnalizador(var variables : Map[String, Expresion] = Map()) {
  def contieneVariable(nombre: String): Boolean = {
    variables.keys.exists(nombreDeVariable => nombreDeVariable == nombre)
  }

  def agregarVariable(nombreVariable : String, expresion: Expresion): Expresion ={
    variables += (nombreVariable -> expresion)
    expresion
  }
}

