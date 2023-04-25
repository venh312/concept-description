## OSI 7 - 네트워킹에 대한 표준을 7계층으로 나눈것 (통신이 일어나는 과정을 단계별로 파악하기 위해서)
### 인터넷 환경에서 통신하기 위해서 네트워킹에 대한 표준을 7계층으로 나눈 것입니다.
- A 어플리케이션 - L7, FTP, DHCP, HTTP, SMTP, DNS
- P 표현
- S 세션
- T 전송 - L4, TCP, UDP
- N 네트워크
- D 데이터
- P 물리

## TCP
- 신뢰성이 높은 프로토콜 (응답 여부를 확인 하기 때문에 ACK)
- UDP에 비해 느리다.
- 3 Way handshaking 연결 
- 4 Way handshaking 해제

## UDP
- 3way, 4way 과정을 거치지 않아 속도가 빠르다.
- 데이터 전송 위주

## DNS
- 도메인 주소를 IP주소로 변환해주는 시스템입니다.
1. URL입력하면
2. ISP가 관리하는 DNS 해석기에 요청을 라우팅시킵니다.
3. DNS 해석기의 루트 서버에 top-level > second-level > sub dns server 과정을 통해 ip주소를 찾게됩니다.
