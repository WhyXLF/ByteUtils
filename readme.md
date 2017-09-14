README
===========================
#### 简介：
用于读取文件或者网络数据到指定的对象中，可自定义字节读取方法
#### 版本
1.0-SNAPSHOT 支持基本类型字段的注入
#### 使用实例
通过添加注解设置属性需要加载的文件路径(可定制，默认从类路径读取)，当前可用注解如下：
``
@ByteReadService(path = "./record1.txt")
``

通过添加注解设置属性需要用的字段，当前可用的注解如下：

``
@ByteReadField(start = 1,end = 3)
``
用于指定start和end获取指定的位置
``
@ByteReadList(part = 2,start = 1,end = 20)
``
仅对List类型可用，part指定list的size，start指定了读取字节的起始和结束位置

maven依赖添加：

```
<dependency>
   <groupId>com.xlf.utils</groupId>
   <artifactId>ByteUtils</artifactId>
   <version>1.0-SNAPSHOT</version> 
</dependency>
```