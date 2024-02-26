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
 
## 4. Ant Matchers
- white listing by adding index.html
-     http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
- add userDetails()
-     UserDetails john = User.builder()
          .username("user")
          .password(passwordEncoder.encode("1234"))
          .roles("STUDENT")
          .build();
- we need to add password encoder
-       @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder(10);
        }

## 5. Roles & Permission

User Role:
-      STUDENT(Sets.newHashSet()),
        ADMIN(Sets.newHashSet(
          COURSE_READ,
          COURSE_WRITE,
          STUDENT_READ,
          STUDENT_WRITE
        ));
        
        private final Set<ApplicationUserPermission> permissions;
        
        ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
          this.permissions = permissions;
        }
User permission:
-     STUDENT_READ("student:read"),
      STUDENT_WRITE("student:write"),
      COURSE_READ("course:read"),
      COURSE_WRITE("course:write");
  
      private final String permissions;
      
      ApplicationUserPermission(String permission) {
      this.permissions = permission;
      }
      
      public String getPermission() {
      return permissions;
      }