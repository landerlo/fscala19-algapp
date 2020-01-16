
type Option[A] = A | None.type


summon [
   Option[ Option[Option[Double]]] =:= Option[Double]
]