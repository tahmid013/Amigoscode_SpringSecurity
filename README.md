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

## 6. Management APIs with csrf disabled

- csrf is disabled temporary for ensuring the student management (admin)
  apis are workable
- in ApplicationSecurityConfig add 
-     .csrf().disable()   

## 7. Permission based authentication

- user permission
  - user1: read, write
  - user2: only read
- two ways
  - ant-matchers
  - annotations

## 8. Granted Authorities 

- Instead of actual roles in UserDetails we will use `SimpleGrantedAuthority`
  - `roles("STUDENT")` will be treated as `ROLE_STUDENT` 
- In UserRole enum write function to get each role's permission in : Set of `<SimpleGrantedAuthority>`
-     Set<SimpleGrantedAuthority> permissions = getCurRolePermissions().stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                            .collect(Collectors.toSet());
- antmathers accordingly to allow
  - all `/api/student/**` for `STUDENT` role
  - management api's write (DELETE, POST, PUT) permission only for them who has `COURSE_WRITE` in other words : `ADMIN` only 
  - management api's read (GET) permission for role: `ADMIN` and `ADMIN_TRAINEE`
-     .antMatchers("/api/**").hasRole(STUDENT.name())
      .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
      .antMatchers(HttpMethod.POST, "/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
      .antMatchers(HttpMethod.PUT, "/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
      .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN_TRAINEE.name(), ADMIN.name())
                  