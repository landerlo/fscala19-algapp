



val sqrt: Double => scala.Option[Double] =
    d => if (d >= 0) Some(Math.sqrt(d)) else None
