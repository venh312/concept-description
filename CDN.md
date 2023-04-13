### CDN이란(Content Delivery Network, CDN)
컨텐츠의 빠르고 효율적인 전송을 돕는 '컨텐츠 전송 네트워크'를 뜻한다.

분산되어 있는 캐시 서버에 컨텐츠를 옮겨 두었다가 사용자의 요청이 있을 때 사용자에게서 가장 가까운 서버에서 해당 컨텐츠를 전달하는 용도로 사용한다.

CDN은 컨텐츠 전달이라는 용도에 특화된것으로 이미지나 기타 정적 요소들이 상당수 저장, 캐싱되어 사용된다.

### CDN의 구성 요소 
- CDN 서버 : 컨텐츠 저장, 최종 사용자에게 스트리밍 서비스 
- Contents 분배 : 지정된 컨텐츠를 스케쥴에 따라 지정된 CDN 서버에 전송하여 최신 컨텐츠 버전을 유지함. 
- GLB라우터 : 지역적으로 분산되어 설치된 여러 CDN 서버를 사용자와 가장 가까운 CDN서버에서 사용자가 서비스 받을 수 있도록 하는 라우터 
- CDN관리 및 모니터링 SW : 분산되어 있는 CDN서버를 중앙에서 관리, 장애상황 대처

### CDN 기술요소 
- Contents Sync : CP의 웹컨텐츠 중 변경된 내용이 있다면 CDN 서버와 Sync
- Caching 기술 : 자주 사용되는 파일 캐쉬 서버에 저장  
  - Pull 모델 : ISP들의 POP지점에 Cache 서버 배치 
  - Push 모델 : 캐쉬서버를 웹서버 앞에 위치 
- Load Balancing : 서버사이의 트래픽 향상
  - Product-Based : 기업 소유 형태
  - Service-Based : 아웃소싱 형태 
- Streaming 기술 : 실시간으로 사용자가 원하는 컨텐츠 전송 기술
  - Multicasting Streaming : 동시에 많은 고객
  - On-demand Streaming : 주문형 서비스
