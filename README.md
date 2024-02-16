# Amigoscode_SpringSecurity
spring security learning from amigosCode tutorial

## List

## 1. Simple GET API (Done)

- get student by calling "api/v1/students/1"

## 2. add starter spring security

- `'org.springframework.boot:spring-boot-starter-security'`
- Here: default login UI will come
- username: user
- password: will show in each startup
- This will give default form based authentication.

## 3. Basic AUTH incorporate

- here no form based login and 
- no logout url
- each time login url will popup
-      http
          .authorizeRequests()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic();
- 
