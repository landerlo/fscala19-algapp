
type Option[A] = A | None.type

val format: Double => String = d => d.toString+"xE0"
val sqrt: Double => Option[Double] = d => if (d >= 0) Math.sqrt(d) else None

import algae.AlgApp.$

val twiceDotty: Double | None.type = sqrt $ { sqrt $ 81.0 }

val a0: Option[Double] = sqrt(-81.0)
sqrt $ a0
val a1: Double  = sqrt $ a0