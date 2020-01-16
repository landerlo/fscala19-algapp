// We also have a arity 2 operation version of the algebraic operation
import algae.AlgApp.$

val repeat: (Int, String) => String = (times, str) => Array.fill(times)(str).mkString

//Here we use it with simple arguments
val a: Int = 3
val b: String = "a" 

repeat(a, b)

{ repeat $ (a, b) }