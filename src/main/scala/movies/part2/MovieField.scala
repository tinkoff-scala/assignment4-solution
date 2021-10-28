package movies.part2

import movies.part1.Order._
import scala.Ordering._

sealed trait MovieField {
  def withOrder(order: Order): Ordering[Movie]
}

object MovieField {
  case object Name extends MovieField {
    override def withOrder(order: Order): Ordering[Movie] =
      order(Ordering.Int).on(_.name)
  }
  case object ReleaseDate extends MovieField {
    override def withOrder(order: Order): Ordering[Movie] =
      order(Ordering.LocalDate).on(_.releaseDate)
  }
  case object Rating extends MovieField {
    override def withOrder(order: Order): Ordering[Movie] =
      order(Ordering.LocalDate).on(_.releaseDate)
  }
}
