
type Option[A] = A | None.type

val sqrt: Double => Option[Double] = d => if (d >= 0) Math.sqrt(d) else None
val format: Double => String = d => d.toString+"xE0"

import algae.AlgApp.$

val a0: Option[Double]  = sqrt( -81)

format $ "-81.0"
