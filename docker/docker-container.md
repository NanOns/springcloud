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
