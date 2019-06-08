## Cloud service

### 클라우드 컴퓨팅(Cloud Computing)
클라우드 컴퓨팅은 개인이 가진 스마트폰, 스마트패드, 스마트 TV, 노트북, 컴퓨터와 같은 인터넷이 가능한 디바이스를 통해 클라우드라고 불리는 제 3의 공간에서 데이터를 읽고 쓰고 정보를 분석하고 처리하여, 저장하고 관리하는 컴퓨팅 시스템이라고 볼 수 있습니다.

#### IaaS(Infrastructure as a Service)
IaaS(Infrastructure as a Service)는 서버, 스토리지, 네트워크를 가상화 환경으로 만들어, 필요에 따라 인프라 자원을 사용할 수 있게 제공하는 서비스 형태입니다. 쉽게 말해서, 웹 서버나 애플리케이션 서버로 사용할 리눅스 혹은 윈도우즈 서버를 호스팅 업체에서 임대 하는 것과 비슷한 개념이지요.
- virtualization, server, storage...

#### PaaS(Platform as a Service)
PaaS(Platform as a Service)는 SaaS(Software as a Service)의 개념을 개발 플랫폼에도 확장한 방식으로, 개발을 위한 플랫폼 구축을 할 필요 없이 필요한 개발 요소들을 웹에서 쉽게 빌려 쓸 수 있는 서비스를 말합니다.
- Middleware, os...

#### SaaS(Software as a Service)
SaaS(Software as a Service)는 IaaS와 PaaS 위에 올라가는 소프트웨어를 말하며, “on-demand software” 라고도 불립니다. 사용자는 웹 브라우저 등의 클라이언트를 통해 중앙에서 호스팅 되고 있는 소프트웨어에 접속하여 서비스를 받습니다.
- Application ...

### IaaS - compute service
- 컴퓨트 서비스(Compute Service)는 사용자가 원하는 운영체제가 탑재되어 있는 컴퓨터 혹은 서버를 인터넷상에서 접근하여 사용할 수 있도록 제공하는 유료 또는 무료 서비스
- 할당해 주는 가상 서버를 인스턴스라고 부릅니다

### IaaS - storage service
- 스토리지 서비스(Storage Service)는 사용자들이 가지고 있는 데이터나 음악, 동영상, 문서와 같은 파일을 인터넷상에 존재하는 스토리지에 저장, 삭제, 공유 등을 할 수 있도록 제공해 주는 서비스
- 아마존의 S3(Simple Storage Service)나 오픈스택의 오브젝트 스토리지인 Swift


### 블록 스토리지 (Block Storage)
- 인스턴스는 실제 물리 컴퓨터를 여러 대 사용할 수 있도록 가상화 시켜 놓은 컴퓨터이므로 실 컴퓨터처럼 하드 디스크를 추가할 수 있습니다.
- 클라우드 컴퓨팅에서 컴퓨터나 서버를 인스턴스라고 부르는 것처럼, 인스턴스에 추가하는 하드 디스크를 블록 스토리지(Block Storage)라고 부릅니다.

### 오브젝트 스토리지(Object Storage)
- 블록 스토리지와는 다르게 단독으로 구성될 수 있으며, 사용자 계정의 컨테이너에 파일이나 데이터를 저장할 수 있는 저장 공간입니다.

### 예제
#### 클라우드스택(CloudStack)
미국의 Cloud.com사가 개발한 오픈 소스 클라우드 환경 구축 소프트웨어로, GUI 기반의 관리 콘솔, 멀티 하이퍼바이저, 소프트웨어 방화벽, 로드밸런서를 기본으로 제공합니다. 이후에 2011년 7월 Citrix Systems가 인수하였으며, 2012년 5월에 다시 Apache 재단으로 인수되었습니다. 지원하는 하이퍼바이저 종류로는 KVM, XEN, XEN Server, VMware의 ESXI 등을 지원하고 있습니다.

#### 유칼립투스(Eucalyptus)
클라우드 컴퓨팅 시스템에 대한 활발한 연구와 커뮤니티를 조성하기 위해 미국 UC 산타바바라 대학에서 시작된 오프소스 프로젝트입니다. 현재는 Eucalyptus Systems라는 회사에서 관리되고 있으며, 지원하는 하이퍼바이저로는 KVM, XEN, VMware의 ESXi 가 있습니다.

#### 오픈네뷸라 (Open Nebula)
2008년 3월에 TP1을 최초로 릴리즈하면서 탄생하였습니다. 현재는 C21Labs에서 운영 및 유지보수를 하고 있으며, 2012년 10월에 릴리즈 한 3.8 버전부터는 상업용 제품에 대해서만 유지보수를 하고 있습니다. 지원하는 하이퍼바이저는 KVM, XEN, VMware ESXi, Hyper-V가 있습니다.

### 오픈스택(OpenStack)
2010년 7월 NASA와 Rackspace가 손을 잡고 시작한 오픈 소스 프로젝트로써, 컴퓨트 서비스인 Nova, 오브젝트 스토리지 서비스인 Swift, 이미지 서비스인 Glance로 구성이 되어 있으며, 이 외에도 네트워크를 담당하는 Quantum, 인증을 담당하는 Keystone, 블록 스토리지 서비스인 Cinder, 웹UI인 Horizon이 있습니다. 오픈스택에서 지원하는 하이퍼바이저의 종류로는 KVM, XEN, QEMU, LXC, VMware ESX/ESXi, PowerVM, Hyper-V 등이 있습니다.

### reference
- http://naleejang.tistory.com/98