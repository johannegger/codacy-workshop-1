package codacy

import scala.reflect.macros.blackbox.Context
import scala.util.{Failure, Success}

object EmailMacro {
  def impl(c: Context)(args: c.Expr[Any]*): c.Expr[EmailAddress] = {
    import c.universe._

    c.prefix.tree match {
        //StringContext -- StringContext.apply
      case q"$_( $_( ${Literal(Constant(const: String))} ))" =>
        EmailAddress.fromString(const) match {
          case Success(email: EmailAddress) =>
            c.Expr[EmailAddress](q"EmailAddress.fromString(${email.value}).get")
          case Failure(err) =>
            c.abort(c.enclosingPosition, err.getMessage)
        }
      case _ => c.abort(c.enclosingPosition, "only one argument supported")
    }
  }
}











/*implicit val liftable = Liftable[codacy.EmailAddress]((email: EmailAddress) =>
  q"EmailAddress.Impl(${email.value})"
)
c.Expr[EmailAddress](q"$email")*/