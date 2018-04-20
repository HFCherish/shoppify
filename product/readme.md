坑：

1. eureka 和 spring boot jersey 有依赖冲突，会导致程序起不起来。在 eureka 中配置 exclude 一些依赖
2. spring `application.yml` 配置中，相同抬头的配置必须配在一块。eg. `spring.application.name` 和 `spring.datasource.url` 都必须放在同一个 `spring` 下
3. 加入 eureka 后，jersey 利用 `package` 定义自动扫描会有问题，改用 `register`