### docker rabbitmq �̳�

* ��װ
```text
docker run -d --name myrabbitmq -p 5673:5672 -p 15673:15672 docker.io/rabbitmq:3-management
����ҳ��: http://ip:15367  Ĭ��:guest
```
* ���������ն�
```text
docker exec -it rabbitmq /bin/bash
```