package algae
import scala.reflect.ClassTag
import scala.language.implicitConversions

/*
 * Experimental algebraic application where in the face of a union the function application
 * succeeds if any of branches of the disjunction could be applied.
 * e.g. with f: A -> R, x: A | B
 *    f(x) is still valid as we know we it must result on R | B
 *  val y: R | B = f(x) // compiles
 */
object AlgApp {
  /* This experimental implementation has the usual caveats of ClassTag where HKT would result in unsafe behaviour
   * e.g. sum: List[Int] => Int, will accept List[String]
   * Contravariance will be an issue. A sound implementation will require macros to overcome the issues with this POC approach.
   */
  def algApp[A: ClassTag, B, E, X <: (A | E)](f: A => B)(x: X)(implicit applyableEv: A <:< X): (B | E) = x match {
      case a: A => f(a)
      case e: E => e
  } 
  
  // $ extension syntax
  inline def [A: ClassTag, B, E, X <: (A | E)](f: A => B) $ (x: X)(implicit applyableEv: A <:< X): (B | E) = algApp[A, B, E, X](f)(x)

  // This $ extension method applies for functions of arity 2. See 7- section slides for Applicative
  def [A1: ClassTag, A2: ClassTag, B, E1, E2, X1 <: (A1 | E1), X2 <: (A2 | E2)](
    f: (A1, A2) => B) $ (x: (X1, X2))
    (implicit apEv1: A1 <:< X1, apEv2: A2 <:< X2): (B | E1 | E2) = {
    x match {
      case (a1: A1, a2: A2) => f(a1, a2)
      case (a1: A1, e2: E2) => e2
      case (e1: E1, a2: A2) => e1
      case (e1: E1, e2: E2) => e1
    }
  }
}
