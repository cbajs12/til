## Serialization

### Serialization
- 객체를 직렬화하여 전송 가능한 형태로 만드는것을 의미한다.
- 객체들의 데이터를 연속적인 데이터(바이트 단위)로 변형하여, Stream을 통해 데이터를 읽도록 해준다.
- 주로 객체들을 통째로 파일로 저장하거나 전송하고 싶을때 주로 사용된다.
- 직렬화하면 내부에서 자동으로 SerialVersionUID라는 고유의 번호를 생성하여 관리한다. UID는 직렬화와 역직렬화할때, 이 값이 맞는지 확인 후 처리한다.
- RMI : 원격객체통신을 지원해야하기 때문에 객체가 그대로 이동해야하는데, 이를 위해 직렬화 필요
- Beans : 설계시 상태정보를 지정할수 있는데, 이때 객체 직렬화를 사용하여 편하게 객체의 상태정보를 저장

### Deserialization
- 직렬화된 파일 등을 역으로 직렬화하여 다시 객체의 형태로 만드는 것을 의미한다.

### reference
- http://flowarc.tistory.com/entry/Java-객체-직렬화Serialization-와-역직렬화Deserialization
- http://lueseypid.tistory.com/42