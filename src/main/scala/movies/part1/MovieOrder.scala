package movies.part1

import java.time.LocalDate

object MovieOrder {
  def orderingFrom(nameOrder: Order, releaseDateOrder: Order, rating: Order): Ordering[Movie] = {
    new Ordering[Movie] {
      def compare(m1: Movie, m2: Movie) = List(
        nameOrder(Ordering[String]).compare(m1.name, m2.name),
        releaseDateOrder(Ordering[LocalDate]).compare(m1.releaseDate, m2.releaseDate),
        rating(Ordering[Int]).compare(m1.rating, m2.rating)
      ).foldLeft(0)((acc, c) => if (acc != 0) acc else c)
    }
  }
}
