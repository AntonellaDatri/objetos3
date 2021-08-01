package o3.lepifyo.valores
import o3.lepifyo.expresiones.{Expresion, ExpresionBooleana}

case class ValorBooleano(valor: Boolean) extends Valor {
  override def toExpresion(): Expresion = ExpresionBooleana(valor)
}
