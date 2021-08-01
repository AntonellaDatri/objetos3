package o3.lepifyo.expresiones

import o3.lepifyo.parser.ParserLepifyo
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UsoParserSpec extends AnyFunSpec with Matchers {
  it("uso del parser con números literales") {
      type Programa = List[Expresion]

      def programa(expresiones: Expresion*) = expresiones.toList
      def numero(n: Int) = ExpresionNumerica(n)

      val parser = new ParserLepifyo[Programa, Expresion](
        programa = programa,
        numero = numero,
      )

      val ast = parser.parsear("12")

      ast should equal(programa(numero(12)))
  }

  it("uso del parser con una suma de números literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = ExpresionNumerica(n)
    def suma(n1: Expresion, n2: Expresion) = ExpresionSuma(n1, n2)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      suma = suma,
    )

    val ast = parser.parsear("1 + 1")

    ast should equal(programa(suma(numero(1), numero(1))))
  }

  it("uso del parser con una suma de sumas") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = ExpresionNumerica(n)
    def suma(n1: Expresion, n2: Expresion) = ExpresionSuma(n1, n2)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      suma = suma,
    )

    val ast = parser.parsear("1 + 1 + 1")

    ast should equal(programa(suma(suma(numero(1), numero(1)), numero(1))))
  }

  it("uso del parser con una resta de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = ExpresionNumerica(n)
    def resta(n1: Expresion, n2: Expresion) = ExpresionResta(n1, n2)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      resta = resta,
    )

    val ast = parser.parsear("1 - 1")

    ast should equal(programa(resta(numero(1), numero(1))))
  }

  it("uso del parser con una resta de restas") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = ExpresionNumerica(n)
    def resta(n1: Expresion, n2: Expresion) = ExpresionResta(n1, n2)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      resta = resta,
    )

    val ast = parser.parsear("1 - 1 - 1")

    ast should equal(programa(resta(resta(numero(1), numero(1)), numero(1))))
  }

  it("uso del parser con una multiplicacion de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = ExpresionNumerica(n)
    def multiplicacion(n1: Expresion, n2: Expresion) = ExpresionMultiplicacion(n1, n2)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      multiplicacion = multiplicacion,
    )

    val ast = parser.parsear("2 * 3")

    ast should equal(programa(multiplicacion(numero(2), numero(3))))
  }

  it("uso del parser con una multiplicacion de multiplicaciones") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = ExpresionNumerica(n)
    def multiplicacion(n1: Expresion, n2: Expresion) = ExpresionMultiplicacion(n1, n2)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      multiplicacion = multiplicacion,
    )

    val ast = parser.parsear("2 * 3 * 4")

    ast should equal(programa(multiplicacion(multiplicacion(numero(2), numero(3)), numero(4))))
  }

  it("uso del parser con una division de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = ExpresionNumerica(n)
    def division(n1: Expresion, n2: Expresion) = ExpresionDivision(n1, n2)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      division = division,
    )

    val ast = parser.parsear("6 / 2")

    ast should equal(programa(division(numero(6), numero(2))))
  }

  it("uso del parser con una division de divisiones") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = ExpresionNumerica(n)
    def division(n1: Expresion, n2: Expresion) = ExpresionDivision(n1, n2)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      division = division,
    )

    val ast = parser.parsear("18 / 2 / 3")

    ast should equal(programa(division(division(numero(18), numero(2)), numero(3))))
  }

  it("uso del parser con un booleano true") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def booleano(b: Boolean) = ExpresionBooleana(b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      booleano = booleano,
    )

    val ast = parser.parsear("true")

    ast should equal(programa(booleano(true)))
  }

  it("uso del parser con un booleano false") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def booleano(b: Boolean) = ExpresionBooleana(b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      booleano = booleano,
    )

    val ast = parser.parsear("false")

    ast should equal(programa(booleano(false)))
  }

  it("uso del parser con una comparacion de booleanos true") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def booleano(b: Boolean) = ExpresionBooleana(b)
    def igual(a: Expresion, b: Expresion) = ExpresionIgual(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      booleano = booleano,
      igual = igual,
    )

    val ast = parser.parsear("true == true")

    ast should equal(programa(igual(booleano(true), booleano(true))))
  }

  it("uso del parser con una comparacion de booleanos false") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def booleano(b: Boolean) = ExpresionBooleana(b)
    def igual(a: Expresion, b: Expresion) = ExpresionIgual(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      booleano = booleano,
      igual = igual,
    )

    val ast = parser.parsear("false == false")

    ast should equal(programa(igual(booleano(false), booleano(false))))
  }

  it("uso del parser con una comparacion de booleanos true y false") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def booleano(b: Boolean) = ExpresionBooleana(b)
    def igual(a: Expresion, b: Expresion) = ExpresionIgual(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      booleano = booleano,
      igual = igual,
    )

    val ast = parser.parsear("true == false")

    ast should equal(programa(igual(booleano(true), booleano(false))))
  }

  it("uso del parser con una comparacion de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def booleano(a: Boolean): Expresion = ExpresionBooleana(a)
    def igual(a: Expresion, b: Expresion): Expresion = ExpresionIgual(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      booleano = booleano,
      igual = igual,
    )

    val ast = parser.parsear("1 == 1")

    ast should equal(programa(igual(numero(1), numero(1))))
  }

  it("uso del parser con una distincion de booleanos true y false") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def booleano(b: Boolean) = ExpresionBooleana(b)
    def distinto(a: Expresion, b: Expresion) = ExpresionDistinto(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      booleano = booleano,
      distinto = distinto,
    )

    val ast = parser.parsear("true != false")

    ast should equal(programa(distinto(booleano(true), booleano(false))))
  }

  it("uso del parser con una distincion de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def distinto(a: Expresion, b: Expresion) = ExpresionDistinto(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      distinto = distinto,
    )

    val ast = parser.parsear("1 != 2")

    ast should equal(programa(distinto(numero(1), numero(2))))
  }

  it("uso del parser con una comparacion mayor de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def mayor(a: Expresion, b: Expresion) = ExpresionMayor(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      mayor = mayor,
    )

    val ast = parser.parsear("1 > 2")

    ast should equal(programa(mayor(numero(1), numero(2))))
  }

  it("uso del parser con otra comparacion mayor de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def mayor(a: Expresion, b: Expresion) = ExpresionMayor(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      mayor = mayor,
    )

    val ast = parser.parsear("2 > 1")

    ast should equal(programa(mayor(numero(2), numero(1))))
  }

  it("uso del parser con una comparacion menor de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def menor(a: Expresion, b: Expresion) = ExpresionMenor(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      menor = menor,
    )

    val ast = parser.parsear("2 < 1")

    ast should equal(programa(menor(numero(2), numero(1))))
  }

  it("uso del parser con otra comparacion menor de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def menor(a: Expresion, b: Expresion) = ExpresionMenor(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      menor = menor,
    )

    val ast = parser.parsear("1 < 2")

    ast should equal(programa(menor(numero(1), numero(2))))
  }

  it("uso del parser con una comparacion mayor igual de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def mayorIgual(a: Expresion, b: Expresion) = ExpresionMayorIgual(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      mayorIgual = mayorIgual,
    )

    val ast = parser.parsear("1 >= 2")

    ast should equal(programa(mayorIgual(numero(1), numero(2))))
  }

  it("uso del parser con otra comparacion mayor igual de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def mayorIgual(a: Expresion, b: Expresion) = ExpresionMayorIgual(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      mayorIgual = mayorIgual,
    )

    val ast = parser.parsear("2 >= 1")

    ast should equal(programa(mayorIgual(numero(2), numero(1))))
  }

  it("uso del parser con una comparacion menor igual de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def menorIgual(a: Expresion, b: Expresion) = ExpresionMenorIgual(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      menorIgual = menorIgual,
    )

    val ast = parser.parsear("1 <= 2")

    ast should equal(programa(menorIgual(numero(1), numero(2))))
  }

  it("uso del parser con otra comparacion menor igual de numeros literales") {
    type Programa = List[Expresion]

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int): Expresion = ExpresionNumerica(n)
    def menorIgual(a: Expresion, b: Expresion) = ExpresionMenorIgual(a, b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
      menorIgual = menorIgual,
    )

    val ast = parser.parsear("2 <= 1")

    ast should equal(programa(menorIgual(numero(2), numero(1))))
  }
}
