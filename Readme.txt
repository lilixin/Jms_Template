#spring jms消息使用模板  

原生JMS 未使用mq

运行条件 ：

本地创建数据库membership   
表membership.member_register_job
账号密码  root root    db.properties里面改

运行需要qpid.properties里面配置的swiftQ(本地没装)

80端口运行

生产消息  http://localhost/test/produce.json
查询接口  http://localhost/reg/data.json