# sb3-mssql-board

Spring Boot 3, Thymeleaf, Spring Data JPA, Microsoft SQL Server로 구현한 간단한 게시판 예제입니다.

## 목차

- [소개](#소개)
- [기술 스택](#기술-스택)
- [주요 기능](#주요-기능)
- [프로젝트 구조](#프로젝트-구조)
- [시작하기](#시작하기)
- [주요 URL](#주요-url)
- [데이터베이스](#데이터베이스)
- [테스트](#테스트)

## 소개

`sb3-mssql-board`는 게시글 목록, 상세 보기, 작성, 댓글 등록 기능을 제공하는 Spring MVC 기반 게시판 프로젝트입니다. 화면은 Thymeleaf와 Bootstrap CDN을 사용하며, 데이터는 Microsoft SQL Server에 저장합니다.

## 기술 스택

| 구분 | 기술 |
| --- | --- |
| Language | Java 21 |
| Framework | Spring Boot 3.5.14 |
| Build Tool | Gradle |
| View | Thymeleaf, Bootstrap 5 |
| Persistence | Spring Data JPA, Hibernate |
| Database | Microsoft SQL Server |
| Security | Spring Security |
| Utility | Lombok |

## 주요 기능

- 게시글 목록 조회
- 게시글 페이징 처리
- 게시글 상세 조회
- 게시글 작성
- 댓글 등록
- 게시글별 댓글 수 조회
- 댓글 소프트 삭제를 위한 `is_deleted` 컬럼 구성

## 프로젝트 구조

```text
.
+-- src
|   +-- main
|   |   +-- java/com/ezlevup/sb3_mssql_board
|   |   |   +-- controller
|   |   |   +-- domain
|   |   |   +-- dto
|   |   |   +-- repository
|   |   |   +-- service
|   |   |   +-- SecurityConfig.java
|   |   |   +-- Sb3MssqlBoardApplication.java
|   |   +-- resources
|   |       +-- templates
|   |       +-- sql
|   |       +-- application.properties
|   |       +-- application-local.properties
|   +-- test
+-- build.gradle
+-- settings.gradle
+-- README.md
```

## 시작하기

### 1. 저장소 클론

```bash
git clone <repository-url>
cd sb3-mssql-board
```

### 2. 데이터베이스 설정

`src/main/resources/application-local.properties`에서 로컬 SQL Server 접속 정보를 환경에 맞게 수정합니다.

```properties
spring.datasource.url=jdbc:sqlserver://127.0.0.1;instanceName=<INSTANCE_NAME>;databaseName=<DATABASE_NAME>;encrypt=true;trustServerCertificate=true
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
```

### 3. 애플리케이션 실행

Windows:

```bash
gradlew.bat bootRun
```

macOS 또는 Linux:

```bash
./gradlew bootRun
```

실행 후 브라우저에서 다음 주소로 접속합니다.

```text
http://localhost:8080/board/list
```

## 주요 URL

| Method | URL | 설명 |
| --- | --- | --- |
| GET | `/board/list` | 게시글 목록 |
| GET | `/board/list?page=0` | 게시글 목록 페이징 |
| GET | `/board/view/{id}` | 게시글 상세 |
| GET | `/board/write` | 게시글 작성 화면 |
| POST | `/board/write` | 게시글 저장 |
| POST | `/comment/write/{boardId}` | 댓글 저장 |

## 데이터베이스

게시글 테이블은 JPA 엔티티(`Board`)를 기준으로 생성됩니다. 댓글 테이블은 `src/main/resources/sql/schema.sql`을 참고할 수 있습니다.

댓글 테이블 예시:

```sql
CREATE TABLE tb_comment (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    board_id BIGINT NOT NULL,
    content NVARCHAR(1000) NOT NULL,
    writer VARCHAR(50) NOT NULL,
    created_at DATETIME2 DEFAULT GETDATE(),
    is_deleted CHAR(1) DEFAULT 'N'
);
```

## 테스트

```bash
gradlew.bat test
```

macOS 또는 Linux:

```bash
./gradlew test
```
