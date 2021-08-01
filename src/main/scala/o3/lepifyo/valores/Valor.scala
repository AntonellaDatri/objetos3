package o3.lepifyo.valores

import o3.lepifyo.expresiones.Expresion

trait Valor {
  def toExpresion(): Expresion
}
