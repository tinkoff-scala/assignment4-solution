package movies.part2

import java.time.LocalDate

sealed trait Person {
  def name: String
  def birthDate: LocalDate
  def rewards: List[Reward]
}

sealed case class Reward(name: String, count: Int)

sealed case class Actor(name: String, birthDate: LocalDate, rewards: List[Reward]) extends Person
sealed case class Director(name: String, birthDate: LocalDate, rewards: List[Reward]) extends Person

final case class Movie(
  name: String,
  releaseDate: LocalDate,
  rating: Int,
  actors: List[Actor],
  director: Director,
  dvdReleaseDate: LocalDate
)
