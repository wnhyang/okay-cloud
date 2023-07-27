FROM registry.cn-shanghai.aliyuncs.com/wnhyang/openjdk:8-jre

# 创建目录
RUN mkdir -p /home/app
# 指定路径
WORKDIR /home/app

ARG JAR_FILE=./target/okay-shortLink-service.jar

COPY ${JAR_FILE} app.jar

EXPOSE 3301

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["java","-jar","app.jar","$JAVA_OPTS"]
