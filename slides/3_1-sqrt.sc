

type Option[A] = A | None.type

val sqrt: Double => Option[Double] =
    d => if (d >= 0) Math.sqrt(d) else None
