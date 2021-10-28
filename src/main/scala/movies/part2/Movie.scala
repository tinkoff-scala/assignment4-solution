package movies.part2

import java.time.LocalDate

final case class Movie(
  name: String,
  releaseDate: LocalDate,
  rating: Int,
  actors: List[Person],
  director: Person,
  dvdReleaseDate: LocalDate
)

case class Person(name: String, birthDate: LocalDate, rewards: List[Reward])

case class Reward(name: String, count: Int)
