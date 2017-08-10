package com.lxz.scala.demo2

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.ExecutionContext

/**
  * Created by xiaolezheng on 17/8/10.
  */
object ActorDemo1 extends App {
  implicit val ec = ExecutionContext.global
  implicit val timeout = Timeout(5, TimeUnit.SECONDS)

  val system = ActorSystem("system-worker")
  val worker = system.actorOf(Props[Worker], "worker-actor")

  worker ! new Start(System.currentTimeMillis())
  worker ! new End(System.currentTimeMillis())
  worker ! 10

  TimeUnit.SECONDS.sleep(5)
}


trait Cmd

case class Start(start: Long) extends Cmd

case class End(end: Long) extends Cmd

class Worker extends Actor with ActorLogging {
  override def receive = {
    case s: Start => {
      log.info("start time: {}", s.start)
    }

    case e: End => {
      log.info("end time: {}", e.end)
    }

    case o => {
      log.warning("no support cmd, {}", o)
    }
  }
}
