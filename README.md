# GomeOS MVVM
GomeOS MVVM是一个基于MVVM思想架构，结合google databingding技术来设计的一个框架，用于日常app的开发，模块间设计严格按照迪米特法则，并具有良好的解耦设计。
框架简化开发者adapter与recyclerview的数据繁琐创建，无需开发adapter，只需对列表中视图和数据进行绑定即可，大大提升开发效率。
## 框架设计


## 模块介绍
### ViewModel生命周期与Activity保持一致
- onCreate：与Activity相同
- onRestoreInstanceState：与Activity相同
- onStart ：与Activity相同
- onResume ：与Activity相同
- onWindowFocusChanged ：与Activity相同
- onSaveInstanceState ：与Activity相同
- onPause ：与Activity相同
- onStop ：与Activity相同


### Use Case  
- 功能：
纯业务逻辑类
网络请求
数据库读写
- 限制：
不负责线程切换
不负责View Bean数据变换
只允许使用UseCaseManager获取实例

### module

- 功能:<br>
1, 模块入口对象<br>
2, SharedPreferences升级时点<br>
3, 数据库升级时点<br>
4, 持有UseCaseManager，用于获取Use Case实例
- 使用:<br>
1, 每个模块单例继承该类<br>
2, 定义与其他模块的接口事件方法


## 使用
- 在build.gradle 打开dataBinding 选项
```gradle
android{
    ...
    dataBinding {
        enabled = true
    }
}
```
- 添加依赖
 ```
  compile 'com.gomeos.mvvm:mvvm:1.0.0'
 ```
- application里
```
 ModuleManager.getInstance().init(this);
 ModuleManager.getInstance().installModule(DemoModule.get()); //可以有多个module，开始进行装载即可
```

- module 创建，作为一个模块的入口，多个模块可以有多个xxxmodule
```
public class MyModule extends Module {
    private static volatile DemoModule instance = null;
    public static DemoModule get() {
        if (instance != null) {
            return instance;
        }
        synchronized (DemoModule.class) {
            if (instance == null) {
                instance = new DemoModule();
            }
        }
        return instance;
    }
    @Override
    protected void onStart(UseCaseManager userCaseManager) {
        userCaseManager.register(MyUseCase.class);
    }
}

```

- UseCase
```
public class MyUseCase extends UseCase {
...数据初始化
...相关数据提供网络请求
}
```

- UI相关viewmodel及itemviewmodel根据提供的工厂模式进行添加即可，具体参见demo
- mvvm框架还提供了在viewmodel里方便启动其他界面的方法以及跳转下页面后数据回传问题，并提供通过代理方式管理viewmodel里的对话框方式，具体参见demo用法展示。

License
-------
```
   Copyright (C) 2017 gomeos
   
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
   
      http://www.apache.org/licenses/LICENSE-2.0
   
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
