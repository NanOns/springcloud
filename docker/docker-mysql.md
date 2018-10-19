### docker mysql�̳�

* ��װ
```text
docker run -p 3306:3306 --name mymysql -v $PWD/conf:/etc/mysql/conf.d -v $PWD/logs:/logs -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
```
```text
-p 3306:3306���������� 3306 �˿�ӳ�䵽������ 3306 �˿ڡ�

-v -v $PWD/conf:/etc/mysql/conf.d����������ǰĿ¼�µ� conf/my.cnf ���ص������� /etc/mysql/my.cnf��

-v $PWD/logs:/logs����������ǰĿ¼�µ� logs Ŀ¼���ص������� /logs��

-v $PWD/data:/var/lib/mysql ����������ǰĿ¼�µ�dataĿ¼���ص������� /var/lib/mysql ��

-e MYSQL_ROOT_PASSWORD=123456����ʼ�� root �û������롣
```

* ������������
```text
docker run --name mysql2 --volumes-from mysql -d mysql:5.7

mysql2 ����mysql����Ŀ¼
```
* ����,Ǩ��, �ָ�
```text
����
docker run --rm --volumes-from mysql -v $(pwd):/backup mysql:5.7 tar cvf /backup/backup.tar /var/lib/mysql

�ָ�
docker run --rm --volumes-from mysql -v $(pwd):/backup mysql:5.7 bash -c "cd /var/lib/mysql && tar xvf /backup.tar --strip 1"

--rm 
```
[docker mysql ����](https://www.binss.me/blog/learn-docker-with-me-about-volume/)