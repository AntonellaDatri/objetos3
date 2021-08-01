package o3.lepifyo.expresiones

import o3.lepifyo.Programa
import o3.lepifyo.analizador.{Problema, Regla}
import o3.lepifyo.contexto.{Contexto, ContextoAnalizador}
import o3.lepifyo.expresiones.Operador.Operador
import o3.lepifyo.valores.{ValorBooleano, ValorNumerico}

abstract class OperacionComparacion(val a: Expresion, val b: Expresion, val operador: Operador) extends Expresion {
  override def ejecutar(contexto: Contexto): ValorBooleano = {
    val valor1 = a.ejecutar(contexto)
    val valor2 = b.ejecutar(contexto)

    (operador, valor1, valor2) match {
      case (Operador.>, valor1 : ValorNumerico, valor2 : ValorNumerico) => valor1 > valor2
      case (Operador.<, valor1 : ValorNumerico, valor2 : ValorNumerico) => valor1 < valor2
      case (Operador.>=, valor1 : ValorNumerico, valor2 : ValorNumerico) => valor1 >= valor2
      case (Operador.<=, valor1 : ValorNumerico, valor2 : ValorNumerico) => valor1 <= valor2
      case (Operador.==, _, _) => ValorBooleano(valor1 == valor2)
      case (Operador.!=, _, _) => ValorBooleano(valor1 != valor2)
      case _ => throw new RuntimeException("Operacion de comparacion invalida")
    }
  }

  override def analizar(regla: Regla,contexto: ContextoAnalizador, programa: Programa): List[Problema] = regla.resolver(this, contexto, programa) ++ a.analizar(regla, contexto, programa) ++ b.analizar(regla, contexto, programa)
}