package o3.lepifyo.parser

import o3.lepifyo.Programa
import o3.lepifyo.expresiones.{Expresion, ExpresionBooleana, ExpresionDistinto, ExpresionDivision, ExpresionIgual, ExpresionMayor, ExpresionMayorIgual, ExpresionMenor, ExpresionMenorIgual, ExpresionMultiplicacion, ExpresionNumerica, ExpresionResta, ExpresionSuma}
import o3.lepifyo.lambda.{AplicacionDeLambda, DeclaracionLambda}
import o3.lepifyo.variables.{AsignacionVariable, DeclaracionVariable, LecturaVariable}

object TestParser {
  def get(): ParserLepifyo[Programa, Expresion] = {
    new ParserLepifyo[Programa, Expresion] (
      programa = (instrucciones: List[Expresion] ) => Programa (instrucciones),
      numero = (n: Int) => ExpresionNumerica (n),
      suma = (a: Expresion, b: Expresion) => ExpresionSuma (a, b),
      resta = (a: Expresion, b: Expresion) => ExpresionResta (a, b),
      multiplicacion = (a: Expresion, b: Expresion) => ExpresionMultiplicacion (a, b),
      division = (a: Expresion, b: Expresion) => ExpresionDivision (a, b),
      booleano = (a: Boolean) => ExpresionBooleana (a),
      igual = (a: Expresion, b: Expresion) => ExpresionIgual (a, b),
      distinto = (a: Expresion, b: Expresion) => ExpresionDistinto (a, b),
      mayor = (a: Expresion, b: Expresion) => ExpresionMayor (a, b),
      mayorIgual = (a: Expresion, b: Expresion) => ExpresionMayorIgual (a, b),
      menor = (a: Expresion, b: Expresion) => ExpresionMenor (a, b),
      menorIgual = (a: Expresion, b: Expresion) => ExpresionMenorIgual (a, b),
      declaracionVariable = (nombre: String, valor: Expresion) => DeclaracionVariable(nombre, valor),
      variable = (nombre: String) => LecturaVariable(nombre),
      asignacion = (nombre: String, expresion: Expresion) => AsignacionVariable(nombre, expresion),
      lambda = (parametros:List[String], cuerpo: List[Expresion]) => DeclaracionLambda(parametros, cuerpo),
      aplicacion = (expresion: Expresion, argumentos:List[Expresion]) => AplicacionDeLambda(expresion, argumentos)
    )
  }
}