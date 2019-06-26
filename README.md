## Pre-Requisites
```
$ sudo yum install epel-release
$ sudo yum install maven
$ sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
$ sudo yum install docker-ce
$ sudo yum install -y python-pip
$ sudo pip install docker-compose
$ sudo yum upgrade python*
```

## Maven
```
$ mvn archetype:generate  [the default one is simple Java project]
$ mvn package
```

## Docker Compose
```
$ sudo docker-compose up
$ sudo docker-compose stop
```

#### Error handling
Docker commands
```
$ sudo service docker start
$ sudo service docker stop
$ sudo docker ps -a
$ sudo docker rm [CONTAINER]
$ sudo docker rmi [IMAGE]
$ sudo docker network ls
```

> ERROR: Pool overlaps with other one on this address space
> Solution> sudo docker network prune


## System Info
```
[YOUR_IP] <== $ hostname -I | awk '{print $1}'
```

# Kafka

## Kafka Docker
```
$ git clone https://github.com/wurstmeister/kafka-docker
$ cd kafka-docker
$ vim docker-compose.yml
------
KAFKA_ADVERTISED_HOST_NAME: [YOUR_IP]
------
$ sudo docker-compose up -d
$ sudo docker-compose scale kafka=3
$ sudo docker ps -a
```

Now you can login your docker image
```
$ sudo docker exec -it [YOUR_CONTAINER_ID] /bin/bash
```

## Kafka Create Topic
```
$ wget https://www-eu.apache.org/dist/kafka/2.2.0/kafka_2.12-2.2.0.tgz
$ tar zxvf kafka_2.12-2.2.0.tgz; cd kafka_2.12-2.2.0
$ export ZK=[YOUR_IP]:2181
$ bin/kafka-topics.sh --create --topic MyTopic --partitions 4 --zookeeper $ZK --replication-factor 3
$ bin/kafka-topics.sh --list --zookeeper $ZK
$ bin/kafka-topics.sh --zookeeper $ZK --describe --topic MyTopic
```

## Test Kafka
```
$ cd streaming
$ sh run-build.sh
(tty1)$ sh run-producer.sh
(tty2)$ sh run-consumer.sh
```

# Spark

## Spark Docker Cluster
```
$ git clone https://github.com/pppk520/docker-spark-cluster.git
$ cd docker-spark-cluster
-----------
Check spark version on http://apache.mirror.iphh.net/spark/

$ vim docker/base/Dockerfile
ENV SPARK_VERSION=2.4.2
-----------
$ sudo ./build-images.sh
$ sudo docker-compose up
```
The Docker compose will create the following containers:

container|Ip address
---|---
spark-master|10.5.0.2
spark-worker-1|10.5.0.3
spark-worker-2|10.5.0.4
spark-worker-3|10.5.0.5

## Submit Spark Job

## Check Job Status

Spark Master
http://[IP_OR_DNS].westus.cloudapp.azure.com:8080

## Connect to Spark Worker
```
docker exec -ti spark-worker-2 /bin/bash
```



