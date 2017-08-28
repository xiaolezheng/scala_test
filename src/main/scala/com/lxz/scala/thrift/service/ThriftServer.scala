package com.lxz.scala.thrift.service

import java.net.InetSocketAddress

import com.twitter.finagle.ThriftMux
import com.twitter.util.{Await, Future}
import org.slf4j.LoggerFactory

/**
  * Created by xiaolezheng on 17/8/28.
  */
class ThriftServer extends DemoService[Future] {
  val logger = LoggerFactory.getLogger(classOf[ThriftServer])

  override def method1(): Future[String] = {
    logger.info("invoke method1")
    Future("hello method1")
  }

  override def method2(a: Int, b: Int): Future[Int] = {
    logger.info("invoke method2")
    Future(a + b)
  }

  override def method3(): Future[Unit] = {
    logger.info("invoke method3")
    Future.Unit
  }

  override def genId(): Future[Long] = {
    val id = System.nanoTime();
    logger.info("id: {}", id)
    Future(id)
  }
}

object ThriftServer extends App {
  val server = ThriftMux.server.serveIface(new InetSocketAddress("127.0.0.1", 8081), new ThriftServer)
  Await.result(server)
}
