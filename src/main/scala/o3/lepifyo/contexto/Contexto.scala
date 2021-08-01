package o3.lepifyo.contexto

import o3.lepifyo.valores.Valor

class Contexto(var contextoPadre : Option[Contexto] = None, var variables : Map[String, Valor] = Map()) {

  def agregarVariable(nombreVariable : String, valor : Valor): Valor ={
    variables += (nombreVariable -> valor)
    valor
  }
  def modificarVariable(nombreVariable : String, valor : Valor): Valor ={
    val optionVariable = variables.find(tupla => tupla._1 == nombreVariable)
    if(optionVariable.isEmpty){
      obtenerPadre(nombreVariable).modificarVariable(nombreVariable, valor)
    } else {
      variables += (nombreVariable -> valor)
      valor
    }
  }

  def obtenerVariable(nombreVariable : String): Valor ={

    val optionVariable = variables.find(tupla => tupla._1 == nombreVariable)
    if(optionVariable.isEmpty){
      obtenerPadre(nombreVariable).obtenerVariable(nombreVariable)
    } else {
      optionVariable.get._2
    }
  }

  def obtenerPadre(nombreVariable : String) : Contexto = {
    contextoPadre.getOrElse(throw new RuntimeException(s"No se pudo encontrar una variable con nombre $nombreVariable"))
  }
}
