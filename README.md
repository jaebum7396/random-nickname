# githubActionCI-CD을 이용한 랜덤 닉네임 생성기 api서비스 

API 이용방법
https://bloodgang.shop/api/nickname

|요청변수|타입|허용값|필수/선택|설명|
|------|---|---|---|---|
|  count |int|1-10|선택(default=2)|결과 출력 건수|

### 예시
랜덤 닉네임 3개 요청
```
curl --location --request GET 'https://bloodgang.shop/api/nickname?count=3'
```

응답 
```jsop
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
