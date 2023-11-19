### Homebrew 소개
Homebrew는 루비로 개발된 Mac 용 패키지 관리 애플리케이션입니다. 프로그램을 설치할 때 설치 파일을 다운로드 받은 후 설치 파일을 실행하여 설치하는게 아니라 터미널에서 명령어를 입력하여 프로그램을 설치할 수 있습니다.
이러한 편의성 때문에 Mac 사용자라면 반드시 설치해야 하는 필수 프로그램으로 사실상 패키지 관리 애플리케이션에서는 Mac에서 표준으로 자리 잡고 있습니다.

#### Homebrew 공식 홈페이지 : https://brew.sh/

### Homebrew 설치하기
Homebrew를 설치하기 위해서 터미널을 실행하고 다음과 같이 입력합니다.
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

- MacBook 패스워드를 입력 후 설치를 계속 진행합니다.
- 계속 진행하기 위해 엔터키를 누릅니다.

설치 완료 후 터미널에 `brew -v`

만약 다음과 같이 오류가 발생한다면 Homebrew 경로를 추가합니다.
```
$ brew -v
zsh: command not found: brew
```

### zshrc에 homebrew path 추가
```
$ echo 'export PATH=/opt/homebrew/bin:$PATH' >> ~/.zshrc
```
### zshrc 반영
```
$ source ~/.zshrc
```

### 프로그램 설치 관련
- brew update : brew를 최신 버전으로 업데이트
- brew search <패키지명> : 설치 가능한 프로그램 검색
- brew install <패키지명>[@버전] : 프로그램 설치[@특정버전]
- brew upgrade <패키지명> : 패키지 업그레이드
- brew upgrade : 모드 패키지 업그레이드

### 정보 확인
- brew list : 설치된 프로그램 목록
- brew info <패키지명> : 패키지 정보 보기
- brew outdated : 업그레이드 필요한 프로그램 찾기

### 삭제
- brew cleanup <패키지명> : 최신버전 이외의 버전들 전부 삭제
- brew uninstall <패키지명> : 특정 패키지 삭제

### 프로그램 설치하기
Homebrew로 yarn을 설치해보겠습니다.
```
$ brew install yarn
```

### 프로그램 제거하기
brew uninstall <패키지명> 명령을 사용하여 프로그램을 제거할 수 있습니다.

앞서 설치한 yarn을 제거하겠습니다.
```
$ brew uninstall yarn
```

### Homebrew 제거하기
Homebrew를 제거하기 위해서 터미널을 실행하고 다음과 같이 입력합니다.
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/uninstall.sh)"
```
삭제를 계속하기 위해서 Y를 입력합니다.

MacBook 패스워드를 입력 후 삭제를 계속 진행합니다.

### 실제 경로 제거
`/opt/homebrew` 폴더를 제거
