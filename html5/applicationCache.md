## Application Cache
- HTML5에서 offline application을 위해서 만들어진 API
- 웹의 자원을 Application level에서 cache를 제어할수 있다는것
- HTML5를 통하여, 서버 중심의 웹 데이터들이 클라이언트로 쉽게 데이터나 연산을 분산시키고 재활용할 수 있게 만들게 해주었다.

### 적용
#### 기본
- HTML문서에 manifest를 지정해야하는데 일종의 캐시설정 규칙을 파일로 저장하는것
- appcache.manifest라는 파일을 html문서와 동일한 곳에 저장
```html
CACHE MANIFEST
# version 0.1.0
CACHE:
css/style.css
js/app.js
images/1.png
http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js
NETWORK:
FALLBACK:
```

- CACHE MANIFEST는 반드시 포함되어야 하는것이다.
- CACHE는 한번 요청한 다음에 caching되어지는 URL규칙이다. 이와 동일한 URL이 브라우저에 요청되어지면, 이 설정을 보고 application cache에 저장된 자원을 재사용하게 된다.
- NETWORK는 HTML페이지가 요청될때 캐싱과 상관없이 매번 네트워크 요청을 하는 URL규칙이 들어가진다. 수시로 변할수 있는 파일을 주로 정의
- FALLBACK은 URL요청이 실패했을때 대처하는 URL을 정의해서 다른 URL요청으로 대신하는 규칙을 만들 수있다.
- manifest파일은 확장자에 제한이 없다.
- manifest파일이 변경되면 window.applicationCache.status에서 UPDATEREADY상태로 변경되기 때문에 새로운 이미지가 갱신된다.

#### 주의점
- manifest가 서버에서 요청할때 header정보에 Content-Type이 text/cache-manifest로 되어야지만 정상적으로 요청받고 적용할수 있다.
- 서버 프로그램에 Content-Type="text/cache-manifest"등을 지정해준다.
- 또는 apache웹서버 .htaccess파일에 `AddType text/cache-manifest .manifest`추가
- application cache를 사용하게 되면 cache를 적용한 html파일이 자동으로 캐싱되어 버린다. 그래서 html을 수정하더라도 cache된 html을 로드해서 적용되지 않는다. 방법은 manifest를 변경해서 application cache가 업데이트 된후 window.location.reload()를 해야한다.

#### html페이지
- HTML문서가 요청될때 브라우저가 HTML5 application level에서 이해를 해야하기 때문에 HTML파일을 수정해야한다.
```html
<!DOCTYPE html>
<html  manifest="appcache.manifest">
<head>
    <meta charset="utf-8"/>
    <title>HTML5 Application Cache</title>
    <link href="css/style.css" rel="stylesheet"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script src="js/app.js"></script>
    <script type="text/javascript">
        window.addEventListener('load', function(e) {
            window.applicationCache.addEventListener('updateready', function(e) {
                if (window.applicationCache.status == window.applicationCache.UPDATEREADY) {
                    window.applicationCache.swapCache();

                    if (confirm('A new version of this site is available. Load it?')) {
                        window.location.reload();
                    }
                } else {}
            },false);
        },false);

    </script>
</head>
<body>
        <header>
            <h1 id="title">HTML5 Application Cache</h1>
        </header>
        <section>
            <article id="contents">
                <h2 id="subject" class="subjects">HTML5 Application Cache example</h2>
                <ul>

                    <li><img src="images/1.png"/></li>
                </ul>
            </article>
        </section>

</body>
</html>
```
- javascript는 window가 로드될때 applicationCache의 업데이트 이벤트를 감지해서 변경된것이 있으면 applicationCache를 swapCache하고 reload시켜서 application cache를 업데이트 하는 코드이다.
- CACHE에 적은 URL을 사용하였다.

### reference
- http://blog.saltfactory.net/html5/growing-response-performance-using-with-application-cache.html
