**Chức năng cơ bản:**
  - Quản lý sách (thêm, sửa, xóa, tìm kiếm).
  - Quản lý khách hàng.
  - Quản lý đơn hàng / bán sách.
  - Báo cáo doanh thu (nếu còn thời gian).
  - Quản lý nhân viên (option)
  - Chatbot tìm sách, tóm tắt nội dung (option)

**Công nghệ:**
- Java 17 + Spring Boot 3.2.0
- SQL Server
- Spring Data JPA, Spring Security
- Maven, Lombok
- SpringDoc OpenAPI (Swagger)
- Chức năng của từng folder:
  - Config: Chứa các class cấu hình (Cấu hình Db, Cấu hình Web, Cấu hình bảo mật)
  - Controller: Chứa các controller của các entity
  - dto: design pattern của SpringBoot (Tách biệt entity và api, bảo mật, validation input)
  - entity: chứa các đối tượng của dự án(User, ...)
  - Exception: chứa các exception để debug
  - repository(truy cập dữ liệu trong SpringBoot, cầu nối giữa Service và Db)
  - util: chứa tiện ích có thể sử dụng lại
  - resources: tài nguyên, sql
  - pom.xml: các thư viện sử dụng

## Cấu trúc dự án
```
BTL_OOP/
├── src/
│   ├── main/
│   │   ├── java/com/btl/oop/
│   │   │   ├── BtlOopApplication.java          # Main Spring Boot application
│   │   │
│   │   │   ├── config/                         # Cấu hình (DB, Security, Web MVC)
│   │   │   │   ├── DatabaseConfig.java
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── WebConfig.java
│   │   │
│   │   │   ├── controller/                     # Controllers cho cả Customer & Admin
│   │   │   │   ├── AuthController.java         # Login, Register, Logout
│   │   │   │   ├── BookViewController.java     # Cho Customer xem sách
│   │   │   │   ├── OrderController.java        # Customer đặt hàng
│   │   │   │   ├── UserController.java         # Admin CRUD User
│   │   │   │   ├── BookController.java         # Admin CRUD Book
│   │   │   │   └── OrderAdminController.java   # Admin quản lý đơn hàng
│   │   │
│   │   │   ├── dto/                            # Data Transfer Objects
│   │   │   │   ├── ApiResponseDTO.java
│   │   │   │   ├── UserRequestDTO.java
│   │   │   │   ├── UserResponseDTO.java
│   │   │   │   ├── BookRequestDTO.java
│   │   │   │   └── BookResponseDTO.java
│   │   │
│   │   │   ├── entity/                         # JPA Entities (bảng CSDL)
│   │   │   │   ├── BaseEntity.java
│   │   │   │   ├── User.java                   # Dùng cho cả Customer & Admin
│   │   │   │   ├── Book.java
│   │   │   │   ├── Order.java
│   │   │   │   └── OrderItem.java
│   │   │
│   │   │   ├── exception/                      # Ngoại lệ custom
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │
│   │   │   ├── repository/                     # Data Access Layer (DAO)
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── BookRepository.java
│   │   │   │   └── OrderRepository.java
│   │   │
│   │   │   ├── service/                        # Business Logic Layer
│   │   │   │   ├── UserService.java
│   │   │   │   ├── BookService.java
│   │   │   │   ├── OrderService.java
│   │   │   │   └── impl/
│   │   │   │       ├── UserServiceImpl.java
│   │   │   │       ├── BookServiceImpl.java
│   │   │   │       └── OrderServiceImpl.java
│   │   │
│   │   │   └── util/                           # Helper / Utils
│   │   │       └── MapperUtil.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties          # Cấu hình Spring Boot
│   │       ├── schema.sql                      # Script tạo CSDL
│   │       └── templates/                      # Giao diện Thymeleaf
│   │           ├── index.html                  # Landing Page
│   │           ├── auth/                       # Trang login/register
│   │           │   ├── login.html
│   │           │   └── register.html
│   │           ├── books/                      # Customer & Admin CRUD sách
│   │           │   ├── list.html
│   │           │   ├── create.html
│   │           │   └── update.html
│   │           ├── users/                      # Quản lý User (Admin)
│   │           │   ├── list.html
│   │           │   ├── create.html
│   │           │   └── update.html
│   │           ├── orders/                     # Quản lý/đặt hàng
│   │           │   ├── cart.html
│   │           │   ├── checkout.html
│   │           │   ├── list.html               # Cho admin quản lý đơn hàng
│   │           │   └── detail.html
│   │           └── fragments/                  # Layout chung (header/footer)
│   │               ├── header.html
│   │               └── footer.html
│   │
│   └── test/                                   # Unit & Integration Tests
│
├── pom.xml                                     # Maven dependencies
└── README.md                                   # Tài liệu dự án
```

## Các Entity cần thêm cho hệ thống nhà sách
- **Book** - Quản lý sách
- **Customer** - Quản lý khách hàng  
- **Order** - Quản lý đơn hàng
- **OrderItem** - Chi tiết đơn hàng
- **Category** - Danh mục sách
- **Author** - Tác giả
- **Employee** - Nhân viên (option)
- **Revenue** - Báo cáo doanh thu (option)
- Java Spring Boot

## Class Diagram
![](UML/class_diagram.png)