# 🧪 Project_AT — Kiểm Thử Tự Động Website E-Learning

Dự án kiểm thử tự động cho nền tảng học trực tuyến **PLT E-Learning** ([elearning.plt.pro.vn](https://elearning.plt.pro.vn/trang-chu)), sử dụng **Selenium WebDriver** + **TestNG** theo mô hình **Page Object Model (POM)**.

---

## 📋 Mục Lục

- [Tổng Quan](#tổng-quan)
- [Công Nghệ Sử Dụng](#công-nghệ-sử-dụng)
- [Cấu Trúc Dự Án](#cấu-trúc-dự-án)
- [Các Test Case](#các-test-case)
- [Cài Đặt & Chạy](#cài-đặt--chạy)
- [Dữ Liệu Test (JSON)](#dữ-liệu-test-json)
- [Yêu Cầu Hệ Thống](#yêu-cầu-hệ-thống)

---

## 📌 Tổng Quan

Dự án tập trung kiểm thử hai luồng nghiệp vụ chính của hệ thống:

| Module | Mô tả |
|---|---|
| **Quản lý Khóa Học (Admin)** | Tạo khóa học mới, cập nhật nội dung chương & bài học |
| **Giao Diện Học Viên (User)** | Duyệt khóa học, xem chương/bài, kiểm tra thông tin hiển thị, phát video |

---

## 🛠️ Công Nghệ Sử Dụng

| Thư Viện / Công Cụ | Phiên Bản | Mục Đích |
|---|---|---|
| Java | SE 23 | Ngôn ngữ lập trình chính |
| Selenium WebDriver | 3.5.3 | Tự động hóa trình duyệt |
| TestNG | 7.11.0 | Framework kiểm thử |
| json-simple | 1.1.1 | Đọc dữ liệu test từ file JSON |
| SLF4J | 2.0.13 | Logging |
| ChromeDriver | (đính kèm) | Điều khiển Google Chrome |
| Eclipse IDE | — | Môi trường phát triển |

---

## 📂 Cấu Trúc Dự Án

```
Project_AT/
├── driver/
│   └── chromedriver.exe          # ChromeDriver để điều khiển Chrome
├── src/
│   ├── pages/                    # Page Object Classes
│   │   ├── LoginPage.java        # Trang đăng nhập (Admin)
│   │   ├── LoginPageUser.java    # Trang đăng nhập (Học viên)
│   │   ├── CoursePage.java       # Trang quản lý khóa học (Admin)
│   │   └── LearningPage.java     # Trang học của học viên
│   └── TestNG/                   # Test Classes
│       ├── Them_CapNhatKhoaHoc.java   # Test tạo & cập nhật khóa học
│       └── Giao_Dien_Hoc_Vien.java   # Test giao diện học viên
├── testng.xml                    # Cấu hình chạy TestNG Suite
├── .classpath                    # Cấu hình thư viện Eclipse
└── .project                      # Cấu hình dự án Eclipse
```

---

## ✅ Các Test Case

### 🔷 Module 1: Thêm & Cập Nhật Khóa Học (`Them_CapNhatKhoaHoc.java`)

> **URL:** `https://elearning.plt.pro.vn/trang-chu` | **Tài khoản:** Admin

| Test Case | Mô tả | Dữ liệu đầu vào |
|---|---|---|
| **TC2** (priority=1) | Đăng nhập Admin → Vào menu Khóa học → Thêm khóa học mới (upload ảnh + điền thông tin) → Xác nhận lưu | `taokhoahoc.json` |
| **TC1** (priority=2) | Chọn khóa học "Database_nhom3" → Mở tab "Nội dung" → Cập nhật tiêu đề/mô tả chương và bài học → Lưu | `thembaihoc.json` |

**Luồng TC2:**
```
Login (Admin) → Menu "Khóa học" → "Thêm mới" → Upload ảnh bìa
→ Nhập tên & mô tả (từ JSON) → Lưu → Xác nhận
```

**Luồng TC1:**
```
Chọn khóa học "Database_nhom3" → Tab "Nội dung" → "Sửa nội dung"
→ Cập nhật tiêu đề/mô tả chương & bài học (từ JSON) → Lưu → Xác nhận
```

---

### 🔷 Module 2: Giao Diện Học Viên (`Giao_Dien_Hoc_Vien.java`)

> **URL:** `https://elearning.plt.pro.vn/trang-chu` | **Tài khoản:** Học viên

| Test Case | Mô tả |
|---|---|
| **TC1** (priority=1) | Đăng nhập → Chọn khoá học "Lập trình Web cơ bản" → Duyệt qua toàn bộ 6 chương và tất cả bài học |
| **TC2** (priority=2) | So sánh tên từng chương hiển thị trên UI với dữ liệu kỳ vọng (KHỚP / KHÔNG KHỚP) |
| **TC3** (priority=3) | So sánh mô tả từng chương hiển thị trên UI với dữ liệu kỳ vọng |
| **TC4** (priority=4) | So sánh tên từng bài học trong tất cả 6 chương với dữ liệu kỳ vọng |
| **TC5** (priority=5) | Điều hướng sang khóa học khác → Kiểm tra mô tả bài học → Phát video YouTube nhúng trong bài |

**Khóa học được kiểm thử (TC1–TC4):** `Lập trình Web cơ bản` gồm 6 chương:
1. HTML cơ bản (9 bài)
2. CSS và thiết kế giao diện (8 bài)
3. JavaScript căn bản (8 bài)
4. JavaScript nâng cao (4 bài)
5. Responsive Website (3 bài)
6. Dự án mini Website cá nhân (4 bài)

---

## 🚀 Cài Đặt & Chạy

### 1. Yêu cầu cài đặt trước

- [Java JDK 23+](https://www.oracle.com/java/technologies/downloads/)
- [Eclipse IDE](https://www.eclipse.org/downloads/) (có hỗ trợ TestNG plugin)
- [Google Chrome](https://www.google.com/chrome/) (phiên bản khớp với `chromedriver.exe`)
- Plugin **TestNG for Eclipse** (cài qua Eclipse Marketplace)

### 2. Clone dự án

```bash
git clone https://github.com/<your-username>/Project_AT.git
```

### 3. Import vào Eclipse

1. Mở Eclipse → **File > Import > Existing Projects into Workspace**
2. Trỏ đến thư mục `Project_AT`
3. Click **Finish**

### 4. Thêm thư viện JAR

Download các file JAR sau và đặt vào một thư mục cố định (ví dụ `C:\ThuVienTest\`), sau đó thêm vào **Build Path** của project:

| File JAR | Tải tại |
|---|---|
| `selenium-server-standalone-3.5.3.jar` | [Selenium Releases](https://github.com/SeleniumHQ/selenium/releases) |
| `testng-7.11.0.jar` | [TestNG Maven](https://mvnrepository.com/artifact/org.testng/testng) |
| `json-simple-1.1.1.jar` | [JSON Simple](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple) |
| `slf4j-api-2.0.13.jar` | [SLF4J](https://mvnrepository.com/artifact/org.slf4j/slf4j-api) |
| `slf4j-simple-2.0.13.jar` | [SLF4J](https://mvnrepository.com/artifact/org.slf4j/slf4j-simple) |

> ⚠️ Sau khi thêm JAR, cập nhật đường dẫn trong file `.classpath` nếu vị trí thư mục khác.

### 5. Cập nhật đường dẫn ChromeDriver

Mở các file test và cập nhật đường dẫn ChromeDriver:

```java
// Trong Them_CapNhatKhoaHoc.java và Giao_Dien_Hoc_Vien.java
System.setProperty("webdriver.chrome.driver", "D:\\Project_AT\\driver\\chromedriver.exe");
```

Thay bằng đường dẫn tuyệt đối tương ứng trên máy của bạn.

### 6. Chuẩn bị dữ liệu test

Tạo 2 file JSON tại ổ `D:\` (hoặc cập nhật đường dẫn trong code):

**`D:\taokhoahoc.json`** — Dữ liệu tạo khóa học:
```json
[
  {
    "ten_khoahoc": "Tên khóa học mới",
    "mota_khoahoc": "Mô tả ngắn về khóa học"
  }
]
```

**`D:\thembaihoc.json`** — Dữ liệu cập nhật bài học:
```json
[
  {
    "tieude_chuong": "Tiêu đề chương",
    "mota_chuong": "Mô tả chương",
    "tieude_bai": "Tiêu đề bài học",
    "mota_bai": "Mô tả bài học"
  }
]
```

**Ảnh bìa khóa học:** Đặt file ảnh tại `D:\images.jpg` (hoặc cập nhật đường dẫn trong `TC2`).

### 7. Chạy kiểm thử

**Cách 1 — Chạy file TestNG cụ thể:**
- Click chuột phải vào `Them_CapNhatKhoaHoc.java` hoặc `Giao_Dien_Hoc_Vien.java`
- Chọn **Run As > TestNG Test**

**Cách 2 — Chạy toàn bộ Suite:**
- Click chuột phải vào `testng.xml`
- Chọn **Run As > TestNG Suite**

---

## 📁 Dữ Liệu Test (JSON)

Dự án sử dụng file JSON bên ngoài để tách dữ liệu khỏi code test, giúp dễ dàng thay đổi test data mà không cần sửa source code.

| File | Dùng bởi | Mục đích |
|---|---|---|
| `D:\taokhoahoc.json` | `TC2` | Danh sách khóa học cần tạo |
| `D:\thembaihoc.json` | `TC1` | Nội dung chương & bài học cần cập nhật |

---

## 💻 Yêu Cầu Hệ Thống

| Thành phần | Yêu cầu |
|---|---|
| Hệ điều hành | Windows 10/11 |
| Java | JDK 23 trở lên |
| Trình duyệt | Google Chrome (phiên bản phù hợp với ChromeDriver) |
| RAM | Tối thiểu 4 GB |
| IDE | Eclipse IDE for Java Developers |

---

## 👥 Nhóm Phát Triển

Dự án kiểm thử tự động được thực hiện trong khuôn khổ thực tập / học phần kiểm thử phần mềm tại **PLT Solutions**.

---

## 📝 Ghi Chú

- Các đường dẫn tuyệt đối (driver, JSON, ảnh) cần được cập nhật theo môi trường máy cục bộ trước khi chạy.
- ChromeDriver phải có phiên bản tương thích với Google Chrome đang cài đặt. Kiểm tra tại [chromedriver.chromium.org](https://chromedriver.chromium.org/downloads).
- Các test case chạy tuần tự theo `priority` và phụ thuộc vào trạng thái UI sau mỗi bước.
