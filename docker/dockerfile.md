### dockerfile

* ����
```text
1. ����
һ���������񹹽����ı��ļ�.

2. �����ʽ
docker build -t <������> .

3. ����ָ��
    ADD ���ڸ����ļ�
    --ADD <src>...<dest> 
    
    CMD: ������������
    
    COPY: ��ADD����,ֻ�ǲ�֧��url��ѹ����
   
    ENTRYPOINT: ��CMD����
    
    EXPOSE: ָ����¶�˿�
    
    FROM: ָ����������
    
    RUN: ��shell �ն�����,RUN command,RUN["executable","param1","param2"]
    eg: RUN ["/bin/bash","-c","echo start build"]
    
    VOLUME: ָ�����ص�,��ָ��ʹ������һ��Ŀ¼ӵ�г־û��洢����
    VOLUME /data
    
    WORKDIR: ָ������Ŀ¼,����cd. �ڸ�ָ��֮���RUN,CMD,ENTRYPOINT������Ŀ¼��Ϊ��ǰĿ¼
```

### ʵս����
* wordpress�
```text
docker pull mysql
docker run --name some-wordpress --link some-mysql:mysql -d wordpress
docker run --name mysql_wordpress -e MYSQL_ROOT_PASSWORD=wordpress  -d  mysql
docker run --name docker_wordpress --link mysql_wordpress:mysql -p 8080:80 -d wordpress

```
