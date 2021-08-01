package o3.lepifyo.variables

import o3.lepifyo.Programa
import o3.lepifyo.expresiones.Expresion
import o3.lepifyo.analizador.{Problema, Regla}
import o3.lepifyo.contexto.{Contexto, ContextoAnalizador}
import o3.lepifyo.valores.Valor

case class AsignacionVariable(nombre: String, expresion: Expresion) extends Expresion {
  override def ejecutar(contexto: Contexto): Valor = {
    contexto.modificarVariable(nombre, expresion.ejecutar(contexto))
  }

  override def analizar(regla: Regla, contexto: ContextoAnalizador, programa: Programa): List[Problema] = {
    regla.resolver(this, contexto, programa) ++ expresion.analizar(regla, contexto, programa)
  }
}
