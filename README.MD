## 饭团管理系统

> 此系统用户公司内部组团吃饭AA制账务管理

### 功能介绍

- 基础功能

    1. 支持根据单个用户进行充值，消费，转账
    
    2. 支持根据单次饭局总额AA制，进行批量消费。

### 系统角色

- 超级管理员
    - 上帝视角，脚本初始化时候自动生成。记账，管理用户信息
    
-  饭团团长
    - 记账
    
- 饭团成员
    - 查看饭团账务信息

### 部署流程  

- 打包

    1. 下载代码

    2. maven导入 additionjar目录下的 addtion-plus包，mybatis-selecterplus包，zkAddition包
    
    3. 修改 local.properties中数据库信息
    
    4. 打包命令 
        ```batch
            mvn clean package -DskipTests -Plocal
        ```
        根据不同的环境选择 Plocal或者Pproduct

- 执行
    
    1. 打包后得到war包 /fantuan-web/target/fantuan-web.war
    
    2. 通过tomcat或者其他容器启动war包。端口自己指定。
    
- 执行数据库脚本
    
    1. 在mysql 数据库里执行 脚本：/sql/fantuan.sql 即可，会初始化一个超级管理员账号：admin,密码：123123

### 使用过程

1. 访问主页

![图片](http://ovuanu0c3.bkt.clouddn.com/hexo/1524904168123.png?imageView2/2/w/596/h/640/format/jpg/q/100|watermark/2/text/d3d3LmxpbGh1aS5jb20=/font/5b6u6L2v6ZuF6buR/fontsize/400/fill/Izk2OEM4Qw==/dissolve/100/gravity/SouthEast/dx/10/dy/10|imageslim)

2. 用admin超级管理员登录

![图片](http://ovuanu0c3.bkt.clouddn.com/hexo/1524904209988.png?imageView2/2/w/1394/h/640/format/jpg/q/100|watermark/2/text/d3d3LmxpbGh1aS5jb20=/font/5b6u6L2v6ZuF6buR/fontsize/400/fill/Izk2OEM4Qw==/dissolve/100/gravity/SouthEast/dx/10/dy/10|imageslim)

3. 点击用户管理进入用户管理页面

![图片](http://ovuanu0c3.bkt.clouddn.com/hexo/1524904293279.png?imageView2/2/w/1408/h/640/format/jpg/q/100|watermark/2/text/d3d3LmxpbGh1aS5jb20=/font/5b6u6L2v6ZuF6buR/fontsize/400/fill/Izk2OEM4Qw==/dissolve/100/gravity/SouthEast/dx/10/dy/10|imageslim)

4. 点击新增按钮添加饭团团员

![图片](http://ovuanu0c3.bkt.clouddn.com/hexo/1524904336445.png?imageView2/2/w/724/h/640/format/jpg/q/100|watermark/2/text/d3d3LmxpbGh1aS5jb20=/font/5b6u6L2v6ZuF6buR/fontsize/400/fill/Izk2OEM4Qw==/dissolve/100/gravity/SouthEast/dx/10/dy/10|imageslim)

页面介绍：
    用户名：列表显示的名称
    账号：登录用的账号
    邮箱：邮箱地址
    电话：团员电话
    接团开始时间和接团结束时间：此时间段内，此团员会成为饭团长，具有记账功能。用户饭团团长自动流转功能。

  
5. 主页操作

![图片](http://ovuanu0c3.bkt.clouddn.com/hexo/1524905015603.png?imageView2/2/w/1367/h/640/format/jpg/q/100|watermark/2/text/d3d3LmxpbGh1aS5jb20=/font/5b6u6L2v6ZuF6buR/fontsize/400/fill/Izk2OEM4Qw==/dissolve/100/gravity/SouthEast/dx/10/dy/10|imageslim)
