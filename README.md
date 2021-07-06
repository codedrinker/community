## 码问社区

## 在线演示地址
[https://www.mawen.co](https://www.mawen.co)，任何配置、使用和答疑问题，可以 👉[点击](#联系我) 联系我，也可以拉你进群沟通。

## 功能列表  
开源论坛、问答系统，现有功能多社交平台登录(Github，Gitee)提问、回复、通知、最新问答、最热热大、消除零回复等功能。

## 当前项目配套的手把手视频教程
|  标题   |  链接   |
| --- | --- |
|  【Spring Boot 实战】论坛项目【第一季】   |  https://www.bilibili.com/video/BV1r4411r7au  |
|  【Spring Boot 实战】热门话题，经典面试问题实战，TopN 问题【第二季】|  https://www.bilibili.com/video/BV1Z4411f7RK  |
|  【Spring Boot 实战】接入广告流量变现(让你的网站益起来)【第三季】  | https://www.bilibili.com/video/BV1L4411y7J9  |
|  【Spring Boot 实战】Vue 零基础入门(前后端分离的前置视频)【第四季】  |   https://www.bilibili.com/video/BV1gE411R7YA |
|  【Spring Boot 实战】Java 设计模式实战(加薪的必修课)【第五季】  |   https://www.bilibili.com/video/BV1UK4y1M7PC |
|  【Spring Boot 实战】快速搭建免费 HTTPS 服务  |   https://www.bilibili.com/video/BV1oJ411K7VT  |

## 本地运行手册
1. 安装必备工具  
JDK，Maven
2. 克隆代码到本地
```sh
git clone https://github.com/codedrinker/community.git
````
3. 运行数据库脚本，创建本地数据库
```sh
mvn flyway:migrate
```
如果需要使用 MySQL 数据库，运行脚本前修改两处配置
```
# src/main/resources/application.properties 
spring.datasource.url=jdbc:h2:~/community
spring.datasource.username=sa
spring.datasource.password=123
```
```
# pom.xml
<properties>
    <db.url>jdbc:h2:~/community</db.url>
    <db.user>sa</db.user>
    <db.password>123</db.password>
</properties>
```
4. 运行打包命令，生成可执行 jar 文件
```sh
mvn package -DskipTests
```
4. 运行项目  
```sh
java -jar target/community-0.0.1-SNAPSHOT.jar
```
如果是线上部署，可以增加配置文件(production.properties)，同时运行命令修改如下
```sh
java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
```
5. 访问项目
```
http://localhost:8887
```

## 其他
1. 视频初期未使用 Flyway 之前的数据库脚本
```sql
CREATE TABLE USER
(
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN VARCHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
);
```
2. 生成 Model 等 MyBatis 配置文件的命令
```
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```


## 技术栈
|  技术   |  链接   |
| --- | --- |
|  Spring Boot   |  http://projects.spring.io/spring-boot/#quick-start   |
|   MyBatis  |  https://mybatis.org/mybatis-3/zh/index.html   |
|   MyBatis Generator  |  http://mybatis.org/generator/   |
|   H2  |   http://www.h2database.com/html/main.html  |
|   Flyway  |   https://flywaydb.org/getstarted/firststeps/maven  |
|Lombok| https://www.projectlombok.org |
|Bootstrap|https://v3.bootcss.com/getting-started/|
|Github OAuth|https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/|
|UFile|https://github.com/ucloud/ufile-sdk-java|
|Bootstrap|https://v3.bootcss.com/getting-started/|

## 扩展资料
[Spring 文档](https://spring.io/guides)    
[Spring Web](https://spring.io/guides/gs/serving-web-content/)   
[es](https://elasticsearch.cn/explore)    
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)    
[Bootstrap](https://v3.bootcss.com/getting-started/)    
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)    
[Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)    
[菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)    
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)    
[Spring Dev Tool](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#using-boot-devtools)  
[Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)  
[Markdown 插件](http://editor.md.ipandao.com/)   
[UFfile SDK](https://github.com/ucloud/ufile-sdk-java)  
[Count(*) VS Count(1)](https://mp.weixin.qq.com/s/Rwpke4BHu7Fz7KOpE2d3Lw)  
[Git](https://git-scm.com/download)   
[Visual Paradigm](https://www.visual-paradigm.com)    
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)  
[Lombok](https://www.projectlombok.org)    
[ctotree](https://www.octotree.io/)   
[Table of content sidebar](https://chrome.google.com/webstore/detail/table-of-contents-sidebar/ohohkfheangmbedkgechjkmbepeikkej)    
[One Tab](https://chrome.google.com/webstore/detail/chphlpgkkbolifaimnlloiipkdnihall)    
[Live Reload](https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei/related)  
[Postman](https://chrome.google.com/webstore/detail/coohjcphdfgbiolnekdpbcijmhambjff)

## 更新日志
- 2019-7-30 修复 session 过期时间很短问题   
- 2019-8-2 修复因为*和+号产生的搜索异常问题  
- 2019-8-18 添加首页按照最新、最热、零回复排序  
- 2019-8-18 修复搜索输入 ? 号出现异常问题
- 2019-8-22 修复图片大小限制和提问内容为空问题
- 2019-9-1 添加动态导航栏  
- 2021-7-5 修复因为网络原因不能拉去到自定义 spring starter 问题

## 联系我
有任何问题可以扫码下面两个二维码找到我，左边是微信订阅号，关注回复 ‘面试’即可获得我整理的(2W字)阿里面经，右边是个人微信号，有任何技术上面的问题可以给我留言。
|  微信公众号   |  个人微信   |
| --- | --- |
|  码匠笔记  |  fit8295 |
|  ![](https://mawen-cdn.cn-bj.ufileos.com/wxdyh-qr.jpeg?iopcmd=thumbnail&type=1&scale=50)   |   ![](http://mawen-cdn.cn-bj.ufileos.com/wechat.jpeg?iopcmd=thumbnail&type=1&scale=50)  |
