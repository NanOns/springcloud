## JAVA 与 C混合编程
```text
1.web项目中使用JNI技术与底层的库文件交互
2.不同的平台生成不同的JNI libaray:
  windows: .dll
  linux: .so
  macOS: .jnilib
3.要实现java能调用到c或c++的底层实现,需要在运行时候改变java.library.path内容   
``` 

### 调用流程
![输入图片说明](https://raw.githubusercontent.com/qccr-twl2123/springcloud/master/images/java-jni.jpg "在这里输入图片标题")

```text
linux: 
第一步:
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:/some/pathOfContainingDirectory"
第二步:重命名
mv JNIDemo.so libJNIDemo.so

```
```text
注: so文件放在bin路径下，要修改文件名, 在文件名前面加lib
```

### 参考链接
[JavaWeb动态调用JNI](http://www.imooc.com/article/14702)

[JNI使用总结](https://www.jianshu.com/p/fe42aa3150a0)


