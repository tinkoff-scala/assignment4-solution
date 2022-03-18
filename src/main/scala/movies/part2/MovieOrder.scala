package movies.part2

import movies.part1.Order
import movies.part2.MovieOrder.movieOrderByFields

import java.time.LocalDate

object MovieOrder {
  def movieOrderByFields(fieldsWithOrder: Seq[(MovieField, Order)]): Ordering[Movie] = {
    if (fieldsWithOrder.isEmpty) Ordering.by[Movie, Int](_ => 0)
    else {
      val (topPriorityField, topPriorityOrder) = fieldsWithOrder.head
      fieldsWithOrder.tail.foldLeft(topPriorityField.ordering(topPriorityOrder)) {
        case (totalOrdering, (field, order)) =>
          totalOrdering.orElse(field.ordering(order))
      }
    }
  }
}

object Test extends App {
  val orderByNameAndReleaseDate: Ordering[Movie] = {
    val nameOrder = (MovieField.Name, Order.Ascending)
    val releaseDateOrder = (MovieField.ReleaseDate, Order.Descending)
    movieOrderByFields(Seq(nameOrder, releaseDateOrder))
  }

  val orderByNameAndReleaseDateAndRewards: Ordering[Movie] = {
    val nameOrder = (MovieField.Name, Order.Ascending)
    val releaseDateOrder = (MovieField.ReleaseDate, Order.Descending)
    val rewardsOrder = (MovieField.NumberOfRewards, Order.Descending)
    movieOrderByFields(Seq(nameOrder, releaseDateOrder, rewardsOrder))
  }

  val starWars = List(
    Movie(
      name = "Star wars 4",
      releaseDate = LocalDate.of(1977, 3, 4),
      rating = 9,
      actors = List.empty,
      director = Director("George Lucas", LocalDate.of(1947, 1, 1), rewards = List.empty),
      dvdReleaseDate = LocalDate.of(1999, 1, 1)
    ),
    Movie(
      name = "Star wars 3",
      releaseDate = LocalDate.of(2004, 3, 4),
      rating = 8,
      actors = List.empty,
      director = Director("George Lucas", LocalDate.of(1947, 1, 1), rewards = List(Reward("oscar", 4))),
      dvdReleaseDate = LocalDate.of(1999, 1, 1)
    ),
    Movie(
      name = "Star wars 4",
      releaseDate = LocalDate.of(2010, 3, 4),
      rating = 10,
      actors = List.empty,
      director = Director("George Lucas", LocalDate.of(1947, 1, 1), rewards = List(Reward("oscar", 5))),
      dvdReleaseDate = LocalDate.of(1999, 1, 1)
    )
  )

  val starWars2 = List(
    Movie(
      name = "Star wars 4",
      releaseDate = LocalDate.of(1977, 3, 4),
      rating = 9,
      actors = List.empty,
      director = Director("George Lucas", LocalDate.of(1947, 1, 1), rewards = List.empty),
      dvdReleaseDate = LocalDate.of(1999, 1, 1)
    ),
    Movie(
      name = "Star wars 4",
      releaseDate = LocalDate.of(1977, 3, 4),
      rating = 10,
      actors = List.empty,
      director = Director("George Lucas", LocalDate.of(1947, 1, 1), rewards = List(Reward("oscar", 5))),
      dvdReleaseDate = LocalDate.of(1999, 1, 1)
    )
  )

  println(starWars.sorted(orderByNameAndReleaseDate))
  println("-------------")
  println(starWars2.sorted(orderByNameAndReleaseDateAndRewards))
}
