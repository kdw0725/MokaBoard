# MokaBoard
하누모카의 Spring 샘플 프로젝트
Spring Lagacy project로 생성됨.

# 개요
스프링 공부용 

# 예제
1.계시판(페이징 + 댓글)
2.파일업로드, 다운로드
3.로그인 (스프링 인터셉터)
4.트랜젝션(AOP)

# 주요 URL

/home
/sample : 실제 샘플 페이지


# 개발환경
windows 10 pro(64)  
java 1.8  
spring 4  
tomcat 9
mysql 8.0.11  
mybatis  
thymeleaf3
bootstrap

# 개발도구
STS  
docker  
mysql workbench 8.0.11 CE (64bit)  

# DB 정보
docker-compose를 사용하여 mysql 서버사용
docker와  docker compose가 설치된 환경에서 docker-compose 폴더에서 아래 명령어 실행

/dev_sql01.sql 파일 db관련 스키마및 쿼리
/MokaBoard_docker 폴더 docker 파일 폴더

```
docker stop$(docker ps -a -q)
docker compose start
```

db ip: localhost
db port : 3306
db 계정
root : root / password

dev스키마 : hanumoka_dev
dev계정: hanumoka / password



