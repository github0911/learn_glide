# learn_glide
glide 框架学习使用

## one day 2019年4月17日
### 如何使用glide
[参考示例](https://blog.csdn.net/guolin_blog/article/details/53759439)
- 引入gradle配置
```
repositories {
  mavenCentral()
  google()
}

dependencies {
  
    //https://github.com/bumptech/glide
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'

    //https://github.com/ReactiveX/RxJava
    implementation "io.reactivex.rxjava2:rxjava:2.2.2"
    //https://github.com/ReactiveX/RxAndroid
    implementation "io.reactivex.rxjava2:rxandroid:2.1.0"
}
```
### 参考链接
* [RxJava 官方](https://github.com/ReactiveX/RxJava)
* [RxJava 学习](https://www.jianshu.com/p/0cd258eecf60)
* [示例代码](https://github.com/nanchen2251/RxJava2Examples)
* [给 Android 开发者的 RxJava 1.X 详解 ](https://gank.io/post/560e15be2dca930e00da1083)
