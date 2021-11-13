# LintDemo
Android Lint的使用

## 使用方式
1. 启动检测
    ```
    gradlew lint
    ```
2. 添加依赖
    ```
    // app
    lintChecks project(path: ':lintlib')
    
    // module的aar内部包含检测规则
    lintPublish project(path: ':lintlib')
    ```
    
## 功能介绍
1.  LogDetector  不允许直接使用Log
2.  GlideWithDetector  Glide.with不允许传入Application
3.  DependencyDetector  组件依赖关系，输出的字符串复制到[字符串生成图形](https://mermaid-js.github.io/mermaid-live-editor/edit/#eyJjb2RlIjoiZ3JhcGggVERcbiAgIGFwcC0tPmNoZWNrXG4gICAgY2hlY2stLT5tb2R1bGU0XG4gICAgY2hlY2stLT5tb2R1bGU1XG4gICAgYXBwLS0-bW9kdWxlMlxuICAgIG1vZHVsZTItLT5tb2R1bGU1XG4gICAgYXBwLS0-bW9kdWxlM1xuXG4gICIsIm1lcm1haWQiOiJ7XG4gIFwidGhlbWVcIjogXCJkYXJrXCJcbn0iLCJ1cGRhdGVFZGl0b3IiOnRydWUsImF1dG9TeW5jIjp0cnVlLCJ1cGRhdGVEaWFncmFtIjp0cnVlfQ)
## 其他
 [MeituanLintDemo](https://github.com/GavinCT/MeituanLintDemo)  美团的lint库
```
功能：
 1. 检测HashMap能否替换为SparseArray
 2. Toast创建之后没有调用show方法
 3. 避免使用Log/System.out.println
添加plugin，统一lint.xml，lintOptions，自动更新最新aar(含lint规则)
 ```
 [AndroidLint](https://github.com/RocketZLY/AndroidLint)  米大佬的lint库，比较全
 
 [AndroidLint](https://github.com/RocketZLY/AndroidLint)  b站逮虾户的lint库
 ```
 1. ContextCastDetector 不允许context返回值ContextWrapper/Application强转【类型/返回值判断】
 2. EventSpaceDetector Event参数是否有空格【参数判断，是否有空格，event类名】
 3. GlideDetector 不允许使用glide/decodeFile，应该使用中间方法【方法判断，glide.with判断，BitmapFactory.decodexxx判断】
 4. LogDetector  不允许使用Log.xxx【方法判断】
 5. PngResourceDetector 扫描drawable/mipmap目录文件大于10【文件判断】
 6. PrivacyClassDetector 
 7. RouteDetector 不允许使用路由Annotation/方法/DefaultUriRequest【注解/方法/类名判断】
 8. SafeFileDetector 不允许使用Environment.xxx/Pictures【方法/参数判断】
 9. ThreadDetector 不允许使用Thread【类名判断】
 10.AlertDialogDetector 不允许使用AlertDialog【类名判断】
 
```