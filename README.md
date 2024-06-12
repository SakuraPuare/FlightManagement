# 智慧机场系统软件开发计划书

## 1. 引言

### 1.1. 背景

随着旅游业的蓬勃发展，机场的运营需求不断增加。为了更好地服务管理者、航司、旅客、商户和机场工作人员，需要一套智慧机场系统来管理和协调机场的各项工作。

### 1.2. 目的

为了保证项目团队按时且保质保量地完成项目目标，也便于项目团队成员更好地了解本项目地情况，从而使项目开发的各个过程合理有序地进行，有必要以文件化的形式，把处在项目生命周期内的工作任务范围、各项工作的任务分解、项目团队组织结构、各团队成员的工作责任、团队内外沟通协作方式、开发进度安排、经费预算、项目内外环境条件及风险对策等内容以书面的方式描述出来，以作为项目团队成员及项目相关者之间的共识与约定、项目生命周期内的所有项目活动的行动基础以及项目团队开展和检查项目工作的依据。

此计划书不仅能让参与项目的开发者们知道项目如何进行，还明确了团队成员各自的职责，可以保证项目团队之间的协作更加有条不紊地进行，从而使得项目工作的各个过程能够合理有序地展开。同时，计划书也能在团队内外的沟通中起导向作用，对团队间的工作范围、开发模块间的关系以及开发进度、经费预算、分配人力物力、风险等因素进行了大概的描述。

此软件开发计划书的编写主要是为“智慧机场系统”的开发做主要的规划和整合。本软件开发计划面向用户、项目开发人员及项目监督人员。

### 1.3. 相关文档

\[1\]《智慧机场系统需求文档》

\[2\]《软件开发计划书》

\[3\]《需求规格说明书》

\[4\]《软件设计说明书》

\[5\]《软件实现说明书》

\[6\]《测试报告》

\[7\]《部署文档》

\[8\]《用户使用说明书》

\[9\]《项目总结表》

## 2. 项目概述

### 2.1. 项目目标

本产品为智慧机场系统，主要应用于机场的协调跑道资源、调度航班信息、提供信息查询功能以及登机时的安全检查等功能，为一个独立的系统，主要与机场方面进行交互。该系统主要包括四大部分：旅客客户端、商户客户端、航司客户端和工作人员客户端。

1. 旅客客户端功能
    - [x] 旅客注册
    - [x] 旅客登录
    - [x] 查询机票
    - [x] 购买机票
    - [x] 查询订单
    - [ ] 查询行李
2. 商户客户端
    - [x] 商户注册
    - [x] 商户登录
    - [x] 增、删、查、改商品信息
3. 航司客户端功能
    - [x] 航司注册
    - [x] 航司登录
    - [x] 增、删、查、改航班信息
    - [x] 增、删、查、改机票信息
4. 工作人员客户端功能
    - [x] 工作人员注册
    - [x] 工作人员登录
    - [x] 导入机票信息
    - [ ] 申请和审核报修请求
    - [ ] 录入行李信息
    - [ ] 查询财务报表

### 2.2. 项目范围

本节主要依照《智慧机场系统软件需求规格说明书》的相关章节来说明“智慧机场系统”的涉及范围。

### 2.3. 项目使用对象

- 本平台的**最终用户**是希望通过本系统的辅助完成办理机场各项手续的旅客、希望在机场进行产品销售的商户以及希望借由本系统提供的相关信息提升工作效率的普通机场工作人员。

- 本平台的**系统维护人员**为项目开发团队，对于此系统的相关部分比较熟悉，团队内部具有对数据库、计算机、网络较为熟悉的人员。

- 本平台的**系统管理员**为负责上传、修改和更新航班相关信息的航司及负责各表单审批、导入和打印等相关操作的机场管理员（属于一类特殊的机场工作人员）。

### 2.4. 需要交付的成果

#### 2.4.1. 需要交付的软件

基于Vue（或React）和Spring Boot设计的“智慧机场系统”，其中包括由Vue框架编写的前端页面、后台MySQL数据库系统、可直接在Linux服务器部署的后台逻辑代码（包括使用的用于渲染和与用户交互的前端页面代码）。

#### 2.4.2. 需要交付的文档

- 《软件开发计划书》

- 《需求规格说明书》

- 《软件设计说明书》

- 《软件实现说明书》

- 《测试报告》

- 《部署文档》

- 《用户使用说明书》

- 《项目总结表》

### 2.5. 项目开发

本系统采用PC机进行开发，开发环境如下：

- 操作系统：Windows，Linux

- 数据库系统：MySQL-8.0.15

- IDE：Visual Studio Code, IntelliJ IDEA

- 测试工具：Junit

- 浏览器：Google Chrome