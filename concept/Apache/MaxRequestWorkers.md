## httpd-mpm.conf - CentOS Apache2 기준
/usr/local/apache/conf/extra/httpd-mpm.conf

`MaxRequestWorkers` 설정은 Apache HTTP 서버의 다양한 멀티 프로세싱 모듈(MPM)에 의해 사용됩니다. 이 모듈들은 Apache가 클라이언트 요청을 처리하는 방식을 다르게 구현하며, 따라서 `MaxRequestWorkers` 설정이 각각 다른 역할을 합니다. 아래에서 각 MPM 모듈의 역할을 간략히 설명하겠습니다:

1. **mpm_prefork_module**:
   - `MaxRequestWorkers` 설정은 `mpm_prefork_module`에 의해 사용됩니다.
   - 이 MPM은 프로세스를 사용하여 클라이언트 요청을 처리합니다. 각 요청은 별도의 Apache 프로세스에서 처리됩니다.
   - `MaxRequestWorkers` 설정은 동시에 실행되는 Apache 프로세스 수를 제한하는 역할을 합니다. 설정된 값은 서버가 동시에 처리할 수 있는 클라이언트 요청 수를 결정합니다.

2. **mpm_worker_module**:
   - `MaxRequestWorkers` 설정은 `mpm_worker_module`에 의해 사용됩니다.
   - 이 MPM은 프로세스와 스레드를 사용하여 클라이언트 요청을 처리합니다. 각 프로세스 내에서 여러 스레드가 요청을 병렬로 처리합니다.
   - `MaxRequestWorkers` 설정은 동시에 실행되는 Apache 프로세스와 각 프로세스 내의 스레드 수를 제한하는 역할을 합니다. 설정된 값은 서버가 동시에 처리할 수 있는 클라이언트 요청 수를 결정합니다.

3. **mpm_event_module**:
   - `MaxRequestWorkers` 설정은 `mpm_event_module`에 의해 사용됩니다.
   - 이 MPM도 프로세스와 스레드를 사용하여 클라이언트 요청을 처리하지만, `mpm_worker_module`와 달리 일부 프로세스가 스레드 대신 이벤트 처리를 담당합니다.
   - `MaxRequestWorkers` 설정은 동시에 실행되는 Apache 프로세스와 각 프로세스 내의 스레드 및 이벤트 처리 프로세스 수를 제한하는 역할을 합니다. 설정된 값은 서버가 동시에 처리할 수 있는 클라이언트 요청 수를 결정합니다.

`mpm_prefork_module`, `mpm_worker_module`, `mpm_event_module` 중 어떤 모듈을 사용할지는 Apache의 운영 환경과 성능 요구 사항에 따라 다릅니다. 각 모듈은 장단점을 가지고 있으며, 서버의 하드웨어 및 애플리케이션의 특성에 따라 선택됩니다. `MaxRequestWorkers` 설정은 선택한 MPM 모듈에 따라 서버의 성능을 효과적으로 조절하는 데 사용됩니다.
