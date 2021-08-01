package o3.lepifyo.expresiones

import o3.lepifyo.Programa
import o3.lepifyo.analizador.{Problema, Regla}
import o3.lepifyo.contexto.{Contexto, ContextoAnalizador}
import o3.lepifyo.valores.Valor

trait Expresion {
  def analizar(regla: Regla, contexto: ContextoAnalizador, programa : Programa): List[Problema] = regla.resolver(this, contexto, programa)

  def ejecutar(contexto: Contexto): Valor
}
