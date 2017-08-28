package com.lxz.scala.thrift.service

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future, Futures}
import org.slf4j.LoggerFactory

/**
  * Created by xiaolezheng on 17/8/28.
  */
object ThriftClient extends App {
  val logger = LoggerFactory.getLogger("ThriftClient")

  val client = Thrift.client.newIface[DemoService[Future]]("127.0.0.1:8081")

  for (i <- 1 to 1000) {
    val id = client.genId()
    id.onSuccess(value => logger.info("index:{}, id: {}", i, value))
  }

  val f1 = client.method1()
  val f2 = client.method2(1, 2)
  val f3 = client.method3()

  val result = Futures.join(f1, f2, f3)

  Await.ready(result).onSuccess {
    case (r1, r2, r3) => logger.info("r1: {}, r2: {}", r1, r2)
  }.onFailure(e => logger.info("error: " + e))
}
