package o3.lepifyo.lambda

import o3.lepifyo.Programa
import o3.lepifyo.analizador.{Problema, Regla}
import o3.lepifyo.contexto.{Contexto, ContextoAnalizador}
import o3.lepifyo.expresiones.Expresion
import o3.lepifyo.valores.{Valor, ValorLambda}

case class DeclaracionLambda(parametros:List[String], cuerpo: List[Expresion]) extends Expresion {
  override def ejecutar(contexto: Contexto): Valor = ValorLambda(parametros,cuerpo, contexto)

  override def analizar(regla: Regla, contexto: ContextoAnalizador, programa: Programa): List[Problema] = {
    val contextoLambda = new ContextoAnalizador()
    regla.resolver(this, contexto, programa) ++ cuerpo.flatMap(expresion => regla.resolver(expresion, contextoLambda, Programa(cuerpo)))
  }
}
