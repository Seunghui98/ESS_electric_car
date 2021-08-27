# ⚡ 태양광 ESS 전기차 충전소 입지 선정 및 충전소 어플
2020년도 한전 전력데이터 신서비스 개발 경진대회에 참가하여 준비한 프로젝트입니다.  
한전에서 제공받은 빅데이터를 기반하여 광주광역시 지역의 태양광 기반 ESS(에너지 저장장치) 전기차 충전소의 최적 위치를 선정하였습니다. 그 후 선정된 태양광 ESS 충전소에 전기차 사용자의 충전소 접근 및 편리한 사용을 위해 서비스를 제공하는 어플리케이션을 제작하였습니다.   

<br>

## 👀 프로젝트 개요  
- 📆 프로젝트 기간  
  - 2020.07.20  ~ 2020.08.18 (4Weeks)


- 📌 기획 배경
  - 자동차 배출가스에 대한 국제적인 환경 규제가 강화되어 전기차 보급이 급증
  - 에너지 공급 방식의 다양화가 필요
  - 친환경 에너지인 태양광 에너지를 이용할 수 있는 전기차 충전소의 최적 위치 선정 분석 필요
  - 기존 전기차 충전소의 특징과 다른 태양광 ESS 전기차 충전소만의 특징을 고려하여 편리하게 이용할 수 있는 어플 제작


## 🔧 프로젝트 사용기술
1) 데이터 분석  
- R Studio
- QGIS

2) SW 구현
- Android Studio(Client)
- MySQL(DataBase)
- JSP(Server) - [GitHub](https://github.com/Seunghui98/ESS_electric_car_Server)  

<br>
<br>

## 📈 데이터 분석
### 1️⃣ 사용 데이터
- 광주광역시 전기차 충전량 데이터 - 한전 제공 데이터[비공개]
- 광주광역시 주요 교차로 교통량 데이터 - [공공데이터](https://www.data.go.kr/data/15056405/fileData.do)
- 광주광역시 사업체 데이터 - [공공데이터](https://www.data.go.kr/data/3073433/fileData.do)
- 광주광역시 인구통계 - [공공데이터](https://www.data.go.kr/data/3073420/fileData.do)

### 2️⃣ 모형 설정
- 독립 변수 : 충전량, 충전시간, 인구밀도, 사업체수, 종사자수, 제곱미터당 단가, 교통량
- 종속 변수 : 충전빈도

### 3️⃣ 회귀분석
![K-050](https://user-images.githubusercontent.com/54658745/131117698-37581a7e-a7c5-4484-b01b-083f114b424d.png)
![K-051](https://user-images.githubusercontent.com/54658745/131117756-15111620-cb71-4a0a-9e7e-f68e2b9226e5.png)

### 4️⃣ 클러스터링 분석
![K-052](https://user-images.githubusercontent.com/54658745/131117957-72f0fc55-56e4-4117-98da-ba0b978a67ac.png)
![K-053](https://user-images.githubusercontent.com/54658745/131117907-6039c1d1-0fdb-4f01-930c-cf8e2575dd28.png)

### 5️⃣ 최적 입지 선정
![K-054](https://user-images.githubusercontent.com/54658745/131118013-0f297ea0-8fe5-4292-a053-1bb7f5956e90.png)


##  :rocket: 전체 SW의 구조

![K-045](https://user-images.githubusercontent.com/54658745/131113262-af9e67bb-5ee7-49b9-b650-8d3b0e8ba971.png)

<br>
<br>

##  ✨ 화면 구성도 및 주요 기능
### 1️⃣ 로그인 및 지도 기능
![K-046](https://user-images.githubusercontent.com/54658745/131114005-ebc9d36a-786d-4a8a-afae-6c72d35b4685.png)  
### 2️⃣ 최적 위치로 선정된 태양광 ESS 전기차 충전소 정보 확인  
![K-047](https://user-images.githubusercontent.com/54658745/131114011-70d6948e-c760-4cea-8471-cc20650d8720.png)
### 3️⃣ 위치 기반으로 BEST 충전소 추천 기능
![K-048](https://user-images.githubusercontent.com/54658745/131114016-35743ceb-3cf4-431a-849e-4e5d94552df5.png)

<br>
<br>


## 📝 프로젝트 기대 효과
- 신재생에너지로의 전환과 이용자 충전 수요를 동시에 충족
- 공급자의 효율적인 충전소 운영
- 충전소 정보 제공을 통하여 사용자의 편의성 증대
- ESS를 이용하여 낭비되는 전력량 감소
- 전기차 홍보 효과와 그에 따른 전기차 소요 증가

