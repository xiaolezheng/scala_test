package com.lxz.scala.demo11

import java.util.concurrent.TimeUnit

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}
import org.slf4j.LoggerFactory


/**
  * Created by xiaolezheng on 17/8/24.
  */
object Client extends App {
  val logger = LoggerFactory.getLogger("Clinet")
  val client: Service[http.Request, http.Response] = Http.newService("www.scala-lang.org:80")
  val request = http.Request(http.Method.Get, "/")
  request.host = "www.scala-lang.org"
  val response: Future[http.Response] = client(request)

  Await.result(response.onSuccess({
    rep: http.Response => {
      logger.info("-------------------------------")
      logger.info("GET success: {}", rep)
      logger.info("-------------------------------")
    }
  }))

  TimeUnit.SECONDS.sleep(1000)
}
