# 요구사항 정리

- 한 주에서 월,화,수,목,금요일의 점심 메뉴를 추천해주는 애플리케이션
- 메뉴 추천 서비스에서 추천할 수 있는 카테고리와 각 카테고리의 메뉴는 아래와 같다.

```
일식: 규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼
한식: 김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음
중식: 깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채
아시안: 팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜
양식: 라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니
```

## Flow

1. 메뉴 추천을 받을 코치들의 이름과, 각 코치가 먹지 못하는 메뉴를 입력한다.
    - 코치의 이름은 2~4글자, 코치의 수는 2~5명
    - 먹지 못하는 메뉴는 0~2개, 0개인 경우 빈 값 입력
2. 메뉴를 추천한다.
    1. 각 요일에 추천할 카테고리 무작위로 선택 (요일은 월요일 ~ 금요일이며, 한 주에 같은 카테고리는 최대 2번까지만 고를 수 있음)
    2. 각 코치가 월요일에 먹을 메뉴를 추천
3. 메뉴 추천 결과를 출력한다.

```
- 예시
메뉴 추천 결과입니다.
[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]
[ 카테고리 | 한식 | 한식 | 일식 | 중식 | 아시안 ]
[ 토미 | 쌈밥 | 김치찌개 | 미소시루 | 짜장면 | 팟타이 ]
[ 제임스 | 된장찌개 | 비빔밥 | 가츠동 | 토마토 달걀볶음 | 파인애플 볶음밥 ]
[ 포코 | 된장찌개 | 불고기 | 하이라이스 | 탕수육 | 나시고렝 ]

추천을 완료했습니다.
```

- 사용자가 잘못된 값을 입력한 경우 IllegalArgumentException 발생 -> "[ERROR]" 로 시작하는 에러메시지 출력 -> 재입력
    - 예외는 구체화된 예외로서, 명시적으로 처리한다.

## 카테고리, 메뉴 추천 방식

1. 카테고리

- 추천할 카테고리는 camp.nextstep.edu.missionutils에서 제공하는 Randoms.pickNumberInRange()에서 생성해 준 값을 이용하여 정해야 한다.

```
String category = categories.get(Randoms.pickNumberInRange(1, 5));
```

- 이 때 임의로 카테고리의 순서 또는 데이터를 변경하면 안 된다.
- Randoms.pickNumberInRange()의 결과가 1이면 일식, 2면 한식, 3이면 중식, 4면 아시안, 5면 양식을 추천해야 한다.
- 추천할 수 없는 카테고리인 경우 다시 Randoms.pickNumberInRange()를 통해 임의의 값을 생성해서 추천할 카테고리를 정해야 한다.
- 숫자를 5개 뽑는데 뽑은 숫자가 3개 이상 나올 수 없도록 해야함.

2. 메뉴

- 추천할 메뉴는 정해진 카테고리에 있는 메뉴를 camp.nextstep.edu.missionutils에서 제공하는 Randoms.shuffle()을 통해 임의의 순서로 섞은 후, 첫 번째 값을 사용해야 한다.
- 카테고리에 포함되는 메뉴 목록을 List<String> 형태로 준비한다.

```
String menu = Randoms.shuffle(menus).get(0);
```

- 이 때 임의로 메뉴의 순서 또는 데이터를 변경하면 안 된다.
- Randoms.shuffle() 메서드의 인자로 전달되는 메뉴 데이터는, 최초에 제공한 목록을 그대로 전달해야 한다.
- <b>코치에게 추천할 메뉴를 정할 때 이미 추천한 메뉴, 먹지 못하는 메뉴도 포함된 리스트를 전달해야 한다.</b>
- 추천할 수 없는 메뉴인 경우 다시 섞은 후 첫 번째 값을 사용해야 한다.

# 구현해야 할 기능

1. 입력

- 코치들의 이름
    - 코치는 2~5명
    - 이름 길이 2~4
    - 중복 X
    - ","으로 구분
- 각 코치가 먹지 못하는 메뉴
    - 메뉴는 0~2개
    - 메뉴판에 없는 메뉴 X
    - ","으로 구분

- 입력값에 대한 유효성 검증은 별도의 클래스에서 수행
- 입력값에 대한 변환은 별도의 클래스에서 수행
- 입력값에 대해 비즈니스와 관련된 검증은 도메인에서 수행

2. 메뉴를 추천하는 기능

- 카테고리를 고르는 기능 구현 :
  -> 1~5사이의 숫자를 고르고 고른 숫자가 리스트에 등장하는 횟수를 검사하여 유효한 카테고리 번호 리스트를 생성
- 메뉴를 고르는 기능 구현
  : 고른 카테고리의 번호로 음식들을 조회하여 리스트를 섞은 후 첫번째 인덱스의 음식이름을 고른다
  -> 음식 이름을 이미 코치가 추천받은 음식 리스트에 가지고 있는 지 검사, 음식 이름이 코치가 먹지 못하는 음식 리스트에 존재 하는 지 검사
  -> 검사에 실패한 경우 다시 1번 과정


3. 코치에 대한 도메인

- 메뉴를 추천하는 기능이 구현된 클래스를 사용하여 자신의 추천 점심 메뉴를 생성한다.
- 코치의 이름, 먹지 못하는 음식 리스트, 추천 받은 음식 리스트을 필드로 가진다.

4. 코치들에 대한 도메인 ( 코치를 감싼 일급 컬렉션 )

- 중복된 이름이 존재하는지 검사한다.


5. 뷰에서 출력하기 위한 DTO

- 코치의 이름과 코치가 추천받은 메뉴 리스트를 필드로 가진다.

