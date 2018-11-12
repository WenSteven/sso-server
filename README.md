### 史上最简单的Spring Boot 2.0 & Oauth2 的Demo

## 模块介绍：

1. app项目是其他需要授权的应用
2. oauth2-server模块是oauth2授权中心
3. resources-server模块是oauth2资源服务

>当然也可以直接通过postman或者curl测试不同授权模式下的效果

## 数据库说明
1. 应用启动的时候会自动创建，参考db文件夹下的`data.sql`和`schema.sql`

## 如果运行呢？

1. 分别启动
2. 打开url `http://localhost:8002` (app模块)，点击受保护的资源的链接，这时候会跳转到授权服务器进行登录，然后授权，最终回到调用方界面。
3. over!



