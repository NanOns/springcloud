### docker redis ʹ�ý̳�
* ��װ
```text
docker pull  redis:3.2
```
* ����
```text
docker run -p 6379:6379 -v $PWD/data:/data  -d redis:3.2 redis-server --appendonly yes

��������:
redis-server --appendonly yes : ������ִ��redis-server�����������redis�־û�����
```
* redis-cli(�ͻ���)
```text
docker exec -it  <����ID> redis-cli
```