## OAuth - 다양한 플랫폼 환경에서 권한 부여를 위한 산업 표준 프로토콜
인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹사이트 상의 자신들의 정보에 대해 웹사이트나 애플리케이션의 접근 권한을 부여할 수 있는 공통적인 수단으로서 사용되는, 접근 위임을 위한 개방형 표준

- OAuth 2.0은 1.0에서 알려진 보안 문제 등을 개선한 버젼   
- 인증만 하는 것은 openID

## OAuth 구성 요소

### Resource Owner	
- 웹 서비스를 이용하려는 사용자, 자원(개인정보)을 소유하는 자
### Client
- 자사 또는 개인이 만든 애플리케이션 서버
- 클라이언트 라는 이름은 client가 Resource server에게 필요한 자원을 요청하고 응답하는 관계
### Authorization Server	
- 사용자의 동의를 받아서 권한을 부여하는 서버
- 사용자는 이 서버로 ID, PW를 넘겨 Authorization Code를 발급 받을 수 있다.
- Client는 이 서버로 Authorization Code을 넘겨 Token을 받급 받을 수 있다.
### Resource Server	
- 사용자의 개인정보를 가지고있는 애플리케이션 (Google, Facebook, Kakao 등등)
- Client는 발급 받은 Token을 이 서버로 넘겨 개인정보를 응답 받는다.
### Access Token	
- 자원에 대한 접근 권한을 Resource Owner가 인가하였음을 나타내는 자격증명
- Client는 Authorization Server로 부터 access token(비교적 짧은 만료기간-탈취 위험 보안 문제) 과 refresh token(비교적 긴 만료기간)을 함께 부여 받는다.
### Refresh Token	
- access token이 만료될 때 refresh token을 통해 access token을 재발급 받아 사용한다.
