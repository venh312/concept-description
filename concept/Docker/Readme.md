## 📌 도커
컨테이너를 만들고 사용할 수 있도록 하는 컨테이너화 기술로써 애플리케이션의 실행에 필요한 환경을 하나의 이미지로 모아두고, 
그 이미지를 사용하여 다양한 환경에서 애플리케이션 실행 환경을 구축 및 운영하기 위한 오픈소스 가상화 플랫폼입니다.

### 도커 엔진
도커 엔진(Docker Engine)은 컨테이너화된 애플리케이션을 개발, 배포, 실행하기 위한 오픈 소스 플랫폼입니다. 도커는 가상화 기술을 사용하여 애플리케이션과 
그에 필요한 종속성들을 컨테이너로 패키징하고, 호스트 시스템에서 동작하도록 하는 것을 중점으로 합니다.

### 간단한 도커 엔진의 구성 요소
1. **도커 데몬(Docker Daemon):** 도커 엔진의 핵심 구성 요소로, 호스트 시스템에서 컨테이너의 생성, 실행, 관리를 담당합니다. 데몬은 도커 API 요청을 수신하고, 컨테이너의 라이프 사이클을 관리합니다.

2. **도커 클라이언트(Docker Client):** 사용자가 도커와 상호작용하기 위한 명령행 도구 또는 API를 통한 클라이언트입니다. 클라이언트는 도커 데몬과 통신하여 컨테이너 관리 작업을 수행합니다.

3. **도커 레지스트리(Docker Registry):** 도커 이미지를 저장하고 공유하기 위한 저장소입니다. 기본적으로 도커 허브(Docker Hub)라는 공식 레지스트리가 제공되며, 사용자는 필요에 따라 자체 레지스트리를 운영할 수도 있습니다.

4. **도커 이미지(Docker Image):** 컨테이너를 생성하는 데 사용되는 템플릿입니다. 이미지는 파일 시스템, 실행 환경, 애플리케이션 코드, 라이브러리 등 모든 것을 포함합니다.

5. **도커 컨테이너(Docker Container):** 이미지를 기반으로 생성된 실행 가능한 인스턴스로, 격리된 환경에서 애플리케이션이 실행됩니다. 각 컨테이너는 독립적이며, 호스트 시스템과 다른 컨테이너와 격리되어 있습니다.

도커의 핵심 가치 중 하나는 `컨테이너가 어떤 환경에서도 동일하게 실행`될 수 있다는 것입니다. 이는 개발 환경, 테스트 환경, 프로덕션 환경에서 일관성 있는 배포를 가능케 하며, 애플리케이션 간의 종속성 충돌을 방지하는데 도움이 됩니다.

### 도커의 내부 동작 방식

1. **도커 이미지:**
   - 도커 이미지는 애플리케이션을 실행하는 데 필요한 모든 것을 포함하는 파일 시스템 스냅샷입니다.
   - 이미지는 여러 레이어로 구성되며, 각 레이어는 파일 시스템 변경 사항을 나타냅니다.
   - 이미지는 읽기 전용이며, 컨테이너를 실행할 때 이 이미지를 기반으로 새로운 읽기/쓰기 가능한 레이어가 생성됩니다.

2. **도커 레지스트리:**
   - 도커 레지스트리는 도커 이미지를 저장하고 공유하는 원격 저장소입니다. 도커 허브(Docker Hub)는 도커의 공식 레지스트리입니다.
   - 개발자는 레지스트리에 이미지를 푸시(push)하여 공유하거나, 풀(pull)하여 이미지를 다운로드합니다.

3. **도커 컨테이너:**
   - 도커 컨테이너는 이미지를 기반으로 실행되는 가상 환경입니다.
   - 컨테이너는 격리된 네트워크, 파일 시스템, 프로세스 공간을 가지고 있어 독립적으로 실행될 수 있습니다.
   - 컨테이너는 호스트 시스템의 도커 데몬에서 관리되며, 여러 컨테이너 간에는 격리가 이루어집니다.

4. **도커 데몬:**
   - 도커 데몬은 호스트 시스템에서 실행되는 백그라운드 프로세스로, 도커 클라이언트와 API를 통해 상호작용합니다.
   - 데몬은 컨테이너의 라이프사이클 관리, 이미지 빌드, 네트워킹, 스토리지 관리 등의 역할을 수행합니다.

5. **도커 클라이언트:**
   - 도커 클라이언트는 사용자가 도커와 상호작용하기 위한 인터페이스입니다.
   - 명령행 도구(`docker` 명령어)나 API를 통해 도커 데몬과 통신하여 컨테이너, 이미지, 레지스트리 관리 등의 작업을 수행합니다.

6. **도커 네트워킹:**
   - 도커는 컨테이너 간의 네트워크 통신을 지원하며, 여러 컨테이너가 동일한 호스트에서 서로 통신할 수 있습니다.
   - 다른 호스트에 있는 컨테이너 간의 네트워크 통신은 도커 네트워크 기능을 사용하여 설정할 수 있습니다.

이러한 구성 요소들이 조합되어 도커는 애플리케이션을 컨테이너로 패키징하고 실행하는 데 사용됩니다. 

## 📌 쿠버네티스 - (구글이 오픈소스로 공개)
도커의 등장으로 컨테이너 기반 배포 방식이 보편화되고, 많은 서비스들이 도커라이징 되어 이미지로 관리되기 시작했습니다.
점점 이미지가 많아지면서, 관리해야할 컨테이너와 서버들 또한 많아지게 되면서 컨테이너들의 관리를 자동화할 도구 (컨테이너 오케스트레이션 툴) 의 필요성이 대두되기 시작합니다.

### 컨테이너 오케스트레이션 툴
(Kubernetes, Docker Swarm, AWS의 ECS< 하시코프의 Nomad, Mesos의 Marathone 등)


### 쿠버네티스에서 프로그램을 사용하는 과정
1) Docker Build (애플리케이션 코드를 내려 받아 도커 컨테이너 이미지로 빌드)
2) Docker Push (컨테이너 이미지를 레지스트리(저장소)에 등록)
3) kubectl create (레지스트리에 등록된 이미지를 기바느로 쿠버네티스 오브젝트 생성)
4) kubectl expose (쿠버네티스 오브젝트를 외부에서 접속할 수 있도록 서비스 형태로 노출)
