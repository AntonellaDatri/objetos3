package o3.lepifyo.lambda

import o3.lepifyo.Programa
import o3.lepifyo.analizador.{Problema, Regla}
import o3.lepifyo.contexto.{Contexto, ContextoAnalizador}
import o3.lepifyo.expresiones.{Expresion, ExpresionBooleana, ExpresionNumerica, OperacionAritmetica, OperacionComparacion}
import o3.lepifyo.valores.{Valor, ValorLambda}
import o3.lepifyo.variables.{AsignacionVariable, DeclaracionVariable, LecturaVariable}

case class AplicacionDeLambda(expresionLambda: Expresion, argumentos:List[Expresion])  extends Expresion {
  override def ejecutar(contextoPrincipal: Contexto): Valor = {
    val valor = expresionLambda.ejecutar(contextoPrincipal)
    valor match {
      case ValorLambda(parametros, cuerpo, contextoPadre) =>
        val variables = obtenerVariablesDelContexto(contextoPrincipal, parametros)
        val contextoLambda = new Contexto(contextoPadre = Some(contextoPadre), variables = variables)
        val valores = cuerpo.map(expresion => expresion.ejecutar(contextoLambda))
        valores.last
    }
  }

  override def analizar(regla: Regla, contexto: ContextoAnalizador, programa: Programa): List[Problema] = {
    regla.resolver(this, contexto, programa) ++ argumentos.flatMap(expresion => regla.resolver(expresion, contexto, programa))
  }

  private def obtenerVariablesDelContexto(contexto: Contexto, parametros: List[String]) = {
    argumentos.zipWithIndex.map { case (expresion, i) => (parametros(i), expresion.ejecutar(contexto)) }.toMap
  }
}
