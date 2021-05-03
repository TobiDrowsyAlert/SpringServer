# Dlib를 활용한 졸음방지 애플리케이션 (java, python)

### 🔗Link

**Source**

[TobiDrowsyAlert/SpringServer](https://github.com/TobiDrowsyAlert/SpringServer)

[TobiDrowsyAlert/Android_APP](https://github.com/TobiDrowsyAlert/Android_APP)

## 📖 상세 내용
![Dlib를 활용한 졸음방지 애플리케이션 (java 72da5a0ceff94c9db875f283879c29ef 1](https://user-images.githubusercontent.com/44090332/116850146-0dcc5c00-ac2b-11eb-85c9-7fbd5cdd36ea.png)
![Dlib를 활용한 졸음방지 애플리케이션 (java 72da5a0ceff94c9db875f283879c29ef](https://user-images.githubusercontent.com/44090332/116850145-0d33c580-ac2b-11eb-868b-6cfab6576af3.png)
![image12303](https://user-images.githubusercontent.com/44090332/116850119-fee5a980-ac2a-11eb-9fdb-0dee3d1c7fb0.png)

![깜빡임resize](https://user-images.githubusercontent.com/44090332/116853839-a9f96180-ac31-11eb-9714-4711ccb2fea1.gif)
![눈감김resize](https://user-images.githubusercontent.com/44090332/116853846-abc32500-ac31-11eb-8681-4c326b1f9413.gif)
![하품resize](https://user-images.githubusercontent.com/44090332/116853852-ae257f00-ac31-11eb-82f6-95ed43bf32b8.gif)
![운전자이탈resize](https://user-images.githubusercontent.com/44090332/116853857-b087d900-ac31-11eb-9d17-1e8d82c477fb.gif)
![따라말하기resize](https://user-images.githubusercontent.com/44090332/116853863-b1b90600-ac31-11eb-8dd6-8efc0ca47670.gif)
![체조resize](https://user-images.githubusercontent.com/44090332/116853869-b382c980-ac31-11eb-8642-22450f6e7c3c.gif)

졸업 작품으로 진행한 프로젝트입니다.  졸음과 관련된 논문들을 참조하여, 눈 깜빡임 빈도, 하품 빈도, 눈 감김, 고개의 각도 등 졸음 징후를 확인하여, 졸음 운전 사고를 방지하는 것을 목적으로 동작하는 애플리케이션입니다. 인종과 성별 등을 개인마다 인식률에 차이가 있으므로 이를 완화하고자 졸음 발생 피드백을 통해 졸음 기준 값을 변경하는 작업을 기능을 갖고 있습니다. 

## 🛠️ 사용 기술 및 라이브러리

### ✔Language, Framework

- Java
- Spring3
- Android
- Python
- Flask

### ⚙Infra

- AWS EC2
- AWS RDS

## 📱 담당한 기능

- Retorift을 활용한 안드로이드-외부 플랫폼 간 HTTP 통신
- Dlib 라이브러리, 안드로이드 화면 얼굴 좌표 추출
- 네이버 음성인식 API를 활용한 졸음 피드백 및 피드백 처리 프로세스
- Spring을 통한 기본적인 유저 관련 기능들
- 사용자 로그 계산을 통한 졸음 인식 기준 값 변경
- AWS EC2 상의 톰캣 서버, Flask 서버 배포
- 운전자 취약시간 분석, 정보 갱신, 인식률 통계 계산 및 WebApplication으로 웹 그래프 출력
- JUnit과 Mock 객체를 활용한 단위 테스트, Postman을 이용해 통합 테스트 진행

## 💡 깨달은 점

- 네이버 음성인식 API, Dlib 라이브러리 등 외부 소스 분석 및 활용 능력 향상
- 서버 통신
    - 프로젝트 과정에서 HTTP 메시지의 플랫폼 독립적인 특성의 유용함을 깨달음
    - 미리 HTTP RESTful 설계 하는 것은 중요하다!
    - EC2 배포와 Linux 상의 톰캣 로그 확인 방법
    - 200(정상), 400(잘못된 요청), 404(페이지 확인불가), 502(게이트웨이 불량) 등 HTTP 오류 메시지 코드 확인 및 오류 처리 지식 향상, 연동 플랫폼-서버 간 디버깅

## 구체적 프로젝트
![image02](https://user-images.githubusercontent.com/44090332/116850117-fdb47c80-ac2a-11eb-81e5-55203d98d95e.png)

---
Dlib와 미리 학습된 dat파일을 활용해 안드로이드 카메라에서 얼굴을 분석, 데이터를 추출해서 지속적으로 AWS 상의 서버와 통신하며 졸음을 발생 시 경보와 로그 데이터를 저장하는 서비스

기존에 눈 감김 외에 졸음 관련 논문을 분석해 하품, 눈 깜빡임 횟수, 고개의 각도, 운전자 이탈 여부를 입력 값으로 추가해, 더 다양한 상황에서 동작시킵니다.

졸음의 기초 값은 졸음 논문에서 도출된 데이터를 기준으로 하되, 성별, 인종에 따라 인식률 여부가 판이하게 갈리므로 사용자의 로그 데이터를 활용해 기준 값을 조금씩 변동합니다.

위의 사항을 위해 인식률이 정확한 지 사용자의 피드백이 필요, 운전자가 운전 중에도 피드백이 가능하도록 네이버 음성인식 API를 활용했습니다.

## 프로젝트

---

## 시연 영상

---
