## java/android虚拟机

````
 android开发使用语言便是java,而android虚拟机(Dalvik,art)和普通的hospot等java虚拟机很多东西也是相似的(android虚拟机命令是基于寄存器的),因此可以对比来看(dalvik命令基于栈,看起来会比较紧凑一些)
````

````
 java或者说android虚拟机可以暂且当做是一个普通的进程,与一般的线程相对应,只不过其中会包含其他一般进程没有的部分(class解释器;JIT:Just In Time,即时编译等)
````

````
 java和虚拟机并不是绑定的关系,java规范和虚拟机规范是分开的,因此class文件并不只能由java文件来生成,事实上资深工作者可以直接编辑class文件来实现java语法不予许的功能
````

> Dalvik VM并不是一个Java虚拟机,它没有遵循Java虚拟机规范,不能直接执行Java的Class文件,使用的是寄存器架构而不是JVM中常见的栈架构。    ——  [ 深入理解Java虚拟机 ][ 周志明 ]

即便如此,也可以通过java虚拟机来查看一般的android代码执行情况,因为dalvik执行的dex其实是class文件转化而来,也是使用java语法编写的应用程序.

## Java,jdk

### java文件说明

java文件是以**java语法**为依据编译的文件,因为java中对安全限制较高,因此舍去了很多c++中指针等危险操作,对于访问溢出等,在生成底层代码时,会自动加上访问检测等操作,对于内存释放等,也会自动处理(**自动添加越界检测等操作**),因此java编写很方便;不过在最后执行底层代码时,也会因为这些操作导致运行效率偏低.

### jdk版本与功能

java编写的依据便是jdk库,同时在jdk有升级时,class文件规范,虚拟机自身也可能会有相应的更改,不同版本jdk可使用功能如下:

 1. 1995年5月23日;版本1.0;解释执行,Java虚拟机,Applet,AWT
 1. 1996年1月23日;版本1.1;JDK 1.1版的技术代表有：JAR文件格式、JDBC、JavaBeans、RMI。Java语法也有了一定的发展，如内部类（Inner Class）和反射（Reflection）都是在这个时候出现的
 1. 1998年12月4日;JDK 1.2;java分为SE,ME,EE;EJB,Java Plug-in,Java IDL,Swing;JIT(Just In Time),java中strictfp关键字,Collections集合等
 1. 2000年5月8日;JDK 1.3;2000年5月8日;添加数字计算,Timer API,JNDI平台级服务等
 1. 2002年2月13日;JDK 1.4;正则表达式,异常链,NIO,日志类,XML解释器和XSLT转换器等
 1. 2004年9月30日;JDK 1.5;自动装箱、泛型、动态注解、枚举、可变长参数、遍历循环（foreach循环）等语法特性都是在JDK 1.5中加入的。在虚拟机和API层面上，这个版本改进了Java的内存模型（Java Memory Model，JMM）、提供了java.util.concurrent并发包等
 1. 2006年12月11日;JDK 1.6;提供动态语言支持（通过内置Mozilla JavaScript Rhino引擎实现）、提供编译API和微型HTTP服务器API等。同时，这个版本对Java虚拟机内部做了大量改进，包括锁与同步、垃圾收集、类加载等方面的算法都有相当多的改动。
 1. 2009年2月19日;JDK 1.7;JDK 1.7的主要改进包括：提供新的G1收集器（G1在发布时依然处于Experimental状态，直至2012年4月的Update 4中才正式“转正”）、加强对非Java语言的调用支持（JSR-292，这项特性到目前为止依然没有完全实现定型）、升级类加载架构等。
 2. 2014年3月19日;JDK 1.8;Lambda表达式,Optional,Stream等
 
可以看到,随着jdk版本提升,功能也是越来越强

### java文件模版

编写一个简单的java文件,用于查看从java文件到最终字节码的执行过程:

----
    package com.knowledge.mnlin.frame.base;
    
    /**
     * 功能----RxBus传递对象,用于基本的显示toast等信息的传递
     * <p>
     * Created by MNLIN on 2017/9/23.
     */
    
    public class BaseEvent {
        //operateCode 类型,EventBus传递过来时,判断对应的类型
        public int operateCode;
    
        public Object data;
    
        public BaseEvent(int operateCode,Object data){
            this.operateCode=operateCode;
            this.data=data;
        }
    }
----