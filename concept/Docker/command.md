### ⌨️ 도커 명령어 모음 (찾기, ctrl + f)

## 📌 이미지 
### 이미지 검색하기 (nginx)
```
$ docker search nginx
```

### 이미지 내려받기, 다운로드 (nginx)
```
$ docker pull nginx
```
아무런 조건을 주지 않고 이름만으로 `pull`을 수행하면 기본적으로 `latest` 태그가 적용된다.

#### 안정화 버전(stable)을 사용하고 싶다면...
```
$ docker pull nginx:stable
```

### 이미지 조회하기 (전체)
```
$ docker image ls
```

### 이미지 조회하기 (nginx, 특정)
```
$ docker images nginx
```

### 이미지 생성 과정 내역 조회하기 (nginx)
```
$ docker history nginx:latest
```

## 📌 컨네이너
### 컨테이너 실행하기
```
$ docker run -d -p 8080:80 --name nginx-exposed -v /root/html:/usr/share/nginx/html --restart always nginx
```
- -d: 컨테이너를 백그라운드에서 구동한다.
- -p: 포트 설정
- --name: 컨테이너 이름을 지정한다.
- -v: [호스트 디렉토리]:[컨테이너 디렉토리]를 연결한다.(마운트, 볼륨)
- --restart always: 컨테이너의 재시작과 관련된 정책을 의미하는 옵션으로 오류가 발생하거나 중지되는 경우 즉시 재시작하거나, 컨테이너를 자동으로 시작하도록 설정할 수 있다.
  - 기본값은 `no`

### 컨테이너 모두 실행하기
```
docker start $(docker ps -aq)
```

### 컨테이너 중지,종료하기
```
$ docker stop nginx-exposed
```

### 컨테이너 모두 중지,종료하기
```
docker stop $(docker ps -aq)
```

### nginx 이미지를 사용하는 모든 컨테이너 ID 조회 (-q 옵션)
```
$ docker ps -q -f ancestor=nginx
```

### nginx 이미지를 사용하는 모든 컨테이너 중지
```
$ docker stop $(docker ps -q -f ancestor=nginx)
```

### 컨테이너 상태 확인
```
$ docker ps
$ docker ps -a
```

### 컨테이너 shell 접속하기
```
$ docker exec -it 컨테이너ID /bin/bash
```

### 컨테이너 shell root계정 접속하기
```
$ docker exec -u 0 -it 컨테이너ID /bin/bash
```

### 컨테이너 내부 확인하기
```
$ docker exec 1b4a24ab3ec8 ls /usr/share/nginx/html
```
컨테이너 ID(1b4a24ab3ec8), 또는 이름도 가능)의 `/usr/share/nginx/html` 경로에서 `ls` 명령어를 실행한다.

### 컨테이너 삭제하기
```
$ docker rm 컨테이너ID
```

### 볼륨 생성하기
```
$ docker volume create nginx-volume
```

### 볼륨 전체 조회하기
```
$ docker volume ls
```

### 볼륨 상세 조회하기
```
$ docker volume inspect nginx-volume
```
```
[
    {
        "CreatedAt": "2023-11-14T22:35:22Z",
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/var/lib/docker/volumes/nginx-volume/_data",
        "Name": "nginx-volume",
        "Options": {},
        "Scope": "local"
    }
]
```
`nginx-volume` 이름으로 생성한 볼륨으로 컨테이너 실행 시 바인드 마운트 처럼 사용할 수 있으며, 볼륨은 빈 디렉토리를 덮어 쓰지 않고 컨테이너 디렉토리 파일을 보존한다.

## 📌 컨테이너 이미지 빌드하기 
소스 코드가 자바로 작성되어 있는 구성에서 작성한 내용입니다.

### 패키지 구조
- DockerFile
- mvnw
- pom.xml
- src

아래 과정은 DockerFile에 작성되어있는 내용으로 Docker build 되며, `chmod 700 mvnw`, `./mvnw clean package` 선 작업으로 target 폴더에 jar 파일이
존재하는 상태에서 작성되었습니다.
```
$ docker build -t basic-img .
```
-t(tag)는 만들어질 이미지를 의미하고, .(dot, 점)은 이미지에 원하는 내용을 추가하거나 변경하는데 필요하나 작업공간을 현재 디렉토리로 지정한다는 의미이다.

### 잠깐, 여기서 DockerFile 이란?
빌드용 DSL(Domain-Specific Language, 도메인 특화 언어)로 작성된 파일이다.

#### DockerFile 작성 예
```
FROM openjdk:8 -- 1
LABEL description="Echo IP Java Application" -- 2
EXPOSE 60431 -- 3
COPY ./target/app-in-host.jar /opt/app-in-image.jar -- 4
WORKDIR /opt -- 5
ENTRYPOINT [ "java", "-jar", "app-in-image.jar" ] -- 6
```
#### 1: openjdk:8 이미지 내부에서 컨테이너 이미지를 빌드한다. 여기서 openjdk를 기초 이미지로 사용한다.
#### 2: 이미지에 부가적인 설명을 위한 레이블을 추가할 때 사용된다.
#### 3: 컨테이너로 구동할 때 어떤 포트를 사용하는지 알려준다. ❗️ 주의, 컨테이너를 구동할 떄 자동으로 해당 포트를 호스트 포트와 연결하지 않는다. 단지 정보를 제공할 뿐이다.
#### 4: 호스트에서 새로 생성하는 컨테이너 이미지로 필요한 파일을 복사한다. app-in-host.jar 파일을 이미지의 /opt/app-in-image.jar 로 복사한다.
#### 5: 이미지의 현재 작업 위치를 opt로 변경한다.
#### 6: 대괄호 안에 든 명령을 실행한다. 콤마로 구분된 문자열 중 첫 번째 문자열을 실행할 명령어고, 두 번째 문자열부터 명령어를 실행할 때 추가하는 옵션이다. 여기서는 java -jar app-in-image.jar 가 실행된다는 의미이다.

### 태그 사용하기
```
$ docker build -t basic-img:1.0 -t basic-img:2.0 .
```
태그 옵션(-t)을 추가하여 1.0과 2.0 태그 이미지를 생성한다. 빠른 속도로 생성이 되는데 그 이유는 이미 생성되어있는 basic-img 이미지를 기반으로하여 캐시하기 때문이다.

---

## 📌 쿠버네티스
### 현재 사용하는 도커 버전 확인
```
$ kubectl get nodes -o wide
```
`CONTAINER-RUNTIME` 부분을 확인한다.

### 디플로이먼트 조회
```
$ kubectl get deployments
```

### 서비스 조회
```
$ kubectl get services
```

### 디플로이먼트 생성
```
$ kubectl create deployment failure1 --image=basic-img
```
- --image: 대상 이미지 지정
- failure1: failure1 라는 이름으로 지정
ErrorImagePull과 ImagePullBackOff 가 발생하는 이유는 이미지가 호스트에 존재함에도 기본 설정에 따라 외부(도커 허브)에서 내려 받으려고 시도하기 때문이다.

### 디플로이먼트 생성 (내부 이미지 이용)
```
$ kubectl create deployment failure2 --dry-run=client -o yaml --image=basic-img > failure2.yaml
```

### 수정한 yaml 파일을 디플로이먼트에 적용
```
$ kubectl apply -f failure2.yaml
```

### pod의 상태 및 변화 확인하기
```
kubectl get pods -w
```

### pod에 포트포워딩
```
$ kubectl port-foward pod/{pod 명} {포트}:{대상 포트}
```
   
---
   
## 📌 레지스트리 구성하기 - 265p
호스트에서 생성한 이미지를 쿠버네티스에서 사용하려면 모든 노드에서 공통으로 접근하는 레지스트리(저장소)가 필요하다. 또는 도커허브를 이용해서 이미지를 업로드 한 뒤 사용하는 방법도 있다.










