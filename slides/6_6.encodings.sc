type Either[B, A]    = A | B
type Try[A]          = A | Throwable

case class Negative(neg: Int)
case class Positive(pos: Int)

val toInt: String   => Try[Int]                    = str => scala.util.Try { str.toInt }.fold(identity, identity)
val sign:  Int      => Either[Negative, Positive]  = x => if (x < 0) Negative(x) else Positive(x)
val sqrt:  Positive => Double                      = p => Math.sqrt(p.pos.toDouble)

val input: Either[String, Int] = "81"

import algae.AlgApp.$

val intTry: Either[Try[Int], Int]  = toInt $ input

val signed: Either[Try[Either[Negative, Positive]], Either[Negative, Positive]] = sign $ intTry

//This is still valid, see proof below, but inference gives up here
val sqrtd: Either[Try[Either[Negative, Double]], Either[Negative, Double]] = sqrt $ signed

//proof
summon [
   (Either[Try[Either[Negative, Double]], Either[Negative, Double]])
        =:=
   (Double | Negative | Throwable)
]

//qed. the final type as in the flow diagram
val sqrtdUnion:  Double | Negative | Throwable = sqrt $ signed