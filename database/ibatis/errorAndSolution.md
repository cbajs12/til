## Error and Solution

### 1) Error : There is no READABLE property named'컬럼명'in class '클래스명'
```xml
<sqlMap>
 <update id="update">
	update table set checkNull=#checkNull#
 </update>
<sqlMap>
```
```java
public String getPhoneNum(){} // xml의변수와 대소문자를 일치시켜야한다.
public String setPhoneNum(){} // xml의변수와 대소문자를 일치시켜야한다.
```

### 2) error code [17004]; Invalid column type;
- iBatis, myBatis 모두 Oracle을 사용할때는 nullable column을 사용하기 위한 처리를 해줘야 합니다. `jdbcType=<data type>`
- Mybatis : `insert into (emp_no, emp_name) values ( #{empNo, jdbcType=INTEGER}, #{empName, jdbcType=VARCHAR})`
- ibatis : `(#id:VARCHAR#, #name:VARCHAR#, #title:VARCHAR#) 
insert into (emp_no, emp_name) values ( #empNo:INTEGER#, #empName:VARCHAR#)`

#### Refernce
- http://gubok.tistory.com/399


