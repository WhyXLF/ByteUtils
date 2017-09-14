#字节注入工具
####简介：
用于读取文件或者网络数据到指定的对象中，可自定义字节读取方法
####版本
1.0-SNAPSHOT 支持基本类型字段的注入
####使用实例
通过添加注解设置属性需要加载的文件路径，当前可用注解如下：
``
@ByteReadService(path = "./record1.txt")
``

通过添加注解设置属性需要用的字段，当前可用的注解如下：

``
@ByteReadField(start = 1,end = 3)
``
用于指定start和end获取指定的字符
