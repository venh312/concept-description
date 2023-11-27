## 베이그런트(Vagrant)
사용자의 요구에 맞게 시스템 자원을 할당, 배치, 배포해 두었다가 필요할 때 시스템을 사용할 수 있는 상태로 만들어준다. 이를 프로비저닝(provisioning) 한다.


### 프로비저닝을 위한 기초 파일 생성한다.
```
$ vagrant init
```

### Vagrantfile을 읽어 들여 프로비저닝을 진행한다.
```
$ vagrant up
```

### 베이그런트에서 다루는 가상 머신을 종료한다,
```
$ vagrant halt
```

### 베이그런트에서 관리하는 가상 머신을 삭제한다.
```
$ vagrant destory
```

### 베이그런트에서 관리하는 가상 머신에 ssh로 접속한다.
```
$ vagrant ssh
```

### 베이그런트에서 관리하는 가상 머신에 변경된 설정을 적용한다.
```
$ vagrant provision
```

### 베이그런트 상태 확인 및 삭제
```
$ vagrant global-status --prune
$ vagrant destory -f
```
