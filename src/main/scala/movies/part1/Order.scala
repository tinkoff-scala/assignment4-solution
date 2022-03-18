package movies.part1

sealed trait Order
object Order {
  case object Identity extends Order
  case object Ascending extends Order
  case object Descending extends Order

  implicit class Ops(val order: Order) extends AnyVal {
    def apply[A](ordering: Ordering[A]): Ordering[A] = order match {
      case Identity   => (x: A, y: A) => 0
      case Ascending  => ordering
      case Descending => ordering.reverse
    }
  }
}
