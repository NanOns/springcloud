### docker nginx �̳�

* ��װ����
```text
1.��װ
docker pull nginx

2.����
docker run -p 80:80 -v $PWD/conf.d/default.conf:/etc/nginx/conf.d/default.conf -v $PWD/conf.d:/etc/nginx/conf.d -v $PWD/conf.d:/conf.d -v $PWD/www:/usr/share/nginx/html -v $PWD/conf/nginx.conf:/etc/nginx/nginx.conf -v $PWD/logs:/wwwlogs  -d nginx 
��������:
  -v ����ǰĿ¼���ڵ�������ӦĿ¼

ע:
 �������л���nginxӳ��Ŀ¼
  conf �ֶ�����nginx.conf
      
```