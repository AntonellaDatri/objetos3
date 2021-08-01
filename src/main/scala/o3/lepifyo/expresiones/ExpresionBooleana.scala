package o3.lepifyo.expresiones

import o3.lepifyo.Programa
import o3.lepifyo.analizador.{Problema, Regla}
import o3.lepifyo.contexto.Contexto
import o3.lepifyo.valores.{Valor, ValorBooleano}

case class ExpresionBooleana(a: Boolean) extends Expresion {
  override def ejecutar(contexto: Contexto): Valor = ValorBooleano(a)
}
