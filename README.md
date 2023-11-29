# yangtze - 智能数据分析项目

> 作者：kingYQH



## 项目简介

为企业提供简单高效数据分析的系统 —— `智能BI项目`

虽然系统逻辑和功能并不复杂，代码量也不多，但是正因如此，大家才不需要关注特定的、复杂的业务流程，可以更轻松地学习到 **通用的技术和做项目的经验** 。

麻雀虽小五脏俱全，鱼皮真真正正地 **从 0 到 1 全程直播** 带大家完成了这个项目的完整前端和后端！从产生想法、到需求分析、到技术选型、系统设计、项目初始化、编码实现、测试、再到最后的部署上线，每一个环节我都从理论到实践给大家讲的明明白白、每一个细节都不放过！再加上直播过程中踩的种种坑点以及问题的解决，相信一定可以帮助大家走出书本、走出校园、走出死板 / 单纯讲知识点的网课和教程，学到企业 真正需要的开发技能和经验。



## 智能BI业务流程

1. 用户输入原始数据
   - 分析目标
   - 上穿原始数据
   - 更精细的控制图表，比如图表类型，图表名称
2. 后端校验
   - 校验用户的输入是否合法（比如长度）
   - 成本控制（次数统计，鉴权等）
3. 把处理后的数据输入给AI模型（调用AI接口）==>  AI模型提供图表信息，结论文本 
4. 图表信息，结论文本在前端进行展示

## 项目核心接口
根据用户的输入（文本和文件），最后返回图表信息和结论文本

### 原始数据压缩
ai接口普遍都有输入字数限制，能够允许多传一些数据
如何像ai题词（prompt）？
- 持续输入持续优化






## 本项目适合的同学

1. 学过基本的前端（HTML + CSS + JS 三件套）或后端开发技术（Java Web）
2. 还不知道怎么独立做出完整的项目，想了解规范的开发流程
3. 想快速学习自己不熟悉的技术并且了解其应用（比如你只会前端，想了解后端）
4. 想全方位提高自己的编程能力
5. 想提升做项目的经验和系统设计能力
6. 想学习更多企业主流开发技术
7. 想给简历增加项目经验
8. 想开发和上线自己的网站

### 后端技术

- Spring Boot 2.7.x（贼新）
- Spring MVC
- MyBatis + MyBatis Plus 数据访问（开启分页）
- Spring Boot 调试工具和项目处理器
- Spring AOP 切面编程
- Spring Scheduler 定时任务
- Spring 事务注解

### 数据存储

- MySQL 数据库
- Redis 内存数据库
- Elasticsearch 搜索引擎
- 腾讯云 COS 对象存储

### 工具类

- Easy Excel 表格处理
- Hutool 工具库
- Gson 解析库
- Apache Commons Lang3 工具类
- Lombok 注解

### 业务特性

- Spring Session Redis 分布式登录
- 全局请求响应拦截器（记录日志）
- 全局异常处理器
- 自定义错误码
- 封装通用响应类
- Swagger + Knife4j 接口文档
- 自定义权限注解 + 全局校验
- 全局跨域处理
- 长整数丢失精度解决
- 多环境配置


## 业务功能

- 提供示例 SQL（用户、帖子、帖子点赞、帖子收藏表）
- 用户登录、注册、注销、更新、检索、权限管理
- 帖子创建、删除、编辑、更新、数据库检索、ES 灵活检索
- 帖子点赞、取消点赞
- 帖子收藏、取消收藏、检索已收藏帖子
- 帖子全量同步 ES、增量同步 ES 定时任务
- 支持微信开放平台登录
- 支持微信公众号订阅、收发消息、设置菜单
- 支持分业务的文件上传

### 单元测试

- JUnit5 单元测试
- 示例单元测试类

### 架构设计

- 合理分层


## 快速上手

> 所有需要修改的地方鱼皮都标记了 `todo`，便于大家找到修改的位置~

### MySQL 数据库

1）修改 `application.yml` 的数据库配置为你自己的：

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/my_db
    username: root
    password: 123456
```

2）执行 `sql/create_table.sql` 中的数据库语句，自动创建库表

3）启动项目，访问 `http://localhost:8101/api/doc.html` 即可打开接口文档，不需要写前端就能在线调试接口了~

![](doc/swagger.png)

### Redis 分布式登录

1）修改 `application.yml` 的 Redis 配置为你自己的：

```yml
spring:
  redis:
    database: 1
    host: localhost
    port: 6379
    timeout: 5000
    password: 123456
```

2）修改 `application.yml` 中的 session 存储方式：

```yml
spring:
  session:
    store-type: redis
```

3）移除 `MainApplication` 类开头 `@SpringBootApplication` 注解内的 exclude 参数：

修改前：

```java
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
```

修改后：


```java
@SpringBootApplication
```

### Elasticsearch 搜索引擎

1）修改 `application.yml` 的 Elasticsearch 配置为你自己的：

```yml
spring:
  elasticsearch:
    uris: http://localhost:9200
    username: root
    password: 123456
```

2）复制 `sql/post_es_mapping.json` 文件中的内容，通过调用 Elasticsearch 的接口或者 Kibana Dev Tools 来创建索引（相当于数据库建表）

```
PUT post_v1
{
 参数见 sql/post_es_mapping.json 文件
}
```

这步不会操作的话需要补充下 Elasticsearch 的知识，或者自行百度一下~

3）开启同步任务，将数据库的帖子同步到 Elasticsearch

找到 job 目录下的 `FullSyncPostToEs` 和 `IncSyncPostToEs` 文件，取消掉 `@Component` 注解的注释，再次执行程序即可触发同步：

```java
// todo 取消注释开启任务
//@Component
```