### 史上最简单的Spring Boot 1.5 & Oauth2 的Demo

## 模块介绍：

1. app项目是其他需要授权的应用

2. oauth2-server模块是oauth2授权中心，包括授权服务和资源服务配置

## 数据库说明

1. 参考spring 官方的[数据库文档](https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql)（简单的demo中我们只需要使用到oauth_client_details就足够了）

2. 当然还需要用户表，角色表，参考 `schema.sql` 文件

## 如果运行呢？

1. 先启动oauth2-server然后启动app(其实哪个先启动，看你心情)
2. 打开url `http://localhost:8002` (app模块)，点击受保护的资源的链接，这时候会跳转到授权服务器进行登录，然后授权，最终回到调用方界面。
3. over!

>这个demospring boot 2.0上貌似因为一些实现细节的变化，导致不能正常运行。<br >
此外网上的一些demo写的过于复杂了,这个demo可以说是 *很简单很简单了* <br/>



