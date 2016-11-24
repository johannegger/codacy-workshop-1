package codacy

case class Foo(s:String, i:Int)

object Foo{
  import scala.language.experimental.macros
  def materializer:Materializer[Foo] = macro MaterializerMacro.impl[Foo]
}

object Main {

  def main(args: Array[String]): Unit = {
    args.headOption.collect{ case "email" => ex1 }.getOrElse( ex0 )
  }

  def ex0 = {
    val value = Map( "i" -> 42)
    val myFoo = Foo.materializer.materialize(value)
    println(myFoo)
  }

  def ex1 = {
    import EmailHelpers._
    val myEmail: EmailAddress = email"johann@codacy.com"
    println(myEmail)
  }

}

object EmailHelpers {

  import scala.language.experimental.macros
  implicit class EmailSC(val sc: StringContext) extends AnyVal {
    def email(args: Any*): EmailAddress = macro EmailMacro.impl
  }

}