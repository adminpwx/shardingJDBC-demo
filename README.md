# 仅用于ShardingJDBC学习





# MySql配置读写分离



步骤（Windows）

1. 创建两个MySql数据库服务，并启动两个MySql服务

   1. 复制原来MySql

      ![image-20220409215738449](.\README.assets\image-20220409215738449.png)

   2. 修改复制后的配置文件，改端口号和mysql路劲以及data存储的路径

      ![image-20220409222010248](.\README.assets\image-20220409222010248.png)

   3. 安装

      ```bash
      E:\java\MySQL\mysql-5.7.34-winx64-slave\bin>mysqld install mysqlslave --default-file="E:\java\MySQL\mysql-5.7.34-winx64-slave\my.ini"
      Service successfully installed.
      ```

   4. 然后连上就行了

   5. 删除服务命令

      ```bash
      sc delete 服务名称
      ```

2. 配置数据库主从关系

   1. 在主服务器配置

      ```ini
      [mysqld]
      # binlog文件名
      log-bin=mysql-bin
      # 选择row模式
      binlog_format=ROW
      # mySql实例id，不能和cannal的slaverId重复
      server_id=1
      #设置需要同步的数据库
      binlog-do-db=testmaster
      # 屏蔽系统数据库
      binlog-ignore-db=mysql
      binlog-ignore-db=information-schema
      binlog-ignore-db=performance-schema
      ```

      

   2. 在从服务器配置

      ```ini
      [mysqld]
      # binlog文件名
      log-bin=mysql-bin
      # 选择row模式
      binlog_format=ROW
      # mySql实例id，不能和cannal的slaverId重复
      server_id=2
      #设置需要同步的数据库
      replicate_wild_do_table=testmaster.%
      # 屏蔽系统数据库
      replicate_wild-ignore-table=mysql.%
      replicate_wild-ignore-table=information-schema.%
      replicate_wild-ignore-table=performance-schema.%
      ```

      

   3. 两个服务都重启一下

3. 创建用于主从复制的账号（也可以直接用原来的）

   1. 切换到主库

      ```sql
      # 刷新权限
      FLUSH PRIVILEGES
      # 授权主从复制专用账号
      GRANT REPLICATION SLAVE ON *.* TO 'db_sync'@'%' IDENTIFIED BY 'db_sync';
      ```

      ![image-20220410114623063](.\README.assets\image-20220410114623063.png)

   2. 确定位点，记录名字

      ```
      mysql-bin.000001
      ```

      ![image-20220410114731938](.\README.assets\image-20220410114731938.png)

4. 设置从库向主库同步数据

   1. 切换到从库

   2. 先停止同步

      ```sql
      STOP SLAVE
      ```

   3. 修改从库指向到主库，使用上一步记录的文件名以及位点

      ```sql
      CHANGE MASTER TO
      MASTER_HOST = 'localhost',
      MASTER_USER = 'db_sync',
      MASTER_PASSWORD = 'db_sync',
      MASTER_LOG_FILE = 'mysql-bin.000001',
      MASTER_LOG_POS = 154;
      ```

   4. 启动同步

      ```sql
      START SLAVE
      ```

   5. 查看从库当前状态

      ```sql
      SHOW SLAVE STATUS
      ```

      ![image-20220410151938176](.\README.assets\image-20220410151938176.png)

      结果我失败了，失败原因

      ![image-20220410152016206](.\README.assets\image-20220410152016206.png)

      由于从服务器是克隆的主服务器系统，导致主从mysql uuid相同， Slave_IO无法启动，

      所以这时候我们需要手动修改从机的uuid

      在data文件中找到auto.cnf进行修改

      ![image-20220410152804023](.\README.assets\image-20220410152804023.png)

      修改之后重启服务，然后就可以了

      ![image-20220410152730493](.\README.assets\image-20220410152730493.png)

   6. 最后自己到主库中尝试一下，这就完成了配置