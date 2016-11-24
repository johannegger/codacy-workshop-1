package codacy

import scala.reflect.macros.blackbox._

object MaterializerMacro {

  def impl[A](c: Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[Materializer[A]] = {
    import c.universe._

    val A: Type = tag.tpe

    def isApply(methodSymbol: MethodSymbol) = {
      methodSymbol.name == TermName("apply") &&
        methodSymbol.returnType =:= A &&
        methodSymbol.paramLists.length == 1 &&
        methodSymbol.paramLists.headOption.exists(_.nonEmpty)
    }
    //find the apply
    val applyDecl: c.universe.MethodSymbol = A.companion.decls.collectFirst{ case m:MethodSymbol if isApply(m) => m }
      .getOrElse(c.abort(c.enclosingPosition, "no apply found"))

    //for each parameter query the map
    val getters: List[Tree] = applyDecl.paramLists.head.map{ case param: Symbol =>
      val paramName = param.name.decodedName.toString
      q"xpto($paramName) match{ case v:${param.typeSignature} => v }"
    }

    //value here is the same as value in getters!
    val expr = q"""
      Materializer( xpto => $applyDecl( ..$getters ) )
    """

    c.Expr[Materializer[A]](expr)
  }

}
