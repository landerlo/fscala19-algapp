

type Option[A] = A | None.type

val sqrt_scala: Double => scala.Option[Double] =
    d => if (d >= 0) Some(Math.sqrt(d)) else None

val sqrt_dotty: Double => Option[Double] =
    d => if (d >= 0) Math.sqrt(d) else None
