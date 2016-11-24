package codacy

/*

sealed trait Value
object Value{
  def apply(value:Map[String,Value]):Value = new Obj(value)
  def apply(value:Int):Value = new Intt(value)
  def apply(value:String):Value = new Str(value)

  implicit class Obj(val value: Map[String,Value]) extends Value
  object Obj{ def unapply(arg: Value): Option[Map[String,Value]] = Option(arg).collect{ case obj:Obj => obj.value } }

  implicit class Str(val value: String) extends Value
  object Str{ def unapply(arg: Value): Option[String] = Option(arg).collect{ case obj:Str => obj.value } }

  implicit class Intt(val value: Int) extends Value
  object Intt{ def unapply(arg: Value): Option[Int] = Option(arg).collect{ case obj:Intt => obj.value } }

  case object Empty extends Value
}

trait RecursiveMaterializer[A]{
  def materialize(value:Value):A
}

object RecursiveMaterializer{

  def apply[A](f: Value => A):RecursiveMaterializer[A] = new RecursiveMaterializer[A] {
    override def materialize(value: Value): A = f(value)
  }

  implicit object StringMaterializer extends RecursiveMaterializer[String] {
    override def materialize(value: Value): String = value match{ case Value.Str(value) => value }
  }

  implicit object InttMaterializer extends RecursiveMaterializer[Int] {
    override def materialize(value: Value): Int = value match{ case Value.Intt(value:Int) => value }
  }
}
*/