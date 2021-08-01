package o3.lepifyo.expresiones

case class ExpresionSuma(override val a: Expresion, override val b: Expresion) extends OperacionAritmetica(a, b, Operador.+) {
}
