package o3.lepifyo.valores
import o3.lepifyo.contexto.Contexto
import o3.lepifyo.expresiones.Expresion
import o3.lepifyo.lambda.DeclaracionLambda

case class ValorLambda(parametros:List[String], cuerpo: List[Expresion], contexto: Contexto) extends Valor{
  override def toExpresion(): Expresion = DeclaracionLambda(parametros, cuerpo)
}
