package o3.lepifyo.interprete

import o3.lepifyo.Programa
import o3.lepifyo.expresiones.Expresion
import o3.lepifyo.parser.{ParserLepifyo, TestParser}
import o3.lepifyo.valores.{ValorBooleano, ValorNumerico}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class InterpreteSpec extends AnyFunSpec with Matchers {

  val parser: ParserLepifyo[Programa, Expresion] = TestParser.get()
  val interprete = new InterpreteLepifyo()

  describe("Intérprete") {

    describe("con números") {
      it("interpreta numeros literales") {
        val resultado: Any = ejecutar("2")

        assert(resultado == ValorNumerico(2))
      }

      it("interpreta sumas de números literales") {
        val resultado: Any = ejecutar("2 + 1")

        assert(resultado == ValorNumerico(3))
      }

      it("interpreta sumas de sumas") {
        val resultado: Any = ejecutar("(1 + 1) + (2 + 2)")

        assert(resultado == ValorNumerico(6))
      }

      it("interpreta restas de números literales") {
        val resultado: Any = ejecutar("2 - 1")

        assert(resultado == ValorNumerico(1))
      }

      it("interpreta restas de restas") {
        val resultado: Any = ejecutar("(3 - 1) - (2 - 1)")

        assert(resultado == ValorNumerico(1))
      }

      it("interpreta multiplicaciones de números literales") {
        val resultado: Any = ejecutar("2 * 3")

        assert(resultado == ValorNumerico(6))
      }

      it("interpreta multiplicaciones de multiplicaciones") {
        val resultado: Any = ejecutar("(2 * 3) * (2 * 2)")

        assert(resultado == ValorNumerico(24))
      }

      it("interpreta divisiones de números literales") {
        val resultado: Any = ejecutar("4 / 2")

        assert(resultado == ValorNumerico(2))
      }

      it("interpreta divisiones de divisiones") {
        val resultado: Any = ejecutar("(18 / 2) / (9 / 3)")

        assert(resultado == ValorNumerico(3))
      }

      it("interpreta igualdad de numeros iguales") {
        val resultado = ejecutar("1 == 1")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta igualdad de numeros distintos") {
        val resultado = ejecutar("1 == 3")

        assert(resultado == ValorBooleano(false))
      }

      it("interpreta igualdad de una suma y un numero literal") {
        val resultado = ejecutar("(1 + 2) == 3")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta distincion de numeros iguales") {
        val resultado = ejecutar("1 != 1")

        assert(resultado == ValorBooleano(false))
      }

      it("interpreta distincion de numeros distintos") {
        val resultado = ejecutar("1 != 3")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta que un numero literal es mayor que otro") {
        val resultado = ejecutar("3 > 1")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta que un numero literal no es mayor que otro") {
        val resultado = ejecutar("1 > 3")

        assert(resultado == ValorBooleano(false))
      }

      it("interpreta que un numero literal es mayor o igual que otro") {
        val resultado = ejecutar("3 >= 1")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta que otro numero literal es mayor o igual que otro") {
        val resultado = ejecutar("3 >= 3")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta que un numero literal no es mayor o igual que otro") {
        val resultado = ejecutar("2 >= 3")

        assert(resultado == ValorBooleano(false))
      }

      it("interpreta que un numero literal es menor que otro") {
        val resultado = ejecutar("1 < 3")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta que un numero literal no es menor que otro") {
        val resultado = ejecutar("3 < 1")

        assert(resultado == ValorBooleano(false))
      }

      it("interpreta que un numero literal es menor o igual que otro") {
        val resultado = ejecutar("1 <= 3")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta que otro numero literal es menor o igual que otro") {
        val resultado = ejecutar("3 <= 3")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta que un numero literal no es menor o igual que otro") {
        val resultado = ejecutar("3 <= 2")

        assert(resultado == ValorBooleano(false))
      }
    }

    describe("con booleanos") {
      it("interpreta booleanos literales") {
        val resultado = ejecutar("true")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta igualdad de booleanos iguales") {
        val resultado = ejecutar("true == true")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta igualdad de booleanos distintos") {
        val resultado = ejecutar("true == false")

        assert(resultado == ValorBooleano(false))
      }

      it("interpreta distincion de booleanos iguales") {
        val resultado = ejecutar("true != true")

        assert(resultado == ValorBooleano(false))
      }

      it("interpreta distincion de booleanos distintos") {
        val resultado = ejecutar("true != false")

        assert(resultado == ValorBooleano(true))
      }

      it("no interpreta que un booleano sea mayor que otro") {
        assertThrows[RuntimeException] {ejecutar("true > false") }
      }

      it("no interpreta que un booleano sea menor que otro") {
        assertThrows[RuntimeException] {ejecutar("true < false") }
      }

      it("no interpreta que un booleano sea mayor o igual que otro") {
        assertThrows[RuntimeException] {ejecutar("true >= false") }
      }

      it("no interpreta que un booleano sea menor o igual que otro") {
        assertThrows[RuntimeException] {ejecutar("true <= false") }
      }

      it("no interpreta la suma de booleanos") {
        assertThrows[RuntimeException] {ejecutar("true + false") }
      }

      it("no interpreta la resta de booleanos") {
        assertThrows[RuntimeException] {ejecutar("true - false") }
      }

      it("no interpreta la multplicacion de booleanos") {
        assertThrows[RuntimeException] {ejecutar("true * false") }
      }

      it("no interpreta la division de booleanos") {
        assertThrows[RuntimeException] {ejecutar("true / false") }
      }
    }

    describe("con variables") {
      it("interpreta la declaracion de una variable con un número literal") {
        val resultado = ejecutar("let edad = 27")

        assert(resultado == ValorNumerico(27))
      }

      it("interpreta la declaracion de una variable con una suma") {
        val resultado = ejecutar("let edad = 2 + 2")

        assert(resultado == ValorNumerico(4))
      }

      it("interpreta la lectura de una variable") {
        val resultado = ejecutar("let edad = 24\nlet otraEdad = edad")

        assert(resultado == ValorNumerico(24))
      }

      it("interpreta la declaracion de una variable compuesta por un numero y una variable") {
        val resultado = ejecutar("let edad = 24\nlet añoNacimiento = 2021 - edad")

        assert(resultado == ValorNumerico(1997))
      }

      it("interpreta la declaracion de una variable compuesta por una variable y un numero") {
        val resultado = ejecutar("let edad = 24\nlet edadEnDiezAños = edad + 10")

        assert(resultado == ValorNumerico(34))
      }

      it("interpreta la lectura de multiples variables") {
        val resultado = ejecutar("let añoActual = 2021\nlet edad = 25\nlet añoNacimiento = añoActual - edad")

        assert(resultado == ValorNumerico(1996))
      }

      it("interpreta la escritura de una variable con un valor literal") {
        val resultado = ejecutar("\nlet añoActual = 2020\nañoActual = 2021\n")

        assert(resultado == ValorNumerico(2021))
      }

      it("interpreta la escritura de una variable con una suma de variable y valor literal") {
        val resultado = ejecutar("\nlet añoActual = 2021\nañoActual = añoActual + 1\n")

        assert(resultado == ValorNumerico(2022))
      }

      it("interpreta la escritura de una variable con una suma de variable y variable") {
        val resultado = ejecutar("\nlet añoActual = 2021\nlet uno = 1\nañoActual = añoActual + uno\n")

        assert(resultado == ValorNumerico(2022))
      }

      it("el interprete no modifica una variable a la que no se le hace una asignación") {
        val resultado = ejecutar("\nlet añoActual = 2021\nlet uno = 1\nañoActual = añoActual + uno\nuno")

        assert(resultado == ValorNumerico(1))
      }

      it("interpreta la escritura de una variable de igualdad entre booleanos") {
        val resultado = ejecutar("\nlet variableTrue = true\nvariableTrue = variableTrue == true")

        assert(resultado == ValorBooleano(true))
      }

      it("interpreta la escritura de una variable de diferencia entre booleanos") {
        val resultado = ejecutar("\nlet variableBooleana = 2 > 1\nvariableBooleana = variableBooleana != true")

        assert(resultado == ValorBooleano(false))
      }

      it("interpreta la escritura de una variable cambiandole el tipo") {
        val resultado = ejecutar("\nlet variable = 1\nvariable = true")

        assert(resultado == ValorBooleano(true))
      }

      it("no logra interpretar cuando se intenta leer una variable que no existe") {
        assertThrows[RuntimeException] {ejecutar("\nañoActual")}
      }

      it("no logra interpretar cuando se intenta leer una variable que no se crea en la proxima linea") {
        assertThrows[RuntimeException] {ejecutar("añoActual\n let añoActual = 2021")}
      }


      it("no logra interpretar cuando se asigna un valor en una variable que no existe") {
        assertThrows[RuntimeException] {ejecutar("\nañoActual = 2021")}
      }
    }

    describe("con lambdas"){
      it("ejecutar lamba con un parametro"){
        val rto = ejecutar("let sumar = (n) -> n + 2\n let resultado = sumar(0)")
        assert(rto == ValorNumerico(2))
      }
      it("ejecutar lambda sin parametros"){
        val rto = ejecutar("let devolverY = () -> {\n  let y = 1\n  y = y + 1\n  y\n} \n devolverY()")
        assert(rto == ValorNumerico(2))
      }

      it("ejecutar lamba con variables del contexto"){
        val resultado = ejecutar("let x = 1\n\nlet devolverX = () -> x\ndevolverX()")
        assert(resultado == ValorNumerico(1))
      }

      it("Los parámetros de la lambda ocultan a las variables del contexto"){
        val resultado = ejecutar("let x = 1\nlet devolverX = (x) -> x\ndevolverX(2)")
        assert(resultado == ValorNumerico(2))
      }

      it("Los parámetros de la lambda no cambian a las variables del contexto"){
        val resultado = ejecutar("let x = 1\nlet devolverX = (x) -> x\ndevolverX(2)\nx")
        assert(resultado == ValorNumerico(1))
      }

      it("Se pueden modificar variables del contexto"){
        val resultado = ejecutar("let x = 1\n\nlet modificarX = (y) -> {\n  x = y\n}\nmodificarX(3)\nx")
        assert(resultado == ValorNumerico(3))
      }

      it("Se pueden definir y modificar variables locales dentro de la lambda:"){
        val resultado = ejecutar("let devolverY = () -> {\n  let y = 1\n  y = y + 1\n  y\n}\ndevolverY()")
        assert(resultado == ValorNumerico(2))
      }

      it("Las variables locales ocultan a las variables del contexto en el que se definió la lambda"){
        val resultado = ejecutar("let x = 1\n\nlet devolverX = () -> {\n  let x = 2\n  x = x + 1\n  x\n}\ndevolverX()")
        assert(resultado == ValorNumerico(3))
      }

      it("Las variables locales no modifican a las variables del contexto"){
        val resultado = ejecutar("let x = 1\n\nlet devolverX = () -> {\n  let x = 2\n  x = x + 1\n  x\n}\ndevolverX()\nx")
        assert(resultado == ValorNumerico(1))
      }

      it("Ejecucion de lambdas anidados"){
        val resultado = ejecutar("let sumar = (x) -> (y) -> x + y\nsumar(2)(1)")
        assert(resultado == ValorNumerico(3))
      }

      it("Ejecucion de lambdas anidados con variables del contexto"){
        val resultado = ejecutar("let x = 1\nlet sumar = (x) -> (y) -> x + y\nsumar(2)(x)")
        assert(resultado == ValorNumerico(3))
      }

      it("asd"){
        val resultado = ejecutar("let sumar = () -> {\nlet x = 1\nlet lambda = (y) -> x + y\nlambda\n}\nsumar()(1)")
        assert(resultado == ValorNumerico(2))
      }

      it("Se pueden acceder a las variables definidas en cualquier contexto que sea ancestro del contexto de la lambda que se aplica"){
        val resultado = ejecutar("let x = 2\nlet sumarAX = (y) -> {\n  ((y) -> x + y - 1)(y + 1)\n}\nsumarAX(3)")
        assert(resultado == ValorNumerico(5))
      }

      it("al aplicar un lambda, sus argumentos se evalúan de izquierda a derecha"){
        val resultado = ejecutar("let x = 1\nlet modificarXYDevolverSucesor = (y) -> {\n  x = y\n  y + 1\n}\nlet sumar = (x) -> (y) -> x + y\nlet aplicarA = (f, x) -> {\n  f(x)\n}\naplicarA(sumar(modificarXYDevolverSucesor(2)), x)")
        assert(resultado == ValorNumerico(5))
      }

      it("Las variables definidas dentro del lambda no se pueden acceder desde fuera"){
        assertThrows[RuntimeException] {ejecutar("let devolverY = () -> {\n  let y = 1\n  y = y + 1\n  y\n}\ndevolverY()\ny")}
      }

      it("no se puede ejecutar lambda con lectura de variable antes de declararla"){
        assertThrows[RuntimeException] {ejecutar("let devolverY = () -> {y \nlet y = 1\n} \n devolverY()")}
      }

    }
  }

  private def ejecutar(programa: String) = {
    val ast = parser.parsear(programa)
    interprete.ejecutar(ast)
  }
}
