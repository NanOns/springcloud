### docker ��������
* docker ��װ
```text
ɾ���ɰ汾
yum remove -y docker docker-common docker-selinux docker-engine

��װ
yum install docker 

����
systemctl

```
* ���񹹽���������
```text
mvn package docker:build

docker build --rm -t user-service .
```
* ɾ��ȫ���Ѿ�ֹͣ������
```text
1.List all exited containers
docker ps -aq -f status=exited

2.Remove stopped containers
docker ps -aq --no-trunc -f status=exited | xargs docker rm
```

* ���������ڲ��ն�
```text
docker  exec -i -t  <����ID or ��������>  /bin/bash
```
