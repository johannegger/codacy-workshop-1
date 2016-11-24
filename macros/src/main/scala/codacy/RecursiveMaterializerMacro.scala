/*package codacy

import scala.reflect.macros.blackbox._

object RecursiveMaterializerMacro {

  def impl[A](c: Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[RecursiveMaterializer[A]] = {
    import c.universe._

    val A = tag.tpe

    def isApply(methodSymbol: MethodSymbol) = {
      methodSymbol.name == TermName("apply") &&
        methodSymbol.returnType =:= A &&
        methodSymbol.paramLists.length == 1 &&
        methodSymbol.paramLists.head.nonEmpty
    }
    //find the apply
    val applyDecl = A.companion.decls.collectFirst{ case m:MethodSymbol if isApply(m) => m }
      .getOrElse(c.abort(c.enclosingPosition, "no apply found"))

    //for each parameter query the map
    val getters = applyDecl.paramLists.head.map{ case param =>
      val paramName = param.name.decodedName.toString
      q"""implicitly[RecursiveMaterializer[${param.typeSignature}]].materialize(value.get($paramName).getOrElse(RecursiveMaterializer.Empty))"""
    }

    //value here is the same as value in getters!
    val expr = q"""
      RecursiveMaterializer( _ match{
        case RecursiveMaterializer.Object(value) => $applyDecl( ..$getters )
      })
    """

    c.Expr[RecursiveMaterializer[A]](expr)
  }

}*/

/* --- improving the value name:
    val valueAcc = TermName(c.freshName())
    val valueValDef =  q"val $valueAcc = $EmptyTree"
* */