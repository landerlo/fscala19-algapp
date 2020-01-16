
type Option[A] = A | None.type

val sqrt: Double => Option[Double] =
    d => if (d >= 0) Math.sqrt(d) else None

sqrt(sqrt(81.0)) // cannot work with the standard function application