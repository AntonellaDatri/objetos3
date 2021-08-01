package o3.lepifyo.interprete

import o3.lepifyo.Programa
import o3.lepifyo.contexto.Contexto
import o3.lepifyo.expresiones.Expresion

class InterpreteLepifyo() {
  def ejecutar(programa: Programa): Any = {
    val contexto = new Contexto()
    val valores = programa.instrucciones.map(expresion => expresion.ejecutar(contexto))
    valores.last
  }

}
