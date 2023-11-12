## ⌨️ 도커 명령어 모음 (찾기, ctrl + f)

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

### 컨테이너 단순히 실행하기
```
$ docker run -d --restart always nginx
```
- -d(--detach): 컨테이너를 백그라운드에서 구동한다.
- --restart always: 컨테이너의 재시작과 관련된 정책을 의미하는 옵션으로 오류가 발생하거나 중지되는 경우 즉시 재시작하거나, 컨테이너를 자동으로 시작하도록 설정할 수 있다.
  - 기본값은 `no`


