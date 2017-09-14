字节流读取注入小公举
===========================
#### 简介：
用于读取文件或者网络数据到指定的对象中，可自定义字节读取方法
#### 版本
1.0-SNAPSHOT 支持基本类型字段的注入
#### 使用介绍
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

#### maven依赖添加：

```
<dependency>
   <groupId>com.xlf.utils</groupId>
   <artifactId>ByteUtils</artifactId>
   <version>1.0-SNAPSHOT</version> 
</dependency>
```

#### 使用示例

##### 测试用例
```
       public class ByteReadUtilsTest {
           @Test
           public void invoke() throws Exception {
               ByteReadUtils byteReadUtils = new ByteReadUtils(new FileByteReadFunctionImpl());
               TestByteRead testByteRead = new TestByteRead();
               byteReadUtils.invoke(testByteRead);
               System.out.println(testByteRead.toString());
           }
       }

```

##### 测试对象1
```
    @ByteReadService(path = "./record1.txt")
    public class TestByteRead {
        @ByteReadField(start = 1,end = 3)
        private String a;
        @ByteReadField(start = 4,end = 5)
        private int b;
        @ByteReadField(start = 6,end = 6)
        private char c;
        @ByteReadField(start = 7,end = 9)
        private long d;
        @ByteReadField(start = 10,end = 20)
        private TestByteRead2 testByteRead2;
        @ByteReadList(part = 2,start = 1,end = 20)
        private List<TestByteRead2> listStr;
    
        public String getA() {
            return a;
        }
    
        public void setA(String a) {
            this.a = a;
        }
    
        public int getB() {
            return b;
        }
    
        public void setB(int b) {
            this.b = b;
        }
    
        public char getC() {
            return c;
        }
    
        public void setC(char c) {
            this.c = c;
        }
    
        public long getD() {
            return d;
        }
    
        public void setD(long d) {
            this.d = d;
        }
    
        public TestByteRead2 getTestByteRead2() {
            return testByteRead2;
        }
    
        public void setTestByteRead2(TestByteRead2 testByteRead2) {
            this.testByteRead2 = testByteRead2;
        }
    
        public List<TestByteRead2> getListStr() {
            return listStr;
        }
    
        public void setListStr(List<TestByteRead2> listStr) {
            this.listStr = listStr;
        }
    
        @Override
        public String toString() {
            return "TestByteRead{" +
                    "a='" + a + '\'' +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", testByteRead2=" + testByteRead2 +
                    ", listStr=" + listStr +
                    '}';
        }
    }
```

##### 测试对象2
```
    public class TestByteRead2 {
        @ByteReadField(start = 1,end = 4)
        private String a;
        @ByteReadField(start = 5,end = 10)
        private String b;
    
        public String getA() {
            return a;
        }
    
        public void setA(String a) {
            this.a = a;
        }
    
        public String getB() {
            return b;
        }
    
        public void setB(String b) {
            this.b = b;
        }
    
        @Override
        public String toString() {
            return "TestByteRead2{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    '}';
        }
    }

```