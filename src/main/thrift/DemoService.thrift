namespace java com.lxz.scala.thrift.service

service DemoService {
  string method1();

  i32 method2(1: i32 a, 2: i32 b);

  void method3();

  i64 genId();

}