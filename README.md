# boracay


## UDSP 1.x
### 定位
![](/doc/img/udsp_1.x_location.png)

### 模块说明
#### 交互查询（IQ）模块
##### 说明：
  适用于高并发（1000+）、低延迟（毫秒级）、大数据量（1T+）且业务需求明确（单表、明细）的应用。
##### 场景：
  历史数据查询、交易明细查询、日志信息查询等。
##### 目前实现：
  Solr、HBase、Solr+HBase、ElasticSearch、Redis。
##### 接口支持：
  HyperBase、Kylin等NoSql数据源

#### 联机查询（OLQ）模块
##### 说明：
  适用于低并发（100+）、中延迟（秒级）、中/大数据量（G~T+）、多表关联、统计分析的应用。
##### 应用：
  报表分析、联机分析、多维分析、多表关联等
##### 目前实现：
  Hive、Impala、Hive on Spark、Kylin、Inceptor、Oracle、MySQL、DB2
##### 接口支持：
  TD、Sql Server等Sql数据源

#### 模型管理（MM）模块
##### 说明：
  适用于同步调用和异步调起、查看状态、停止外部模型系统的模型，外部模型需要按照UDSP的报文规则提供模型接口。
##### 应用：
  同步模型调用、异步模型调起、异步模型查看状态、异步模型停止等
##### 目前实现：
  XXX厂商根据UDSP的报文规则实现的模型系统
##### 接口支持：
  按照UDSP的报文规则实现的所有模型系统

#### 实时流（RTS）模块
##### 说明：
  适用于需要实时或准实时数据流入和流出的应用。
##### 应用：
  大额定期提前支取提醒、ATM机大额取款营销、即时存贷提醒等
##### 目前实现：
  Kafka
##### 接口支持：
  IBM MQ、ZeroMQ、ActiveMQ、RabbitMQ等

#### 注册中心（RC）模块
##### 说明：
  用户信息注册、服务信息注册、资源信息注册、权限信息注册。

交互查询、联机查询、模型管理、实时流的应用注册为对外服务，并授予给某个用户，进行用户、IP等权限控制和同步并发、异步并发、查询数据大小的单机或分布式资源控制。

#### 监控中心（MC）模块
##### 说明：
  操作日志监控、消费日志监控、并发信息监控、消费统计图表。

操作日志入库提供操作审计，外部消费日志入库提供消费审计，实时并发信息提供实时消费监控，消费信息以图表形式统计。



## UDSP 2.x
### 定位
![](/doc/img/udsp_2.x_location.png)

### 模块说明
#### 交互建模（IM）模块
##### 说明
  简单点说就是做各个组件间的数据流转

##### 交互建模（IM）目前支持？
###### 批量模型
![](/doc/img/im_batch_support.png)

###### 实时模型
![](/doc/img/im_realtime_support.png)



## 模块架构图
![](/doc/img/udsp_module_architecture.png)



## 代码分支说明
#### master 分支仅用来同步分支最新代码，非发布、不稳定分支。
#### udsp-2.2 分支是2.2版本最新代码，非发布、不稳定分支。
#### udsp-2.2.* 分支为开发分支，经过内部测试使用之后在分支上发布可用版本。
#### udsp-2.2.* - * 分支为各个地区特殊开发分支，作为各个地区代码管理和合并使用。



## 版本升级说明
#### \*.\*.n bug修复、代码优化、性能优化、修改数据库字典信息
#### \*.n.\* 新增功能、添加数据库表、修改数据库表结构
#### n.\*.\* 系统整体架构变化、系统整体设计变化



## 后续开发计划


