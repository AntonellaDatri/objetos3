package o3.lepifyo.expresiones

case class ExpresionMayorIgual(override val a: Expresion, override val b: Expresion) extends OperacionComparacion(a, b, Operador.>=) {
}
