package codacy

import scala.util.{Failure, Success, Try}

sealed trait EmailAddress extends Any{
  def value:String
  override def toString = value
}

object EmailAddress {

  def fromString(raw:String):Try[EmailAddress] = {
    if(emailRegex.findFirstMatchIn(raw).nonEmpty) Success(Impl(raw))
    else Failure(new Exception(s"not a valid email: $raw"))
  }

  private[this] val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r
  private[this] case class Impl(value:String) extends AnyVal with EmailAddress
}
