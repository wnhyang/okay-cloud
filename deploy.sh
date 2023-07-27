#!/bin/bash

SH_NAME=$0
ACTION=$1
APP_NAME=$2
APP_LOG_NAME=$3
APP_PORT=$4
DOCKER_URL=$5

usage() {
    echo "Not Support this operation: "${ACTION}
    echo "Usage: ${SH_NAME} {start|stop|restart} {APP_NAME} {APP_LOG_NAME} {APP_PORT} {ENV_PROFILES}"
}

stop_app() {
    echo 'stopping......'
    # 获取容器id
    CONTAINER=`docker ps -a |grep "${APP_NAME}" | awk '{print $1}'`
    # -n 字符串不为空返回true
    # 删除容器
    if [[ -n "${CONTAINER}" ]]
        then
        docker stop ${CONTAINER}
        docker rm ${CONTAINER}
        echo "The container named ${APP_NAME} has been stopped and deleted"
    else
      echo "The container named ${APP_NAME} does not exist"
    fi
    # 删除镜像
    if [[ -n "${DOCKER_URL}" ]]
        then
        REPO_URL=`echo ${DOCKER_URL%%:*}`
        TAG_NAME=`echo ${DOCKER_URL##*:}`
    fi
    if [[ -n "${TAG_NAME}" ]]
        then
        IMAGE=`docker images | grep ${REPO_URL} | grep ${TAG_NAME} |awk '{print $3}'`
    else
        IMAGE=`docker images | grep ${REPO_URL}|awk '{print $3}'`
    fi
    if [[ -n "${IMAGE}" ]]
        then
        docker rmi ${IMAGE}
        echo "The image named ${APP_NAME} has been deleted"
    else
      echo "The image named ${APP_NAME} does not exist"
    fi

}

create_log_dir(){
    #日志路径要真实存在
    log_path="/home/app/logs/"${APP_LOG_NAME}
    mkdir -p ${log_path}
    echo "create a log path: ${log_path}"
}

start_app() {
    echo '镜像版本：'${DOCKER_URL}

    # 运行容器
    docker run -v /home/app/logs:/home/app/logs \
               -p ${APP_PORT}:${APP_PORT} \
               -p 9998:9998 \
               --add-host nacos:ip \
               --name ${APP_NAME} \
               -d ${DOCKER_URL}
     CONTAINER_ID=`docker ps -a | grep -v "grep" | grep ${APP_NAME} | awk -F: '{print $1}' | awk  '{print $1}' | head -n 1`
     if [[ -n "${CONTAINER_ID}" ]]
        then
        RESULT=`docker inspect -f '{{.State.Running}}' ${CONTAINER_ID}`
        if [[ ${RESULT} = 'true' ]]
           then
           echo "部署成功"
           exit
        fi
     fi
     echo "部署失败"
}

start() {
    create_log_dir
    start_app
}

stop() {
    stop_app
}

case "$ACTION" in
    start)
        start
    ;;
    stop)
        stop
    ;;
    restart)
        stop
        start
    ;;
    *)
        usage
    ;;
esac