package algae

import org.junit.Test
import org.junit.Assert._

import algae.AlgApp._

class SingleTypeApplication {
  import AlgAppTest.length

  @Test def testOkApply(): Unit = {
    assertEquals("I was compiled by dotty :)".length, length $ "I was compiled by dotty :)")
  }
  
  @Test def testIncorrectApply(): Unit = {
//    assertEquals(length $ 555, "I was compiled by dotty :)".length)
  }

}

class ApplicationWithSubtyping {
  trait Obj

  trait Num extends Obj { def n: Int }
  
  case object Negative

  case class Even(k: Int) extends Num { def n: Int = 2 * k }
  
  def nAAs(n: Num): String = List.fill(n.n)("A").mkString

  @Test def testOkApply(): Unit = {
    assertEquals("AAAA", nAAs $ Even(2))

    val y: Obj = new Obj {}
    val yObj: Obj = new Obj {}
    val yn: Obj | Num = Even(8) 

    println(nAAs $ yObj)
    println(nAAs $ yn)
  }

  @Test def testInputIsDisjoint(): Unit = {
    val length: String => Int = (s: String) => s.length
    val positive: Num => Num | Negative.type = n => if (n.n < 0) Negative else n
    val x: Num | String = Even(2)
     nAAs $ x
     val r: Int = length $ (nAAs $ x)
     println(s"R: $r")
     val r2: Int | Negative.type = length $ (nAAs $ (positive $ x))
     println(s"R2: $r2")

     val str: Num | String = "STR"
     val r3: Int | Negative.type = length $ (nAAs $ (positive $ str))
  }
}
class Function2Tests {
  val repeat: (Int, String) => String = (times, str) => Array.fill(times)(str).mkString
  @Test def simpleApplication(): Unit = {
    val result: String = repeat $ (3, "bb") 
    assertEquals(repeat(3, "bb"), result)
  }
  @Test def incorrectApplication(): Unit = {
 //FAILS as expected:   repeat $ (List(), 7.0) 
  }
  @Test def maybeApplicationApplies(): Unit = {
    val a: Int | None.type = 3
    val b: String | None.type = "3"
    val result: String | None.type = repeat $ (a, b) 
    assertEquals(repeat(3, "3"), result)
  }
  @Test def maybeApplicationFirstNone(): Unit = {
    val a: Int | None.type = None
    val b: String | None.type = "3"
    val result: String | None.type = repeat $ (a, b) 
    assertEquals(None, result)
  }
  @Test def maybeApplicationSecondNone(): Unit = {
    val a: Int | None.type = 3
    val b: String | None.type = None 
    val result: String | None.type = repeat $ (a, b) 
    assertEquals(None, result)
  }
  @Test def maybeApplicationBothNone(): Unit = {
    val a: Int | None.type = None
    val b: String | None.type = None 
    val result: String | None.type = repeat $ (a, b) 
    assertEquals(None, result)
  }
  @Test def maybeApplicationOnOneApplies(): Unit = {
    val a: Int = 3
    val b: String | None.type = "b" 
    val result: String | None.type = repeat $ (a, b)
    assertEquals("bbb", result)
  }
  @Test def maybeApplicationOnOneNone(): Unit = {
    val a: Int = 3
    val b: String | None.type = None 
    val result: String | None.type = repeat $ (a, b)
    assertEquals(None, result)
  }
  @Test def maybeApplicationEitherSucc(): Unit = {
    val a: Int | Double = 3
    val b: String | None.type = "b"
    val result: String | Double | None.type = repeat $ (a, b)
    assertEquals("bbb", result)
  }
  @Test def maybeApplicationEitherEitherErr(): Unit = {
    val a: Int | Double = 3.0
    val b: String | None.type = "b" 
    val result: String | Double | None.type = repeat $ (a, b)
    assertEquals(3.0, result)
  }
}

object AlgAppTest {
  import algae.AlgApp._

  val length: String => Int = (s: String) => s.length
  
  val xok: Int | Boolean  | String = "false"
  val xwrong: Int | Boolean = false

  val x1: (String | Boolean | List[String] ) = Nil
  val x2: String | Double = "AA"

  val y1: List[String] | Int | Boolean = algApp(length)(x1)
  val y2: Int | Double = algApp(length)(x2)
  val yw: Int | Boolean = algApp(length)(xok)
 
  object Neg
  val pos: Int => (Int | Neg.type) = (i: Int) => if (i < 0) Neg else i
  val y11: Int | Neg.type | Boolean | List[String] = algApp(pos)(y1)

  val rem: Neg.type => Int = _ => 0

  val fin: Int | List[String] | Boolean = algApp(rem)(y11)
}
