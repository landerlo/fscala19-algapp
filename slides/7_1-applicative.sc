// But the operator also works with the "lifted" encodings we have seen
// This is a more flexible algebraic replacement for applicative functors.
// e.g. with Option
import algae.AlgApp.$

// Arithmetic equivalent
{(2+3) * (1+5) == (2 * 1) + (2 * 5) + (3 * 1) + (3 * 5)
}

val repeat: (Int, String) => String = (times, str) => Array.fill(times)(str).mkString

type Option[A] = A | None.type

val a: Option[Int]    = 3
val b: Option[String] = "a" 

val r: String | Double | None.type = repeat $ (a, b)