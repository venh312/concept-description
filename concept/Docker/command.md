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
### 컨테이너 단순히 실행하기
```
$ docker run -d -p 8080:80 --name nginx-exposed -v /root/html:/usr/share/nginx/html --restart always nginx
```
- -d: 컨테이너를 백그라운드에서 구동한다.
- -p: 포트 설정
- --name: 컨테이너 이름을 지정한다.
- -v: [호스트 디렉토리]:[컨테이너 디렉토리]를 연결한다.(마운트, 볼륨)
- --restart always: 컨테이너의 재시작과 관련된 정책을 의미하는 옵션으로 오류가 발생하거나 중지되는 경우 즉시 재시작하거나, 컨테이너를 자동으로 시작하도록 설정할 수 있다.
  - 기본값은 `no`

### 컨테이너 상태 확인
```
$ docker ps
```

### 컨테이너 내부 확인하기
```
$ docker exec 1b4a24ab3ec8 ls /usr/share/nginx/html
```
컨테이너 ID(1b4a24ab3ec8)의 `/usr/share/nginx/html` 경로에서 `ls` 명령어를 실행한다.

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














