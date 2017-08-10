package com.lxz.scala.demo2

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

/**
  * Created by xiaolezheng on 17/8/10.
  */
object ActorDemo1 extends App {
  val log = LoggerFactory.getLogger("ActorDemo1")

  implicit val ec = ExecutionContext.global
  implicit val timeout = Timeout(5, TimeUnit.SECONDS)

  val system = ActorSystem("system-worker")
  val worker = system.actorOf(Props[Worker], "worker-actor")

  worker ! new Start(System.currentTimeMillis())
  worker ! new End(System.currentTimeMillis())
  val f = worker ? 10
  f.onComplete {
    case Success(v) => log.info("v: {}", v)
    case Failure(e) => log.error("", e)
  }

  TimeUnit.SECONDS.sleep(5)

  system.terminate()
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
      sender ! o
    }
  }
}
