package o3.lepifyo.expresiones

import o3.lepifyo.Programa
import o3.lepifyo.analizador.{Problema, Regla}
import o3.lepifyo.contexto.{Contexto, ContextoAnalizador}
import o3.lepifyo.expresiones.Operador.Operador
import o3.lepifyo.valores.ValorNumerico

abstract class OperacionAritmetica(val a: Expresion, val b: Expresion, val operador: Operador) extends Expresion {
  override def ejecutar(contexto: Contexto): ValorNumerico = {
    val num1 = a.ejecutar(contexto)
    val num2 = b.ejecutar(contexto)
    (num1, num2) match {
      case (num1: ValorNumerico, num2: ValorNumerico) => operador match {
        case Operador.+ => num1 + num2
        case Operador.- => num1 - num2
        case Operador.* => num1 * num2
        case Operador./ => num1 / num2
      }
      case _ => throw new RuntimeException("Operacion aritmetica invalida")
    }
  }

  override def analizar(regla: Regla, contexto: ContextoAnalizador, programa: Programa): List[Problema] = regla.resolver(this, contexto, programa) ++ a.analizar(regla, contexto, programa) ++ b.analizar(regla, contexto, programa)
}


