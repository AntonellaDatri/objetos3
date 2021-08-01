package o3.lepifyo.analizador

import o3.lepifyo.Programa
import o3.lepifyo.expresiones.{Expresion, ExpresionDivision, ExpresionMultiplicacion, ExpresionNumerica, ExpresionResta, ExpresionSuma}
import o3.lepifyo.parser.{ParserLepifyo, TestParser}
import o3.lepifyo.variables.DeclaracionVariable
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class AnalizadorSpec extends AnyFunSpec with Matchers{

  def programa(expresiones: Expresion*): Programa = Programa(expresiones.toList)
  def numero(n: Int): ExpresionNumerica = ExpresionNumerica(n)
  def division(n1: Expresion, n2: Expresion): ExpresionDivision = ExpresionDivision(n1, n2)
  def suma(n1: Expresion, n2: Expresion): ExpresionSuma = ExpresionSuma(n1, n2)
  val parser: ParserLepifyo[Programa, Expresion] = TestParser.get()

  it("uso del analizador con division por cero explicita") {
    val reglas = List(Reglas.divisionCero)
    val analizador = new Analizador()
    val ast = parser.parsear("12/0")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)

    analisis.size should equal(1)
    analisis.head.expresion should equal(ExpresionDivision(ExpresionNumerica(12), ExpresionNumerica(0)))
  }

  it("uso del analizador con division por cero dentro de una suma") {
    val reglas = List(Reglas.divisionCero)
    val analizador = new Analizador()
    val ast = parser.parsear("(1 + 1) + (12/0)")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(1)
  }

  it("uso del analizador con dos errores devuelve dos problemas") {
    val reglas = List(Reglas.divisionCero)
    val analizador = new Analizador()
    val ast = parser.parsear("((12/0)/0)")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(2)
  }

  it("uso del analizador con division por cero implicita") {
    val reglas = List(Reglas.divisionCero)
    val analizador = new Analizador()
    val ast = parser.parsear("(12/(1-1))")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(0)
  }

  it("uso del analizador con division por uno explicito") {
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("12/1")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(1)
    analisis.head.expresion should equal(ExpresionDivision(ExpresionNumerica(12), ExpresionNumerica(1)))
  }

  it("uso del analizador con suma por cero explicito") {
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("12 + 0")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(1)
    analisis.head.expresion should equal(ExpresionSuma(ExpresionNumerica(12), ExpresionNumerica(0)))
  }

  it("uso del analizador con suma por cero explicito es conmutativo") {
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("(0 + 12) + 0")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(2)
  }

  it("uso del analizador con resta por cero explicito") {
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("12 - 0")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(1)
    analisis.head.expresion should equal(ExpresionResta(ExpresionNumerica(12), ExpresionNumerica(0)))
  }

  it("uso del analizador con multplicacion por cero explicito") {
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("12 * 1")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(1)
    analisis.head.expresion should equal(ExpresionMultiplicacion(ExpresionNumerica(12), ExpresionNumerica(1)))
  }

  it("uso del analizador con multplicacion por cero explicito es conmutativo") {
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("1 * (12 * 1)")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(2)
  }

  it("uso del analizador con dos errores en divicion por uno devuelve dos problemas") {
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("((12/1)/1)")
    val analisis : List[Problema] = analizador.analizar(ast, reglas)
    analisis.size should equal(2)
  }

  it("uso del analizador con division por uno implicita") {
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("(12/(5 - 4))")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(0)
  }

  it("uso del analizador con variables duplicadas") {
    val reglas = List(Reglas.variableDuplicada)
    val analizador = new Analizador()
    val ast = parser.parsear("let edad = 20\n let edad = 15")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(1)
    analisis.head.expresion should equal(DeclaracionVariable("edad", ExpresionNumerica(15)))
  }

  it("uso del analizador con variables duplicadas con distintas variables") {
    val reglas = List(Reglas.variableDuplicada)
    val analizador = new Analizador()
    val ast = parser.parsear("let edad = 20\n let edad = 15\nlet nombre = 20\n let nombre = 15")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(2)
  }

  it("uso del analizador con declaracion de variable en la ultima linea") {
    val reglas = List(Reglas.variableUltimaLinea)
    val analizador = new Analizador()
    val ast = parser.parsear("let edad = 20")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(1)
    analisis.head.expresion should equal(DeclaracionVariable("edad", ExpresionNumerica(20)))
  }

  it("uso del anializador con declaracion de variable en la ultima linea  duplicada"){
    val reglas = List(Reglas.variableUltimaLinea)
    val analizador = new Analizador()
    val ast = parser.parsear("let edad = 20\n let edad = 20")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(1)
  }

  it("una variable declarada dentro de una lambda no se considera duplicada"){
    val reglas = List(Reglas.variableDuplicada)
    val analizador = new Analizador()
    val ast = parser.parsear("let edad = 20\n let lambda = () -> {let edad = 20}")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(0)
  }

  it("se detecta una division por cero dentro de una lambda"){
    val reglas = List(Reglas.divisionCero)
    val analizador = new Analizador()
    val ast = parser.parsear("let lambda = () -> {1 / 0}")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(1)
  }

  it("se detecta una operacion redundante dentro de una lambda"){
    val reglas = List(Reglas.opRedundante)
    val analizador = new Analizador()
    val ast = parser.parsear("let lambda = () -> {1 / 1}")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(1)
  }

  it("se detecta una lambda con parámetros con el mismo nombre"){
    val reglas = List(Reglas.lambdaConParametrosDuplicados)
    val analizador = new Analizador()
    val ast = parser.parsear("let lambda = ((n, n) -> n)")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(1)
  }

  it("se detecta una variable como ultima linea adentro de una lambda"){
    val reglas = List(Reglas.variableUltimaLinea)
    val analizador = new Analizador()
    val ast = parser.parsear("let lambda = () -> {\n let edad = 20\n}\nlambda")
    val analisis = analizador.analizar(ast, reglas)

    analisis.size should equal(1)
  }
}
