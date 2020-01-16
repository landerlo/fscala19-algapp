
type Option[A] = A | None.type

val sqrt_scala: Double => scala.Option[Double] = d => if (d >= 0) Some(Math.sqrt(d)) else None
val sqrt: Double => Option[Double] = d => if (d >= 0) Math.sqrt(d) else None

import algae.AlgApp.$

val twiceScala: scala.Option[Double] = 
    scala.Option(81.0).
        flatMap(sqrt_scala).
        flatMap(sqrt_scala)

val twiceDotty = sqrt $ { sqrt $ 81.0 }