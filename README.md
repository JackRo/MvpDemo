# Android MVP 模式Demo

### 使用了以下框架：

1. RxJava 2.x，[RxJava](https://github.com/ReactiveX/RxJava)
2. Retrofit 2.x，[Retrofit](https://github.com/square/retrofit)
3. Butterknife，[Butterknife](https://github.com/JakeWharton/butterknife)
4. [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
（仅使用了[Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle.html)）
5. [XRecyclerView](https://github.com/XRecyclerView/XRecyclerView)

#### Demo使用MVP模式组织App，方便以后重用。

### 介绍

#### Presenter

Presenter层感知生命周期，调用View层接口处理View层的initView（自己无法处理），
自己处理initData（自己可以处理）。

#### View

View层即Activity、Fragment，处理展示数据、显示加载框、显示错误、页面跳转等逻辑

#### Model

Model层被Presenter层持有，用来处理数据，包括来自网络、本地存储的数据