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

// Flexible composition of functions enabling better reuse while having control of complex signatures and error recovery
val sqrtFromInput: (String | Int) => Double | Negative | Throwable =
   intOrStr => sqrt $ (sign $ (toInt $ intOrStr))

val abs: Negative => Positive = n => Positive(Math.abs(n.neg))
val sqrtAbs: (String | Int) => Double | Throwable =
   intOrStr => sqrt $ (abs $ (sign $ (toInt $ intOrStr)))

// e.g. we recover from intTry's discouraged use of Try. Anything else is non recoverable
case class NumberFormatError(msg: String)
val betterNFE: Throwable => NumberFormatError = t => if (t.isInstanceOf[NumberFormatException]) NumberFormatError(t.getMessage) else throw new IllegalStateException(t)

val betterSqrtFromInput: (String | Int) => Double | NumberFormatError =
    x => sqrt $ (abs $ (sign $ (betterNFE $ (toInt $ x))))

type Zero = 0.0.type
val swallowNumberFormatErrors: NumberFormatError => Zero = _ => 0.0 //TODO: Try to recover number from the raw input

val zeroDefaultSqrtFromInput: (String | Int) => Double =
    x => swallowNumberFormatErrors $ (sqrt $ (abs $ (sign $ (betterNFE $ (toInt $ x)))))

zeroDefaultSqrtFromInput( 10)
zeroDefaultSqrtFromInput( 81)
zeroDefaultSqrtFromInput(-81)
zeroDefaultSqrtFromInput(  2)
zeroDefaultSqrtFromInput("2")
zeroDefaultSqrtFromInput("x: 2F")