### Java ��Ŀ���������
* jdk ��װ
```text
�������
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
����jdk
wget http://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-linux-x64.tar.gz

������װĿ¼
mkdir /usr/local/java/

��ѹ����װĿ¼
tar -zxvf jdk-8u171-linux-x64.tar.gz -C /usr/local/java/

���û�������
vim /etc/profile

export JAVA_HOME=/usr/local/java/jdk1.8.0_171
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

ˢ�»�������
source /etc/profile

���������
ln -s /usr/local/java/jdk1.8.0_171/bin/java /usr/bin/java

```
* maven ��װ
```text

```
* git ��װ


[mysql��װ�̳�](https://www.linuxidc.com/Linux/2016-09/135288.htm)
