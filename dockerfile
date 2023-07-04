# 어플리케이션을 실행할 베이스 이미지 선택
FROM openjdk:11
LABEL authors="deang"

# 작업 디렉토리 설정
ARG JAR_FILE=build/libs/*.jar

# 어플리케이션 JAR 파일을 컨테이너의 작업 디렉토리로 복사
COPY ${JAR_FILE} app.jar

# 어플리케이션 실행을 위한 명령어 설정
ENTRYPOINT  ["java", "-jar", "/app.jar"]