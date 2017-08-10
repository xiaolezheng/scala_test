package com.lxz.scala.demo1

import com.lxz.java.Util
import org.slf4j.LoggerFactory

/**
  * Created by xiaolezheng on 17/8/10.
  */
object Demo1 extends App {
  val log = LoggerFactory.getLogger(Demo1.getClass);
  log.info("hello world")

  log.info("{},{}", Util.trim("hello "), 1)
  log.info("{},{}", "hello ", 1)
}
