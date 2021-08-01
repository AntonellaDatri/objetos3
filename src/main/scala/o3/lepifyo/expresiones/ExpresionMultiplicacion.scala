package o3.lepifyo.expresiones

case class ExpresionMultiplicacion(override val a: Expresion, override val b: Expresion) extends OperacionAritmetica(a, b, Operador.*) {
}
