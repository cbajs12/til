# 가상 메모리
- 프로그램을 일부분만 메모리에 올려놓고 실행할수 있다면 프로그램은 물리 메모리 크기에 의해 제약받지 않게된다. 각 사용자 프로그램이 더 작은 메모리를 차지하므로 더 많은 프로그램을 동시에 실행할 수 있게 된다. 또한 프로그램을 메모리에 올리고 스왑하는데 필요한 입출력 횟수가 줄어들어 보다 빨리 실행된다.
- `가상 메모리`는 프로세스 전체가 메모리 내에 올라오지 않더라도 실행이 가능하도록 하는 기법이다. 이 기법의 주요 장점은 사용자 프로그램이 물리 메모리보다 커도 된다는 점이다.
- 가상 메모리는 물리 메모리로부터 사용자 관점의 논리 메모리를 분리시켜 메인 메모리를 균일한 크기의 저장 공간으로 구성된 엄청나게 큰 배열로 추상화시켜 준다.
- 가상 메모리는 파일의 공유를 쉽게 해주고 공유 메모리 구현을 가능하게 한다. 추가적으로 프로세스 생성을 효율적으로 처리할 수 있는 메커니즘도 제공한다.
- 그러나 가상 메모리는 구현하기 어렵고, 만약 잘못 사용하게 된다면 성능이 현저히 저하될 수 있다.
- 한 프로세스의 가상 주소 공간은 그 프로세스가 메모리에 저장되는 논리적인 모습을 말한다.
- 힙은 동적 할당 메모리를 사용함에 따라 주소 공간에서 위쪽으로 확장된다. 스택은 함수 호출을 거듭함에 따라 주소 공간에서 아래쪽으로 확장된다. 힘과 스택 사이의 공백도 가상 주소 공간의 일부이지만, 힙 또는 스택이 확장되어야만 실제 물리 페이지를 요구하게 될 것이다. 공백의 가상 주소 공간을 `성긴 주소 공간`이라고 한다.
- 성긴 주소 공간의 공백은 스택이나 힙 세그먼트가 확장될 때 사용되거나, 프로그램 실행중 동적으로 라이브러리를 링크할 필요가 있을 때 사용된다.
- 가상 메모리는 논리 메모리를 물리 메모리로부터 분리시켜주는 것 외에 페이지 공유를 통해 파일이나 메모리가 둘 또는 그 이상이 프로세스들에 의해 공유되는 것을 가능하게 한다. 이것은 여러 장점을 가진다.
- 첫 째로 시스템 라이브러리가 여러 프로세스들 사이에 공유될 수 있다. 각 프로세스들은 공유 라이브러리가 자신의 가상 주소 공간의 일부라고 생각하지만, 실제로는 라이브러리가 존재하는 물리 메모리 페이지들은 모든 프로세스에게 공유되고 있다. 일반적으로 라이브러리는 읽기만이 허용되는 상태로 프로세스 주소 공간에 매핑된다.
- 두번째로 가상 메모리는 프로세스들이 메모리를 공유하는 것을 가능하게 한다. 한 프로세스가 다른 프로세스와 공유할 수 있는 영역을 만들 수 있도록 해준다. 이 영역을 공유하고 있는 프로세스들에는 각자 자신의 주소 공간상에 있는 것처럼 보이지만 실제 물리 메모리는 공유되고 있다.
- 마지막으로 fork() 시스템 호출을 통한 프로세스 생성 과정에서 페이지들이 공유되는 것을 가능하게 하고, 이에 따라 프로세스 생성 속도를 빠르게 할 수 있다.

## 요구 페이징
- 요구 페이징을 사용하는 가상 메모리에서는 페이지들이 실행과정에서 실제로 필요해질 때 적재된다. 즉, 한 번도 접근되지 않는 페이지는 물리 메모리에 전혀 적재되지 않는다.
- 가상 메모리는 대게 요구 페이징 방식으로 구현되며 세그먼테이션 시스템도 몇이 있으나, 대개는 하나의 세그먼트가 다시 여러 페이지로 나뉘는 페이지된 세그먼테이션 기법을 사용한다.
- 요구 페이징 기법은 어떤 점에서는 스와핑 기법과 비슷하다. 프로세스는 디스크에 존재한다. 프로세스를 실행하고 싶으면 메모리로 읽어 들인다. 이때 전체 프로세스를 읽어오지 않고 `게으른 스왑퍼`를 사용한다. 게으른 스왑퍼는 그 페이지가 필요하지 않는 한 메모리에 적재하지 않는다.
- 스왑퍼는 전체 프로세스를 관리하는 반면, 페이저는 프로세스 내의 개별 페이지들을 관리한다.

### 기본 개념
- 페이저는 프로세스 전체를 스왑 인 하는 대신에 실제 필요한 페이지들만 메모리로 읽어 온다. 이렇게 하려면 어느 페이지가 디스크에 있고, 어느 페이지가 메모리에 올라와 있는지 구별할 수 있어야 한다. 따라서 하드웨어의 지원이 약간 필요하다.
- valid/invalid 비트 기법을 사용하여 이 비트가 유효하다고 설정되면 해당 페이지가 메모리에 있다는 의미이고, 무효하다고 설정되면 해당 페이지가 유효하지 않거나, 유효하지만 디스크에 존재한다는 것을 의미한다.
- 메모리에 올라오는 페이지에 대해서는 유효로 설정, 현재 메모리에 올라오지 않은 페이지의 페이지 테이블 항목은 무효로 설정하거나, 그 페이지가 저장되어있는 디스크 주소를 기록해둔다.
- 프로세스가 메모리에 올라와 있지 않는 페이지에 접근하려고 하면 `페이지 부재 트랩`을 발생시킨다. 페이징 하드웨어는 페이지 테이블을 이용한 주소 변환 과정에서 무표 비트를 발견하고 운영체제에게 트랩을 건다.
- 만약 페이지 부재 트랩이 발생하면, 프로세에 대한 내부 테이블(PCB와 함께 유지)을 검사해서 그 메모리 참조가 유효/무효인지를 알아낸다.  만약 무효한 페이지에 대한 참조라면 그 프로세스는 중단된다. 만약 유효한 참조인데 페이지가 아직 메모리에 올라오지 않았다면, 그것을 디스크로부터 가져와야 한다. 그 다음 자유 프레임(빈 공간)을 찾아서 디스크에서 가져올 페이지를 읽어서 자유 프레임에 내용을 적는다. 디스크 읽기가 끝나면 이 페이지가 메모리에 있다는 것을 알리기 위해 페이지 테이블을 갱신하며, 프로세스가 유지하고 있는 내부 테이블을 수정한다. 트랩에 의해 중단되었던 명령어를 다시 실행하고 프로세스는 다시 작업한다.
- `순수 요구 페이징`이란 어떤 페이지가 필요해지기 전에는 결코 그 페이지를 메모리로 적재하지 않는 방법이다. 
- 요구 페이징은 `참조 지역성`이라는 성질이 있어서 프로그램의 어느 한 특정 작은 부분만 한동안 집중적으로 참조한다. 이러한 성질 때문에 요구 페이징은 만족할 만한 성능을 보인다.
- 요구 페이징을 지원하기 위해 페이지 테이블은 유효/무효 비트 또는 보호 비트의 특수한 값을 이용하여 항목을 무효로 설정할 수 있어야하고 디스크는 주 메모리에 없는 모든 페이지들을 가지고 있는 스왑 공간을 가지고 있어야 한다.
- 요구 페이징을 위한 필수적인 요구사항은 페이지 부재 오류 처리 후에 명령어를 다시 시작할 수 있어야 한다. 페이지 부재가 발생하여 중단된 프로세스 상태를 보관해 두면, 다시 프로세스가 시작할때 중단된 곳부터 다시 시작할수 있어야 한다는 것이다. 대부분의 경우 이 요구사항은 쉽게 만족된다.
- 한 명령어가 많은 기억 장소를 변경하는 경우 상당히 어려운 문제가 발생한다. 문자 이동 명령어의 경우, 어느 블록이라도 페이지 경계에 걸쳐 있으면 이동이 다 끝나지도 않은 상태에서 페이지 부재가 발생할 수 있다. 더구나 소스 블록과 목적 블록이 서로 겹쳐져 있는 경우에는 소스 블록의 내용이 이미 변경되었을 수 있기 때문에 단순히 명령어를 다시 실행하는 것만으로 문제가 해결되지 않는다.
- 이러한 문제는 2가지 방법으로 해결할 수 있다. 하나는 마이크로코드로 두 블록의 양 끝을 계산하여 접근을 시도하는 것이다. 만약 페이지 부재가 발생될 가능성이 있다면, 어떤 블록도 수정되기 전인 단계에서 페이지 부재를 발생시키고, 이동을 시작하면 페이지 부재를 발생시키지 않는다. 또 하나의 방법은 이동에 의해서 덮어 쓰일 위치의 값을 보전하기 위해 임시 레지스터들을 사용하는 것이다. 만약 페이지 부재가 발생하면, 트랩이 발생되기 전의 예전 값들이 메모리에 쓰여진다.

### 요구 페이징의 성능
- 요구 페이징은 시스템의 성능에 중요한 영향을 미친다. 페이지 부재가 발생하지 않는 한 유효 접근 시간은 메모리 접근 시간과 같다. 그러나 페이지 부재가 발생하면 디스크로부터 관련되는 페이지를 읽어온 뒤 필요한 워드를 접근해야 한다. `유효 접근 시간 = (1 - p) * m *a + p * 페이지 부재시간`, p는 페이지 부재가 발생할 확률이다.
- 페이지 부재 처리 시간의 3개의 큰 구성요소: 페이지 부재 인터럽트의 처리, 페이지 읽어 들이기, 프로세스 재시작
- 유효 접근 시간은 페이지 부재율에 비례한다. 따라서 성능을 위해서는 페이지 부재율을 낮게 유지하는 것이 상당히 중요하다.
- 스왑 공간에서의 디스크 입출력은 일반적으로 파일 시스템에서의 입출력보다 빠르다. 그 이유는 스왑 공간은 파일 시스템보다 더 큰 블록을 사용하고, 스왑 공간과 입출력을 할 때는 파일 탐색이나 간접 할당 방법등은 사용하지 않기 때문이다.
- 시스템은 프로세스를 시작할때 그 파일 이미지 전체를 스왑 공간으로 복사한 후 스왑공간으로부터 요구 페이징을 처리함으로써 보다 나은 페이징 처리율을 얻을 수 있다.
- 또 다른 방법은 프로그램을 처음 시작시킬 때에는 파일 시스템으로부터 페이지를 요구하지만 페이지들이 교체될 때는 스왑 공간에 페이지를 기록하는 것이다. 이 방식은 파일 시스템으로부터는 꼭 필요한 페이지들만 읽어 오면서, 이후의 페이징은 스왑 공간에서 일어난다는 것을 보장한다.
- 어떤 시스템들은 바이너리 파일을 스왑 공간에 넣지 않음으로써 스왑 공간의 크기를 줄이기도 한다. 바이너리 파일의 페이지를 요청하면 파일 시스템으로부터 그 페이지를 직접 가져온다. 그러나 이 페이지들의 교체가 필요하면 이들 프레임에 새 페이지의 내용을 그대로 덮어쓸 수 있다. 왜나하면 이들 프레임들은 수정되지 않기 때문이다.

#### 페이지 부재 처리 순서
- OS에 트랩을 요청
- 사용자 레지스터들과 프로세스 상태를 저장한다.
- 현재의 인터럽트가 페이지 부재임을 알아낸다.
- 페이지 참조가 유효한 것인지 확인하고, 디스크에 있는 페이지의 위치를 알아낸다.
- 빈 페이지 프레임을 찾는다. 빈 프레임이 있다면 그것을 사용하고 없다면 희생될 프레임을 선정하기 위해 페이지 교체 알고리즘을 가동한다. 희생 프레임을 빈 프레임으로 만든다.
- 디스크로부터 자유(빈) 프레임으로 읽기 요청을 한다. 읽기 요청이 서비스될 때까지 이 장치의 대기 큐에서 기다린다. 디스크에서 seek 시간 및/혹은 회전 지연시간 동안 기다린다. 메모리 내 지정된 프레임으로 데이터 전송을 시작한다.
- 기다리는 동안 CPU는 다른 사용자에게 할당된다,
- 디스크 입출력 시스템으로부터 인터럽트를 받는다.(I/O 완료)
- 다른 사용자의 레지스터들과 프로세스 상태를 저장한다.
- 인터럽트가 디스크로부터 왔다는 것을 알아낸다.
- 원하는 페이지가 메모리에 있다는 것을 표시하기 위하여 페이지 테이블과 다른 테이블들을 수정한다.
- CPU가 다시 이 프로세스에게 할당될 때까지 프로세스는 기다린다.
- CPU가 프로세스를 처리할 차례가 오면 위에서 저장시켜 두었던 레지스터들, 프로세스 상태, 새로운 페이지 테이블을 복원시키고 인터럽트 되었던 명령어를 다시 실행한다.

## Copy-on-write
- fork()를 하면 부모 프로세스의 페이지들을 실제로 자식 프로세스에 복사해 줌으로써 자식 프로세스의 주소 공간을 구성해 주었다. 그러나 대부분의 자식들은 exec()를 사용하는 경우가 많다. 그러면 부모로부터 페이지들을 복사할 필요가 없다. 그래서 부모의 페이지들을 복사해오는 대신 Copy-on-write 방식을 사용할 수 있다.
- Copy-on-write에서는 자식 프로세스가 시작할 때 부모의 페이지를 당분간 함께 사용하도록 한다. 공유되는 페이지를 `Copy-on-write 페이지`라고 한다. 
- 둘 중의 한 프로세스가 공유 중인 Copy-on-write 페이지를 변경하면 그 페이지의 복사본이 만들어진다. 즉, 프로세스가 수정하는 페이지들에 대해서만 복사본이 생기게 된다. 수정되지 않는 페이지들은 계속 공유가 될 수 있다. 또한, 수정될 수 있는 페이지만이 Copy-on-write로 표시할 필요가 있다. 수정될 수 없는 페이지는 자식과 부모간에 그대로 공유될 수 있다.
- Copy-on-write 처리 과정에서 페이지 복사본을 만들때 빈 페이지를 할당하기 위하여 OS는 빈 페이지 풀을 유지하고 있다. 빈 페이지가 프로세스에게 할당되는 경우는 Copy-on-write할때, 그리고 스택이나 힙 공간을 확장 할 때이다. OS는 보통 요청시 0으로 채우기 기법으로 페이지를 할당한다. 페이지를 할당할 때 그 내용을 전부 0으로 채워 이전 내용을 지우게 된다.
- Linux는 vfork()라는 시스템 호출을 제공한다. vfork()는 Copy-on-write 측면에서 fork()와 다르게 동작한다. vfork()를 하면 부모 프로세스는 보류되고 자식이 부모의 주소 공간을 사용하게 된다. vfork()는 Copy-on-write를 사용하지 않으므로 자식이 부모 주소 공간의 페이지를 수정하게 되면 변경된 페이지가 재실행 시 부모 프로세스에 그대로 보여진다. 따라서 vfork()를 사용할 때에는 자식이 부모 주소 공간의 페이지들에 변경을 가하지 않도록 해야한다. vfork()는 자식이 만들어지자마자 exec()를 하는 경우를 위해 마련된 것이다. 페이지가 전혀 복사되지 않으므로 효율적이다.

## 페이지 교체
- 다중프로그래밍 정도를 올리면, 메모리 과할당이 발생한다. OS는 사용자 프로세스가 실행 중에 페이지 부재가 발생하여, 페이지를 교체하려고 할때 빈 프레임이 없이 모든 메모리가 사용 중임을 발견하게 되었다면, 과할당 되었다는 것을 파악하게 된다.
- OS는 사용자 프로세스를 종료하거나 프로세스 하나를 스왑 아웃하여 그 프로세스의 프레임들을 해제하고 다중프로그래밍 정도를 낮출 수 있다.

### 기본적인 페이지 교체
- 만약 빈 프레임이 없다면 현재 사용되고 있지 않는 프레임을 찾아서 그것을 비운다. 그 프레임의 내용을 스왑 공간에 쓰고 페이지 테이블을 수정하여 프레임을 빈 상태로 만든다. 
- 빈 프레임이 없는 경우에는 디스크를 프레임을 비울 때와 읽어 들일 때로 총 두번 접근해야 한다. 그러므로써, 페이지 부재 처리시간이 2배 소요된다.
- 이러한 오버헤드는 변경 비트를 사용해서 감소시킬 수 있다. 각 페이지나 프레임은 자신과 관련된 변경 비트를 하드웨어에 갖게 된다. 변경 비트는 CPU가 페이지 내의 어떤 워드나 바이트라도 쓰게 되면 페이지가 변경되었음을 나타내기 위해 설정된다. 희생시킬 페이지가 선정되면 변경 비트를 확인한다. 변경 비트가 설정되어 있으면 페이지 내용이 디스크 상의 원래 페이지 내용과 달라졌다는 것을 알 수 있다. 이 경우 현재 내용을 디스크에 기록해야 한다. 그러나 변경 비트가 설정되어 있지 않다면 메모리로 읽혀 들어온후 변경되지 않았음을 알 수 있다.
- 디스크 상의 복사본이 변경되지만 않았다면 메모리 페이지를 디스크에 기록할 필요가 없다. 이미 디스크에 있기 때문이다. 이 기법은 읽기 전용 페이지들에도 적용된다. 이러한 페이지들은 변경될 수 없으므로 필요할 때 언제든 제거될 수 있다. 이러한 방법은 페이지가 변경되지 않은 경우 I/O 시간을 반으로 줄일 수 있으므로 페이지 부재 처리 시간을 많이 줄일 수 있다.
- 요구 페이징 시스템은 프레임 할당 알고리즘과 페이지 교체 알고리즘을 잘 활용하여야 한다. 즉, 여러 프로세스가 존재하는 경우 각 프로세스에 얼마나 많은 프레임을 할당해야 할지 결정해야 한다. 또한 페이지 교체가 필요할 때마다 어떤 페이지를 교체해야 할지 결정해야 한다.
- 페이지 교체 알고리즘의 경우 일반적으로 페이지 부재율이 낮은 것을 선정한다. 이 알고리즘의 성능은 특정 메모리 참조 나열에 대해 알고리즘을 적용하여 페이지 부재 발생 회수를 계산하여 평가한다. 이러한 메모리 주소의 나열을 참조열이라고 부른다. 참조열은 인공적으로 생성할 수도 있고, 주어진 시스템을 추적하여 매 메모리 참조 주소를 기록할 수도 있다. 

### FIFO 페이지 교체
- 이 알고리즘은 각 페이지마다 메모리에 적재된 시간을 기억한다. 어떤 페이지를 교체해야 할 때 메모리에 올라온 시간이 가장 오래된 페이지를 내쫓는다.
- 반드시 페이지가 올라온 시간을 기록할 필요는 없다. 페이지들이 올라온 순서로 큐를 만들어 유지하고 있어도 된다. 큐의 머리 부분 페이지를 교체하고 새로 올라온 페이지는 큐의 끝에 삽입한다.
- FIFO 페이지 교체 알고리즘은 간단하지만 성능이 항성 좋은 것은 아니다. 왜냐하면 교체된 페이지가 오래 전 사용된 뒤 더이상 필요하지 않은 초기화 모듈일 수도 혹은 교체된 페이지가 초기화 된뒤 계속해서 매우 많이 사용되는 변수일수도 있기 때문이다.
- Belady의 모순은 프로세스에게 프레임을 더 주었는데도 오히려 페이지 부재율은 더 증가하는 현상을 일컫는다. 보통은 프로세스에 더 많은 프레임을 할당하면 성능이 좋아질 것이라는 생각을 하지만 그렇지 않은 것이다. 이것은 FIFO 알고리즘의 문제점중 하나이다.

### 최적 페이지 교체(OPT)
- 이 알고리즘은 모든 알고리즘보다 낮은 페이지 부재율을 보이며 Belady의 모순이 발생하지 않는다.
- 핵심은 앞으로 가장 오랫동안 사용되지 않을 페이지를 찾아 교체하라는 것이다.
- 이 알고리즘은 할당된 프레임 수가 고정된 경우 가장 낮은 페이지 부재율을 보장한다.
- 이 알고리즘의 구현은 어렵다. 왜냐하면 프로세스가 앞으로 메모리를 어떻게 참조할 것인지를 미리 알아야하기 때문이다. 따라서 주로 비교 연구 목적을 위해 사용된다.

### LRU 페이지 교체
- 최근의 과거를 가까운 미래의 근사치로 본다면 가장 오랜 기간 동안 사용되지 않은 페이지를 교체할 수 있다. 이것이 LRU 알고리즘의 핵심이다.
- 이 알고리즘은 각 페이지마다 마지막 사용 시간을 유지한다. 페이지 교체 시에 가장 오랫동안 사용되지 않은 페이지를 선택한다.
- 이 알고리즘은 하드웨어의 지원이 필요하다.
- 카운터: 가장 간단한 방법으로써, 각 페이지 항목마다 사용 시간 필드를 넣고 CPU에 논리적인 시계나 카운터를 추가한다. 매 메모리 접근마다 시간은 증가한다. 페이지에 대한 참조가 일어날 때마다 페이지의 사용 시간 필드에 시간 레지스터의 내용이 복사된다. 시간 값이 가장 작은 페이지가 교체된다. 이 기법에서는 LRU 페이지를 찾기 위해 페이지 테이블을 탐색해야 하며, 매 메모리 참조마다 메모리 쓰기 작업을 필요로 한다. 페이지 테이블이 변경될 때 마다 시간 값을 관리하고 시간 값의 오버플로우도 고려해야 한다.
- 스택: 페이지 번호의 스택을 유지하는 방법이다. 페이지가 참조될 때마다 페이지 번호는 스택 중간에서 제거 되어 스택 top에 놓이게 된다. 이런 방식으로 하면 스택의 top은 항상 최근에 사용된 페이지이고, 바닥은 가장 오랫동안 이용되지 않은 페이지이다. 여기서 사용되는 스택은 링크드리스트로 구현하여 중간에 값을 제거하도록 지원한다. 어떤 페이지를 참조해서 그것을 스택의 중간에서 꼭대기로 이동하는 것은 최악의 경우 6개의 포인터 값을 바꿔야 한다. 매 갱신 시에는 오버헤드가 있지만 페이지를 탐색할 필요는 없게된다.
- LRU 교체는 Belady의 모순 현상을 야기하지 않는다. 페이지 교체 알고리즘들 중에는 Belady의 모순 현상을 나타내지 않는 것들이 있다. 이러한 알고리즘을 `스택 알고리즘`이라고 한다.
- 스택 알고리즘은 n개의 프레임에 적재되는 페이지의 집합이 항상 n+1개의 프레임에 적재되는 페이지 집합의 부분집합이 되는 알고리즘이다. LRU 교체의 경우 메모리 상의 페이지들은 n개의 가장 최근에 접근한 페이지이다. 프레임 수가 증가하더라도 이러한 n페이지들은 여전히 가장 최근에 접근한 것들이므로 메모리에 남아있게 된다.
- 계수기, 스택 방법 모두 반드시 표준적인 TLB 레지스터 이상의 하드웨어 지원이 있어야 한다. 계수기 값과 스택을 갱신하는 작업을 메모리 참조 때마다 실행해야 하기 때문이다.

### LRU 근사 페이지 교체
- 하드웨어의 지원없이 LRU를 구현하기 위한 알고리즘이다. 기존의 시스템이 지원하고 있는 참조 비트를 이용하는 것이다.
- 페이지 참조가 있을 때마다 하드웨어가 그 페이지에 대한 참조 비트를 설정한다. 참조 비트는 페이지 테이블에 있는 각 항목마다 연관된다.
- 처음에 모든 참조 비트는 OS에 의해 0으로 채워진다. 프로세스가 실행되면서 참조되는 페잊의 비트는 하드웨어가 1로 바꾼다. 얼마 후에 페이지 사용의 순서는 모르지만 어떤 페이지가 사용되었고, 어떤 페이지가 한번도 사용되지 않았는지를 알수 있다.

#### 부가적 참조 비트 알고리즘
- 일정한 주기로 참조 비트들을 기록함으로써 추가적인 선후 관계 정보를 알수 있다.
- 각 페이지에 대해 8비트를 할당한다. 일정시간 마다 타이머 인터럽트를 걸어서 제어를 OS에게 넘긴다.
- OS는 참조 비트를 8비트 정의 최상위 비트로 이동시키고 나머지 비트들은 하나씩 오른쪽으로 이동시키고 제일 하위 비트를 버린다. 이 8비트 시프트 레지스터는 가장 최근 8구간 동안의 페이지의 사용 기록을 담고 있다. 이 8비트의 이진 값이 클수록 최근에 사용된 것이다.
- 만약 동일한 비트들이 존재한다면 FIFO 방식으로 하나를 선택하거나 동일한 비트들 모두를 교체할 수도 있다.

#### 2차 기회 알고리즘
- 기본은 FIFO 알고리즘이다. 그러나 페이지가 선택될 때마다 참조 비트를 확인한다. 참조 비트가 0이면 페이지를 교체하고, 1이면 다시 한 번 기회를 주고 다음 FIFO 페이지를 선택하기 위해 이동한다. 한 번 기회를 받게 되면 참조 비트는 해제되고 도착 시간이 현재 시간으로 재설정된다. 이에 따라 2차 기회가 주어진 페이지는 다른 모든 페이지들이 교체될 때까지 교체되지 않는다. 게다가 참조 비트가 계속 설정되어 잇을 정도로 자주 사용되는 페이지는 절대로 교체되지 않을 것이다.
- 이 알고리즘의 구현의 한 방법은 순환 큐를 사용하는 것이다. 이 큐에는 포인터가 있어서 다음에 교체될 페이지를 가리킨다. 프레임이 필요해지면 포인터는 참조 비트가 0인 페이지를 발견할 때 까지 포인터를 전진 시킨다. 포인터가 전진하면서 참조 비트를 0으로 바꾼다. 희생될 페이지가 발견되면, 그 페이지는 교체되고 새로운 펭지가 순환 큐의 해당 위치에 삽입된다. 최악의 경우 모든 비트가 1이라면 포인터는 큐를 완전히 한 바퀴 순환하면서 참조 비트를 0으로 만들어 모든 페이지에게 2차 기회를 준다. 모든 비트가 1일 때에는 2차 기회 알고리즘은 FIFO 교체로 바뀐다.

#### 개선된 2차 기회 알고리즘
- 2차 기회 알고리즘은 참조 비트와 변경 비트를 순서쌍으로 생각하여 응용하면 더 개선시킬 수 있다. 두 개의 비트를 조합하여 사용하면 4가지 등급이 가능하다.
- (0, 0) : 최근에 사용되지도 변경되지도 않은 경우 - 교체하기 좋은 페이지
- (0, 1) : 최근에 사용되지는 않았지만 변경은 된 경우 - 이 페이지는 뺏어 오려면 디스크에 내용을 기록해야 하기 때문에 교체에 적당하지는 않다.
- (1, 0) : 최근에 사용은 되었으나 변경은 되지 않은 경우 - 이 페이지는 곧 다시 사용될 가능성이 높음
- (1, 1) : 최근에 사용되었고 변경도 된경우 - 곧 다시 사용될 것이며, 교체시키려면 역시 디스크에 그 내용을 먼저 기록해야 함
- 각 페이지는 4등급 중에 하나에 속하며, 페이지 교체가 필요할 때 클록 알고리즘과 같은 방법을 사용하되 페이지가 어떤 등급에 속하는지 확인한다.
- 교체될 페이지를 찾기까지 여러 번 순환 큐를 검사할 수도 있다

### 계수 기반 페이지 교체
- LFU 알고리즘: 참조 회수가 가장 작은 페이지를 교체하는 방법이다. 활발하게 사용되는 페이지는 참조 횟수가 많아지게 될것이라 가정하에 사용된다. 이 알고리즘은 어떤 프로세스가 초기 단계에서는 한 페이지를 집중적으로 많이 사용하지만 그 후로 다시는 이 페이지를 사용하지 않는 경우에는 판단이 빗나갈 수 있다. 이러한 해결책은 참조 회수를 일정한 시간마다 하나씩 오른쪽으로 시프트해서 지수적으로 그 영향력을 감소시키는 방법이다.
- MFU 알고리즘: 가장 작은 참조 횟수를 가진 페이지가 가장 최근에 메모리로 적재되었고, 앞으로 계속 사용될 것이라는 판단에 근거한 것이다.
- 이 두 알고리즘은 일반적으로 잘 쓰이지 않는다.

### 페이지 버퍼링 알고리즘
- 페이지 부재가 발생하면 예전과 마찬가지로 교체될 프레임을 찾지만, 교체될 프레임의 내용을 디스크에 기록하기 전에 가용 프레임 풀에서 가져온 가용 프레임에 새로운 페이지를 먼저 읽어 들이는 방법이다.
- 이 방법은 교체될 프레임이 쓰이기를 기다리지 않고 프로세스가 가능한 빨리 시작할 수 있도록 한다. 이후에 교체될 프레임이 다 쓰이고 나면 그 프레임이 가용 프레임 풀에 추가된다.
- 이 개념의 확장으로 변경된 페이지 리스트를 유지하는 방법이 있다. 페이징 장치가 유휴 상태일 때마다 변경된 페이지들을 선택하여 디스크에 쓴 후에, 페이지의 변경 비트를 0으로 되돌려 놓는다. 이 기법은 나중에 페이지가 실제로 교체될 때 변경되지 않은 상태여서 쓰지 작업이 필요 없을 가능성을 높인다.
- 다른 변형은 가용 프레임 풀을 유지하지만 각 프레임에 어느 페이지가 있었던지를 기억한다. 프레임 내용은 디스크 장치에 쓰이더라도 수정되지 않기 때문에 프레임이 재사용되기 전에 그 프레임이 필요해지면, 가용 프레임 풀에서 직접 옛 페이지를 제공할 수 있다. 이 경우 I/O는 전혀 필요하지 않다. 페이지 부재가 발생하면 찾는 페이지가 아직도 풀에 남아 있는지 검사한 후, 만약 없으면 그 때 새로운 프레임을 선택하여 페이지를 읽어 들인다.
- 이 기법은 어느 페이지 교체 알고리즘을 보강하는데 유용하게 사용될 수 있다. 잘못된 희생 페이지가 선택되었을 때 발생하는 불이익을 줄여주기 때문이다.
