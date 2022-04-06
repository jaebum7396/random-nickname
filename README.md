# githubActionCI-CD을 이용한 근본없는 랜덤 닉네임 생성기 api서비스 

오픈 API 요청 URL

근본없는 닉네임
https://bloodgang.shop/api/v1/nickname


캐릭터 닉네임(3/30일 추가)
https://bloodgang.shop/api/v1/character

닉네임생성 요청 변수(Request Parameters)

|요청변수|타입|허용값|필수/선택|설명|
|------|---|---|---|---|
|  count |int|1-10|선택(default=2)|결과 출력 건수|

### 닉네임 요청 출력 메시지 JSON 예시
랜덤 근본없는 닉네임 3개 요청
``` bash
curl --location --request GET 'https://bloodgang.shop/api/nickname?count=3'
```

응답Json예시
```json
{
    "success": true,
    "code": 0,
    "msg": "성공하였습니다.",
    "word": [
        "언짢은스튜디오와 천녀",
        "그른견적서와 모필",
        "점잖은편도선염과 시방"
    ]
}
```

랜덤 캐릭터 닉네임 5개
``` bash
curl --location --request GET 'http://localhost:8080/api/v1/character?count=5'
```
응답Json예시
```json
{
  "success": true,
  "code": 0,
  "msg": "성공하였습니다.",
  "word": [
    "곧은 스톰",
    "케케묵은 스패로",
    "네모난 구피",
    "쉬운 스패로",
    "있는 플루토"
  ]
}
```

---
## 👽랜덤 닉네임 만드는 과정


위키 백과사전에서 한글 형용사 모음을 크롤링후 txt파일로 저장 (166개)
https://ko.wiktionary.org/wiki/%EB%B6%84%EB%A5%98:%ED%95%9C%EA%B5%AD%EC%96%B4_%EA%B4%80%ED%98%95%EC%82%AC%ED%98%95(%ED%98%95%EC%9A%A9%EC%82%AC)

위키 백과사전에서 한글 명사 모음을 크롤링후 txt파일로 저장 (16721개)
https://ko.wiktionary.org/wiki/%EB%B6%84%EB%A5%98:%ED%95%9C%EA%B5%AD%EC%96%B4_%EB%AA%85%EC%82%AC

단어 조합


형용사+명사+조사+명사

나올수 있는 경우의 수

46,412,245,606개

## 단어에 따라서 조사를 사용하는 함수 구현

한글 단어는 받침에따라서 을/를이 달라진다.
ex) 사과를, 작품을 

종성 구하는 공식
```
종성 = (문자유니코드 - 0xAC00) % 28
```

``` java
 if(lastCharIndex > 0) {
 //받침이 있을경우
 } else {
 //받침이 없을 경우
 }
```
