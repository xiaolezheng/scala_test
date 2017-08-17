package com.lxz.scala.demo6

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
  * Created by xiaolezheng on 17/8/17.
  */


object Main extends App {
  val logger = LoggerFactory.getLogger(classOf[Main])

  val system = ActorSystem("system-actor")
  val tickActor = system.actorOf(Props(classOf[TickActor]), "tick-actor")

  import system.dispatcher

  val cancellable = system.scheduler.schedule(0 milliseconds, 50 milliseconds,
    tickActor, Tick())

  TimeUnit.SECONDS.sleep(20)

  system.terminate()
}

class Main

case class Tick(id: Long)

case object Tick {
  def apply() = {
    new Tick(System.currentTimeMillis())
  }
}

class TickActor extends Actor with ActorLogging {
  override def receive = {
    case t: Tick => {
      log.info("tick -> {}", t.id)
    }
  }
}
