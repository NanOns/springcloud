### docker redis ʹ�ý̳�
* ��װ
```text
docker pull  redis:3.2
```
* ����
```text
docker run -p 6379:6379 -v $PWD/data:/data  -d redis:3.2 redis-server --appendonly yes

docker run -p 6379:6379 --privileged=true --name myredis -v /home/redis/data/:/data -v /home/redis/redis.conf:/etc/redis/redis.conf -d redis:3.2 redis-server /etc/redis/redis.conf --appendonly yes

��������:
redis-server --appendonly yes : ������ִ��redis-server�����������redis�־û�����
```
* redis-cli(�ͻ���)
```text
docker exec -it  <����ID> redis-cli
```