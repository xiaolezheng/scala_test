package com.lxz.scala.demo10

import org.slf4j.LoggerFactory

/**
  * Created by xiaolezheng on 17/8/22.
  */
object LazyDemo extends App {
  val logger = LoggerFactory.getLogger("LazyDemo")

  val e1 = Db.get(1)
  Db.get(1).team.foreach(v => logger.info("v: {}", v.name))

}

case class Employee(id: Int, name: String, managerId: Int) {
  lazy val manager: Employee = Db.get(managerId)
  lazy val team: List[Employee] = Db.team(id)
}

object Db {
  val table = Map(1 -> (1, "Haruki Murakami", -1),
    2 -> (2, "Milan Kundera", 1),
    3 -> (3, "Jeffrey Eugenides", 1),
    4 -> (4, "Mario Vargas Llosa", 1),
    5 -> (5, "Julian Barnes", 2))

  def team(id: Int) = {
    for (rec <- table.values.toList; if rec._3 == id)
      yield recToEmployee(rec)
  }

  def get(id: Int) = recToEmployee(table(id))

  private def recToEmployee(rec: (Int, String, Int)) = {
    println("[db] fetching " + rec._1)
    Employee(rec._1, rec._2, rec._3)
  }
}
