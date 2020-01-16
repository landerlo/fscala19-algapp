
import algae.AlgApp.$

// The flexibility is exemplified by mixing Either and Option encodings in the same call

type Either[A, B] = A | B
type Option[A] = Either[A, None.type]

val repeat: (Int, String) => String = (times, str) => Array.fill(times)(str).mkString

val falsy: Either[Boolean, Int]  = false
val two:   Either[Boolean, Int]  = 2
val A:     Option[String] = "A" 
val non:   Option[String] = None

val resFalse: String | None.type | Boolean = repeat $ (falsy, A)
val resAA:    String | None.type | Boolean = repeat $ (two, A)
val resNone:  String | None.type | Boolean = repeat $ (two, non)