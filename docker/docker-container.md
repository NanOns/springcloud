### docker  container ����
* ����
```text
����: docker run -����
-d ��̨����
-p <host>:<dest> �˿�ӳ��
-name ������������
-v <hostPath>:<containerPath> ����·��
```
* ������Դ����
```text
1. �ڴ�
docker run -m 100M --memory-swap 200M <imageName>
-m �����ڴ�����
--memory-swap �����ڴ�+swap��С
```
* cgroup(control group)
```text
cd /sys/fs/cgroup/
```

* docker inspect �鿴����������Ϣ(�洢λ��,���ò���,��������,�������)
```text
docker inspect <containerId> or <containerName>
```
* docker logs �鿴��������־
```text
docker logs <containerId> or <containerName>
```
* docker stats �鿴������״̬(CPU,�ڴ�,����or���̿���)
```text
docker stats <containerId> or <containerName>
```
* docker �����ڲ�ִ������
```text
1. docker exec ������+������ִ�е�����


2. docker exec -it <containerName> /bin/bash 
  -it �൱����root��ݵ��������ڲ�
  exit �˳�
```
* ǿ��ɾ���������е�����
```text
docker rm -f <containerName>
```

