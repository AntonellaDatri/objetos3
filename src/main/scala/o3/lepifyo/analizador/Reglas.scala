package o3.lepifyo.analizador

import o3.lepifyo.expresiones.{Expresion, ExpresionDivision, ExpresionMultiplicacion, ExpresionNumerica, ExpresionResta, ExpresionSuma, OperacionAritmetica}
import o3.lepifyo.lambda.DeclaracionLambda
import o3.lepifyo.variables.DeclaracionVariable

object Reglas extends Enumeration {
  val divisionCero = new Regla ("No se puede dividir por cero", Gravedad.Error, (expresion, _, _) => {
        expresion match{
          case ExpresionDivision(_, ExpresionNumerica(0)) => true
          case _ => false
        }
  })
  val opRedundante = new Regla ("Hay una operacion redundante", Gravedad.Advertencia, (expresion, _, _) => {
    expresion match {
      case ExpresionDivision(_, ExpresionNumerica(1)) => true
      case ExpresionResta(_, ExpresionNumerica(0)) => true
      case ExpresionMultiplicacion(_, ExpresionNumerica(1)) => true
      case ExpresionMultiplicacion(ExpresionNumerica(1), _) => true
      case ExpresionSuma(_, ExpresionNumerica(0)) => true
      case ExpresionSuma(ExpresionNumerica(0), _) => true
      case _ => false
    }
  })
  val variableDuplicada = new Regla ("La variable está duplicada", Gravedad.Error, (expresion, contexto, _) => {
    expresion match{
      case DeclaracionVariable(nombre, _) => contexto.contieneVariable(nombre)
      case _ => false
    }
  })
  val variableUltimaLinea = new Regla ("La ultima linea deberia ser una expresión", Gravedad.Advertencia, (expresion, _, programa) => {
    expresion match {
      case declaracion: DeclaracionVariable => declaracion.eq(programa.instrucciones.last)
      case _ => false
    }
  })

  val lambdaConParametrosDuplicados = {
    new Regla("Una lambda no puede tener parámetros con el mismo nombre", Gravedad.Error, (expresion, _, programa) => {
      expresion match {
        case DeclaracionLambda(parametros, _) => parametros.distinct.size != parametros.size
        case _ => false
      }
    })
  }
}

