
type Option[A]       = A | None.type

type Either[A, B]    = A | B

type Validated[E, A] = A | E

type Try[A] = A | Throwable