### Thread1

#### Basic
- 쓰레드 구현 -> Thread 클래스 상속방법
```java
class ShowThread extends Thread{
	...
	public void run(){...}
}
```
- 쓰레드 구현 -> Runnable 인터페이스 상속방법 (다른 클래스를 상속받아야 할경우)
```java
class ShowThread extends Show implements Runnable{
	...
	public void run(){...}
}
```
- 쓰레드 인스턴스 생성후 start메소드를 호출하면, run메소드가 실행된다. 직접 run메소드를 호출할수 있지만, 그렇게하면 쓰레드의 생성이 되지않는다. JVM은 쓰레드 생성의 요구를 Start로 하기 때문
- run메소드의 실행이 완료되면, 해당 쓰레드는 종료되고 소멸된다. 또한, main 메소드의 쓰레드는 호출한 모든 쓰레드가 종료될때까지 실행된다.
- join메소드는 다른 쓰레드의 실행완료를 기다리는 목적으로 호출한다.
- 쓰레드는 힙영역이 공유되기 때문에 인스턴스의 주소값만 알면 다른 쓰레드가 접근 가능하다.
```java
public static void main(String[] args){
	Sum s = new Sum();
	TempThread a1 = new TempThread(s); //s공유
	TempThread a2 = new TempThread(s); //s공유
	a1.start();
	a2.start();
	try{
		a1.join();
		a2.join();
	}catch(InterruptException e){
		...
	}
}
```

#### 동기화(Synchronized)
- 자바의 인스턴스마다 하나의 동기화 키를 가진다.
- Synchronized 메소드 방법

> public synchronized void increment(int i){ i++; }

- Synchronized 블록 방법

> synchronized(this){ i++; }

```java
class Example{
	int num1=0;
	int num2=0;
	
	public void addOneNum1(){
		synchronized(key1){
			num1+=1;
		}
	}
	public void addTwoNum1(){
		synchronized(key1){
			num1+=2;
		}
	}
	public void addOneNum2(){
		synchronized(key2){
			num2+=1;
		}
	}
	public void addTwoNum2(){
		synchronized(key2){
			num2+=2;
		}
	}
	Object key1 = new Object(); // 동기화블록 키로사용
	Object key2 = enw Object(); // 동기화블록 키로 사용
	// 과도한 동기화를 줄일수 있다.
}
```
- wait() - 쓰레드를 대기시킨다. 이 메소드가 실행되면 동기화 블록에 대한 경계가 풀어져 다른 쓰레드가 동기화 블록에 접근 가능하게 된다.
- notify() - 하나의 쓰레드만 깨운다.
- notifyAll() - 모든 쓰레드를 깨운다.
- wait, notify, notifyAll 메소드는 동기화 처리하여, 한 순간에 하나의 쓰레드만 호출이 가능하도록 해야한다.
```java
public void setNews(String news){
	todayNews=news;
	isTodayNews=true;
	synchroized(this){
		notifyAll();
	}
}
public String getNews(){
	if(isTodayNews==false){
		try{
			synchronized(this){
				wait();
			}
		}catch(InterruptException e){
			e.printStackTrace();
		}
	}
	return todayNews;
}
```
#### 동기화(lock & unlock)
- lock & unlock 방법
```java
class Myclass{
	private final ReentrantLock obj = new ReentrantLock();
	void method(){
		obj.lock();
		try{
			...
		}finally{
			obj.unlock();
		}
	}
}
```
- ReentrantLock인스턴스를 대상으로 newCondition이라는 메소드를 호출하면 Condition형 인스턴스가 반환된다. 그리고 이를 통해 await, signal, signalAll을 호출할수있다.
```java
class Example{
	...
	private final ReentrantLock entLock= new ReentrantLock();
	private final Condition readCond = entLock.newCondition();
	private final Condition writeCond = entLock.newCondition();

	public void setNews(String news){
		entLock.lock();
		try{
			if(isNews==true)
				writeCond.await();
			newString = news;
			isNew=true;
			readCond.signal();	
		}finally{
			entLock.unlock():
		}
		
	}
}
```
