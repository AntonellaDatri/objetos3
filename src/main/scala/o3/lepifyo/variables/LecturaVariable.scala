package o3.lepifyo.variables

import o3.lepifyo.Programa
import o3.lepifyo.analizador.{Problema, Regla}
import o3.lepifyo.contexto.Contexto
import o3.lepifyo.expresiones.Expresion
import o3.lepifyo.valores.Valor

case class LecturaVariable(nombre: String) extends Expresion {
  override def ejecutar(contexto: Contexto): Valor = {
    contexto.obtenerVariable(nombre)
  }
}
