package com.github.dnvriend.publisher

import scala.util.Random

package object tweet {
  implicit class ListOps[A](list: List[A]) {
    def randomElement(): A = {
      list(Random.nextInt(list.length))
    }
  }
}
