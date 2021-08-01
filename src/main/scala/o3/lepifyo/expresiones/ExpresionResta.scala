package o3.lepifyo.expresiones

case class ExpresionResta(override val a: Expresion, override val b: Expresion) extends OperacionAritmetica(a, b, Operador.-) {
}
