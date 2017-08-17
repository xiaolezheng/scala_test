package com.lxz.scala.demo5

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.{Done, NotUsed}
import org.slf4j.LoggerFactory

import scala.concurrent.Future


/**
  * Created by xiaolezheng on 17/8/17.
  */
object Main extends App {
  val logger = LoggerFactory.getLogger("Main")
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val source: Source[Int, NotUsed] = Source(1 to 100)
  val done: Future[Done] = source.runForeach(i => logger.info("{}", i))(materializer)
  done.onComplete(- => {
    system.terminate();
    logger.info("over")
  })
}
