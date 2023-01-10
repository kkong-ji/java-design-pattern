# java-design-pattern
[메타코딩] 디자인패턴 강의

<br>

## 1. SOLID 원칙
### `SRP` : 단일 책임 원칙
- object는 class 설계도 상 하나의 책임만을 가진다.
- 그러나, 무조건적인 것은 아니고 권장사항이다.
- ex. `Add class` 가 있을 때, 이 클래스는 더하기 기능만을 수행해야한다. 곱하기 기능, 나누기 기능 x

### `OCP` : 개방 폐쇄 원칙
- 개방(확장)에는 열려있고, 수정(변경)에는 닫혀있다.
- 요구사항의 변경이나 추가사항이 발생하더라도 기존 구성 요소는 수정이 일어나면 안된다.
- 확장, 재사용
- OCP를 위해선 추상화와 다형성이 중요.

### `DIP` : 의존성 역전 원칙
- 구체적인 것에 의존하지 않고 추상적인 것에 의존한다. 

### `LSP` : 리스코브 치환의 원칙
- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야한다. <br>
ex. 자동차 인터페이스에서의 엑셀은 앞으로 가는 기능. 뒤로 가게 되면 LSP위반이 된다.

### `ISP` : 인터페이스 분리 원칙
- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.

### `DIP` : 의존성 역전 원칙
- 구체적인 것에 의존하지 말고 추상적인 것에 의존한다.

<br>

## 2. 전략 패턴

>  같은 문제를 해결하는 여러 알고리즘이 클래스별로 캡슐화되어 있고 이들이 필요할 때 <br>
교체할 수 있도록 함으로써 동일한 문제를 다른 알고리즘으로 해결할 수 있게 하는 디자인 패턴 <br>

<br>

![제목 없음](https://user-images.githubusercontent.com/87354210/210686244-5a119c81-01af-4479-baf4-80b352a352a2.png)

<처음 문지기의 임무>
- 쥐를 쫓아내
- 호랑이를 쫓아내

처음은 쉽다. but, 소, 말, 다람쥐 등등이 추가될 때마다 일일히 코드를 수정해야할까? <br>
타겟을 구체적인 것에 의존하지 않고 추상화해야함.
따라서, "동물을 쫓아내"로 바꾼다.


![image](https://user-images.githubusercontent.com/87354210/210700986-0ff8c62e-394a-479a-8aae-6f1e3d62c833.png)

(경로 계획 전략들)


- 전략 패턴을 사용함으로써 해결되는 문제점
  - 기존 코드의 내용을 수정하여 위배되는 OCP
  - 시스템 확장 시 메서드 중복 문제 

<br>

## 3. 프록시 패턴

- 프록시(Proxy) : 대리자, 대변인

- 어떤 객체를 사용하고자 할 때, 객체를 직접 참조하는 것이 아닌 해당 객체와 <br>
대응하는 객체를 통해 접근하는 것.

- 메소드를 직접 호출하지 않고 왜 Proxy를 사용하는 것일까?
  - 결국은 "흐름제어"
  - 데이터 사이즈가 큰 이미지 같은 경우, 로드될 때, 시간이 걸리게 됨. <br>
    따라서 제어흐름을 통해 데이터가 로딩될 때까지 현재까지 완료된 것을 <br>
    우선적으로 보여주는 것.
  - 객체의 은닉화. 
  
<br>

## 4. 어댑터 패턴

<정의>
- 호환성이 없는 기존 클래스의 인터페이스를 변환 -> 사용자가 기대하는 인터페이스 형태로 변환
- 코드의 재활용성을 증가하고 기존의 코드를 수정하지 않는다는 장점. (OCP 원칙을 지키게됨)

<사용시점>
- 외부 요소를 기존 시스템에 재사용하고 싶지만 아직 만들어지지 않은 경우
- 외부 요소(애플리케이션)가 기대하는 인터페이스와 호환되지 않는 경우

<예제>
### 1. 외부요소 혹은 예전에 만들어진 레거시 코드

`OuterTiger.java`
```java
public class OuterTiger {
    private String fullName = "호랑이";

    public String getFullName() {
        return fullName;
    }
}
```

<br>

### 2. App에서의 동작
`App.java`
```java
public class App {
    public static void main(String[] args) {
        Mouse m = new Mouse();
        Cat c = new Cat();
        OuterTiger ot = new OuterTiger();
        DoorManProxy dm = new DoorManProxy(new DoorMan());
        dm.쫓아내(m);
        dm.쫓아내(c);
        dm.쫓아내(ot);
    }
}
```

> 위와 같이 설계하면 `OuterTiger` 객체에서 오류가 발생한다.
> Why? `OuterTiger` 는 Animal 타입이 아니기 때문에

그렇다면 어떻게 수정하면 좋을까?

<br>

### 3. `OuterTiger` 의 수정?

`OuterTiger.java`
```java
public class OuterTiger extends Animal {
    private String fullName = "호랑이";

    public String getFullName() {
        return fullName;
    }
    
    @Override
    public String getName() {
      return null;
    }
}
```

> 위 코드의 문제점은 뭘까?
> OCP 원칙을 위배. (확장에는 열려있고, 수정에는 닫혀있다.)

<br>

### 4. `TigerAdapter` 생성
```java
public class TigerAdapter extends Animal{
    
    private OuterTiger outerTiger;

    public TigerAdapter(OuterTiger outerTiger) {
        this.outerTiger = outerTiger;
    }

    @Override
    public String getName() {
        return outerTiger.getFullName();
    }
}
```
> `Adapter` 를 생성함으로써 Animal 객체를 상속받을수 있게 되었다. (재사용)  
> 또한, 기존 시스템을 건드리지 않고 해결하게 되었다.

<br>

### 5. App에서의 동작
`App.java`
```java
public class App {
    public static void main(String[] args) {
        Mouse m = new Mouse();
        Cat c = new Cat();
        TigerAdapter ot = new TigerAdapter(new OuterTiger());
        // 어댑터를 
        DoorManProxy dm = new DoorManProxy(new DoorMan());
        dm.쫓아내(m);
        dm.쫓아내(c);
        dm.쫓아내(ot);
    }
}

```

<br>

## 5. 싱글톤 패턴

- 어플리케이션이 시작될 때 어떠한 클래스가 최초 한 번만 메모리를 할당하고 메모리에 인스턴스를  
만들어 사용하는 패턴

### 1. 적용 예시
`DoorMan.java`
```java
public class DoorMan {

    // 1. static 영역에 객체를 1개 생성
    // (자바에서 static은 main 메소드를 호출하기 전에 JVM이 먼저 읽어서 메모리에 올라옴.)
    private static DoorMan doorMan = new DoorMan();
    
    // 2. public으로 오픈하여 객체 인스턴스가 필요하면 이 static 메소드를 통해서만 조회하도록 허용.
    public static DoorMan getInstance() {
        return doorMan;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막음.
    private DoorMan() {

    }
    
    // 로직 호출
    public void 쫓아내(Animal a) {
        System.out.println(a.getName() + "쫓아내");
    }
}
```

### 2. 싱글톤 패턴의 장점
- 고객의 요청이 올 때마다 객체를 생성하는 것이 아닌 만들어진 객체를 공유하는 것이기 때문에   
메모리를 효율적으로 사용가능
- 싱글톤으로 만들어진 클래스의 인스턴스는 전역이기 때문에 다른 클래스의 인스턴스들이  
데이터를 공유하기 쉬움

<br>

### 3. 싱글톤 패턴의 단점
- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어감
- 의존관계상 클라이언트가 구체 클래스에 의존 -> DIP 위반
- 싱글톤 인스턴스가 너무 많은 일을 하거나 많은 데이터를 공유시킬 경우,  
다른 클래스의 인스턴스들과의 결합도가 높아져 OCP를 위반할 수 있음.
- 내부 속성을 변경하거나 초기화 하기 어려움.
- 결론적으로 유연성이 떨어짐.
