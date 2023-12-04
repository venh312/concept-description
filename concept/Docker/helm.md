### edu 기반 helm 설치
https://artifacthub.io/packages/helm/edu/metallb
```
$ helm repo add edu https://iac-source.github.io/helm-charts
```

### 헬름 저장소가 정상적으로 등록됐는지 저장소 목록 확인
```
$ helm repo update
```

### 최신 차트 정보 동기화
```
$ helm repo update
```

### 명령인자로 전달하여 헬름 차트 설치
helm install metallb edu/metallb \
--namespace=metallb-system \
--create-namespace \
--set controller.tag=v0.8.3 \
--set speaker.tag=v0.8.3 \
--set configmap.ipRange=192.168.1.11-192.168.1.29

