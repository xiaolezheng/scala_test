package com.lxz.scala.demo9

import java.io._

import com.lxz.scala.demo9.Commands.Command

import scala.collection.mutable.ListBuffer

/**
  * This module provides functions to persist to/load from disk a list of
  * commands.
  */
object DataCodec {

  def write(cs: List[Command], output: File): Unit = {
    val oos = new ObjectOutputStream(new FileOutputStream(output))
    cs.foreach(oos.writeObject)
    oos.close()
  }

  def read(input: File): List[Command] = {
    val fis = new FileInputStream(input)
    val ois = new ObjectInputStream(fis)
    val commandBuilder = ListBuffer[Command]()
    while (fis.available() != 0) {
      commandBuilder.append(ois.readObject().asInstanceOf[Command])
    }
    ois.close()
    fis.close()

    commandBuilder.result()
  }

}