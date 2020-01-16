

type Option[A] = A | None.type

val sqrt_scala: Double => scala.Option[Double] =
    d => if (d >= 0) Some(Math.sqrt(d)) else None

val sqrt_dotty: Double => Option[Double] =
    d => if (d >= 0) Math.sqrt(d) else None

sqrt_scala(81.0)
sqrt_dotty(81.0)

sqrt_scala(-81.0)
sqrt_dotty(-81.0)