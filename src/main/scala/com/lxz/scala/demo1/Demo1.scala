package com.lxz.scala.demo1

import org.slf4j.LoggerFactory

/**
  * Created by xiaolezheng on 17/8/10.
  */
object Demo1 extends App {
  val log = LoggerFactory.getLogger(Demo1.getClass);
  log.info("hello world")

  //log.info("{},{}", Util.trim("hello "), 1)
  log.info("{},{}", "hello ", 1)


  val seq = Seq(2, 3, 4, 5, 6)
  seq.map(_ * 2).foreach(item => log.info("item: {}", item))
}
