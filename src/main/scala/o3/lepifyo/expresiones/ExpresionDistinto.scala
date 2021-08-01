package o3.lepifyo.expresiones

case class ExpresionDistinto(override val a: Expresion, override val b: Expresion) extends OperacionComparacion(a, b, Operador.!=) {
}
