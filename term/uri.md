## URI, URL, URN

### URL
- Uniform Resource Locator: 정형화 된 리소스(자원) 위치표시
- ex) `http://test.com/work/sample.pdf` -> test.com 서버에서 work 폴더안의 sample.pdf 를 요청하는 URL

### URI
- Uniform Resource Identifier: 통합 자원 식별자
- rewrite 기술을 사용하여 만든 의미있는 식별자 (`http://test.com/company/location`)
-  REST 서비스 (url로 실행되는 서비스)
-  Web-oriented architecture (웹 기반의 구조문법) -> `kakaotalk://sendmsg?text=hello!`  (이 uri는 kakaotalk 프로토콜을 해석할 수 있는 프로그램이 핸들링한다. 해당프로그램은 sendmsg 라는 식별자를 해석하고 동작한다.)
- URI = URL + URN

### URN
- Uniform Resource Name
- ex) `urn:uuid:6e8bc430-9c3a-11d9-9669-0800200c9a66`
- isbn이나 uuid를 해석가능한 프로그램이 있어야 동작

### References
- https://blog.lael.be/post/61
