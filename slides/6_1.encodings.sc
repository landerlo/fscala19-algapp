type Either[B, A]    = A | B
type Try[A]          = A | Throwable

case class Negative(neg: Int)
case class Positive(pos: Int)

val toInt: String   => Try[Int]                    = str => scala.util.Try { str.toInt }.fold(identity, identity)
val sign:  Int      => Either[Negative, Positive]  = x => if (x < 0) Negative(x) else Positive(x)
val sqrt:  Positive => Double                      = p => Math.sqrt(p.pos.toDouble)

val input: String = "-81"

val intTry: Try[Int] = toInt(input)

import algae.AlgApp.$

val signed: Try[Either[Negative, Positive]] =  sign $ intTry

 sqrt $ signed
