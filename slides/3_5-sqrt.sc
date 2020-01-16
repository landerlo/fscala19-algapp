
type Option[A] = A | None.type

val sqrt: Double => Option[Double] = d => if (d >= 0) Math.sqrt(d) else None
val format: Double => String = d => d.toString+"xE0"

val a0: Option[Double]  = sqrt( 81)

import algae.AlgApp.$

println(a0)

format(a0) // KO
