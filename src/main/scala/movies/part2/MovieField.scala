package movies.part2

import movies.part1.Order

sealed trait MovieField {

  def ordering(order: Order): Ordering[Movie]
}

object MovieField {
  case object Name extends MovieField {
    override def ordering(order: Order): Ordering[Movie] = order(Ordering.by(_.name))
  }

  case object ReleaseDate extends MovieField {
    override def ordering(order: Order): Ordering[Movie] = order(Ordering.by(_.releaseDate))
  }

  case object Rating extends MovieField {
    override def ordering(order: Order): Ordering[Movie] = order(Ordering.by(_.rating))
  }

  case object DvdReleaseDate extends MovieField {
    override def ordering(order: Order): Ordering[Movie] = order(Ordering.by(_.dvdReleaseDate))
  }

  case object DirectorName extends MovieField {
    override def ordering(order: Order): Ordering[Movie] = order(Ordering.by(_.director.name))
  }

  case object DirectorBirthDate extends MovieField {
    override def ordering(order: Order): Ordering[Movie] = order(Ordering.by(_.director.birthDate))
  }

  case object NumberOfRewards extends MovieField {
    override def ordering(order: Order): Ordering[Movie] = order(
      Ordering.by(movie =>
        movie.actors.map(a => a.rewards.map(_.count).sum).sum + movie.director.rewards.map(_.count).sum
      )
    )
  }
}
