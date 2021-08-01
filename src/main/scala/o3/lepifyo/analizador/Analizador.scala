package o3.lepifyo.analizador

import o3.lepifyo.Programa
import o3.lepifyo.contexto.{Contexto, ContextoAnalizador}
import o3.lepifyo.expresiones.{Expresion, OperacionAritmetica, OperacionComparacion}

class Analizador {

  def analizar(programa : Programa, reglas : List[Regla]): List[Problema] ={
    val contexto = new ContextoAnalizador()
    programa.instrucciones.flatMap(expresion => reglas.flatMap(regla => {
      expresion.analizar(regla, contexto, programa)
    }))
  }

}
