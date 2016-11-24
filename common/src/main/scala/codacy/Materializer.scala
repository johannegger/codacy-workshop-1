package codacy

trait Materializer[A]{
  def materialize(value:Map[String,Any]):A
}

object Materializer{
  def apply[A](f: Map[String,Any] => A):Materializer[A] = new Materializer[A] {
    override def materialize(value: Map[String, Any]): A = f(value)
  }
}
