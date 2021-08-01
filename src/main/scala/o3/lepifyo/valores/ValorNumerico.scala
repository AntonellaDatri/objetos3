package o3.lepifyo.valores
import o3.lepifyo.expresiones.{Expresion, ExpresionNumerica}

case class ValorNumerico(valor: Int) extends Valor {
  override def toExpresion(): Expresion = ExpresionNumerica(valor)

  def +(sumando: ValorNumerico): ValorNumerico = {
    ValorNumerico(valor + sumando.valor)
  }

  def -(restando: ValorNumerico): ValorNumerico = {
    ValorNumerico(valor - restando.valor)
  }

  def *(factor: ValorNumerico): ValorNumerico = {
    ValorNumerico(valor * factor.valor)
  }

  def /(divisor: ValorNumerico): ValorNumerico = {
    ValorNumerico(valor / divisor.valor)
  }

  def <(otroValor: ValorNumerico): ValorBooleano = {
    ValorBooleano(valor < otroValor.valor)
  }

  def <=(otroValor: ValorNumerico): ValorBooleano = {
    ValorBooleano(valor <= otroValor.valor)
  }

  def >(otroValor: ValorNumerico): ValorBooleano = {
    ValorBooleano(valor > otroValor.valor)
  }

  def >=(otroValor: ValorNumerico): ValorBooleano = {
    ValorBooleano(valor >= otroValor.valor)
  }
}
