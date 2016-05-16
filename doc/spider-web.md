# Spider-Web

## 配置和启动

com.spider.SpiderManagerApplication为主配置入口和启动类
com.spider.config.DatabaseConfig是JPA和数据库的配置，由com.spider.SpiderManagerApplication引入，共同配置应用
属性配置文件为application.properties

## 部署

本地mvn package打包
在1.21上执行python /opt/spider/deploy-web.py，输入密码，完成部署和

## 与Spider-Robot的关系

共用一套数据库
提供推送赔率，比分和比赛进行时间的接口
