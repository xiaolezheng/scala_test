package com.lxz.scala.demo9

/**
  * Created by xiaolezheng on 17/8/22.
  */


import com.lxz.scala.demo9.Commands.{AddLimitOrder, CancelOrder, Command}

import scala.util.Random

object PseudoBenchmark {
  def main(args: Array[String]): Unit = {
    //  """Given empty book
    //    |and buy limit order added
    //    |and second buy limit order added
    //    |and first buy limit order canceled
    //    |When market sell order arrives
    //    |Then OrderExecuted
    //  """.stripMargin
    val commands = Array[Command](
      AddLimitOrder(BuyLimitOrder(OrderId(1), Price(BigDecimal(2.00)))),
      AddLimitOrder(BuyLimitOrder(OrderId(2), Price(BigDecimal(2.00)))),
      CancelOrder(OrderId(1)))

    def nextCommandIndex(i: Int): Int = {
      def randomized(): Int = Random.nextInt(commands.length)

      def sequential(): Int = i % commands.length

      randomized()
    }


    println {
      (0 to 100000).foldLeft(OrderBook.empty) { case (ob, i) =>
        OrderBook.handle(ob, commands(nextCommandIndex(i)))._1
      }
    }
  }
}