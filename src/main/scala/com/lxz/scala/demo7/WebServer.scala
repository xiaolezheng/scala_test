package com.lxz.scala.demo7

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import org.slf4j.LoggerFactory

import scala.io.StdIn


/**
  * Created by xiaolezheng on 17/8/17.
  */
object WebServer {
  def main(args: Array[String]): Unit = {
    val logger = LoggerFactory.getLogger(classOf[WebServer])
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val ec = system.dispatcher

    val route = path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    logger.info(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    StdIn.readLine()
    bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())

  }

}

class WebServer
