# Project_AT — Automation Testing for PLT E-Learning

## Giới thiệu

Đây là dự án kiểm thử tự động cho nền tảng học trực tuyến PLT E-Learning, truy cập tại địa chỉ https://elearning.plt.pro.vn. Dự án được xây dựng bằng Java, sử dụng Selenium WebDriver để điều khiển trình duyệt và TestNG làm framework tổ chức và thực thi các ca kiểm thử.

Kiến trúc dự án tuân theo mô hình Page Object Model (POM), trong đó mỗi trang giao diện được đóng gói thành một class riêng biệt. Cách tổ chức này giúp tách biệt rõ ràng giữa logic điều hướng giao diện và logic kiểm thử, từ đó dễ bảo trì và mở rộng khi hệ thống thay đổi.

---

## Phạm vi kiểm thử

Dự án tập trung vào hai luồng nghiệp vụ chính của hệ thống:

**Luồng quản lý khóa học (dành cho Admin):** Kiểm thử chức năng tạo khóa học mới và cập nhật nội dung chương, bài học thông qua giao diện quản trị.

**Luồng giao diện học viên:** Kiểm thử trải nghiệm người dùng khi duyệt khóa học, xem nội dung chương và bài học, so sánh dữ liệu hiển thị với kết quả kỳ vọng, và phát video bài giảng.

---

## Công nghệ sử dụng

| Thành phần | Phiên bản |
|---|---|
| Java | SE 23 |
| Selenium WebDriver | 3.5.3 |
| TestNG | 7.11.0 |
| json-simple | 1.1.1 |
| SLF4J API | 2.0.13 |
| SLF4J Simple | 2.0.13 |
| ChromeDriver | Đính kèm trong thư mục driver/ |
| Môi trường phát triển | Eclipse IDE |

---

## Cấu trúc dự án

```
Project_AT/
├── driver/
│   └── chromedriver.exe
├── Json_img/
│   ├── taokhoahoc.json
│   ├── thembaihoc.json
│   ├── danhsach_khoahoc.json
│   ├── diendan.json
│   ├── themsinhvien.json
│   └── images.jpg (và các ảnh khác)
├── src/
│   ├── pages/
│   │   ├── LoginPage.java
│   │   ├── LoginPageUser.java
│   │   ├── CoursePage.java
│   │   └── LearningPage.java
│   └── TestNG/
│       ├── Them_CapNhatKhoaHoc.java
│       └── Giao_Dien_Hoc_Vien.java
├── testng.xml
├── .classpath
└── .project
```

Thư mục `pages/` chứa các Page Object Class, mỗi class tương ứng với một trang trên hệ thống. Thư mục `TestNG/` chứa các class kiểm thử thực thi các ca kiểm thử theo kịch bản. Dữ liệu đầu vào được đặt trong `Json_img/` dưới dạng file JSON, tách biệt hoàn toàn với mã nguồn.

---

## Mô tả các ca kiểm thử

### Module 1: Thêm và cập nhật khóa học

File thực thi: `src/TestNG/Them_CapNhatKhoaHoc.java`

Tài khoản sử dụng: Admin (`test.pltsolutions@gmail.com`)

**TC2 (priority = 1) — Tạo khóa học mới**

Ca kiểm thử này mô phỏng luồng một người quản trị tạo một khóa học hoàn chỉnh: đăng nhập vào hệ thống, điều hướng đến menu Khóa học, mở form thêm mới, tải ảnh bìa lên thông qua hộp thoại file của hệ điều hành (dùng Robot API), nhập tên và mô tả khóa học lấy từ file `taokhoahoc.json`, sau đó lưu và xác nhận. Quy trình lặp lại cho tất cả bộ dữ liệu có trong file JSON.

**TC1 (priority = 2) — Cập nhật nội dung khóa học**

Ca kiểm thử này thực hiện sau TC2. Nó chọn khóa học "Database_nhom3" từ danh sách, mở tab Nội dung, vào chế độ chỉnh sửa, rồi cập nhật tiêu đề và mô tả của từng chương và bài học theo dữ liệu trong file `thembaihoc.json`. Sau mỗi lần cập nhật, hệ thống sẽ lưu và xác nhận thay đổi.

---

### Module 2: Giao diện học viên

File thực thi: `src/TestNG/Giao_Dien_Hoc_Vien.java`

Tài khoản sử dụng: Học viên (`test1.pltsolutions@gmail.com`)

**TC1 (priority = 1) — Duyệt toàn bộ khóa học**

Sau khi đăng nhập, học viên chọn khóa học "Lập trình Web cơ bản" và duyệt qua toàn bộ 6 chương với tổng cộng 36 bài học. Ca kiểm thử xác nhận rằng tất cả các bài học có thể được click và hiển thị bình thường.

**TC2 (priority = 2) — Kiểm tra tên chương**

So sánh tên hiển thị của từng chương trên giao diện với danh sách kỳ vọng. Kết quả của từng chương được in ra console dưới dạng KHOP hoặc KHONG KHOP kèm giá trị thực tế.

Danh sách kỳ vọng:
- Chuong 1: HTML co ban
- Chuong 2: CSS va thiet ke giao dien
- Chuong 3: JavaScript can ban
- Chuong 4: JavaScript nang cao
- Chuong 5: Responsive Website
- Chuong 6: Du an mini Website ca nhan

**TC3 (priority = 3) — Kiểm tra mô tả chương**

Tương tự TC2 nhưng áp dụng cho phần mô tả của mỗi chương. Đảm bảo nội dung giải thích mục tiêu học tập được hiển thị đúng với dữ liệu đã nhập.

**TC4 (priority = 4) — Kiểm tra tên bài học**

Duyệt qua tất cả bài học trong 6 chương và so sánh tên từng bài với danh sách kỳ vọng. Chương 1 có 9 bài, các chương còn lại có từ 3 đến 8 bài tùy theo cấu trúc khóa học.

**TC5 (priority = 5) — Kiểm tra mô tả bài học và phát video**

Điều hướng sang một khóa học khác, mở các bài học trong chương 1 và chương 2, so sánh nội dung mô tả bài học với dữ liệu kỳ vọng. Sau đó, ca kiểm thử chuyển focus vào iframe chứa video YouTube và thực hiện click vào nút phát để xác nhận chức năng phát video hoạt động bình thường.

---

## Cài đặt và chạy

### Yêu cầu ban đầu

- Java JDK 23 trở lên
- Eclipse IDE (đã cài plugin TestNG for Eclipse)
- Google Chrome với phiên bản tương thích với `chromedriver.exe` đính kèm

### Các bước thiết lập

**Bước 1 — Clone dự án**

```bash
git clone https://github.com/haipham1213/project_AutomationTesting.git
```

**Bước 2 — Import vào Eclipse**

Mở Eclipse, vào File > Import > Existing Projects into Workspace, trỏ đến thư mục vừa clone về và nhấn Finish.

**Bước 3 — Thêm thư viện JAR**

Tải các file JAR sau về máy và thêm vào Build Path của project (chuột phải vào project > Properties > Java Build Path > Add External JARs):

| File JAR | Nguồn tải |
|---|---|
| selenium-server-standalone-3.5.3.jar | https://github.com/SeleniumHQ/selenium/releases |
| testng-7.11.0.jar | https://mvnrepository.com/artifact/org.testng/testng |
| json-simple-1.1.1.jar | https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple |
| slf4j-api-2.0.13.jar | https://mvnrepository.com/artifact/org.slf4j/slf4j-api |
| slf4j-simple-2.0.13.jar | https://mvnrepository.com/artifact/org.slf4j/slf4j-simple |

Sau khi thêm JAR, cập nhật lại file `.classpath` nếu đường dẫn thư mục trên máy bạn khác với cấu hình mặc định.

**Bước 4 — Cập nhật đường dẫn ChromeDriver**

Trong cả hai file test, kiểm tra và sửa dòng sau cho phù hợp với vị trí thực tế trên máy:

```java
System.setProperty("webdriver.chrome.driver", "D:\\Project_AT\\driver\\chromedriver.exe");
```

**Bước 5 — Chuẩn bị dữ liệu test**

File JSON dữ liệu đã có sẵn trong thư mục `Json_img/`. Tuy nhiên, trong code đang trỏ đường dẫn tuyệt đối (ví dụ `D:\taokhoahoc.json`), nên bạn cần cập nhật lại đường dẫn trong các file test hoặc sao chép file JSON sang vị trí tương ứng.

Cấu trúc file `taokhoahoc.json`:

```json
[
  {
    "ten_khoahoc": "Tên khóa học",
    "mota_khoahoc": "Mô tả khóa học"
  }
]
```

Cấu trúc file `thembaihoc.json`:

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

**Bước 6 — Chạy kiểm thử**

Chuột phải vào file `Them_CapNhatKhoaHoc.java` hoặc `Giao_Dien_Hoc_Vien.java`, chọn Run As > TestNG Test để chạy từng module riêng lẻ. Có thể chạy toàn bộ suite qua file `testng.xml` bằng cách chuột phải > Run As > TestNG Suite.

---

## Lưu ý khi sử dụng

Các đường dẫn tuyệt đối được hard-code trong mã nguồn (đường dẫn ChromeDriver, file JSON, ảnh bìa) cần được cập nhật lại cho phù hợp với môi trường máy cục bộ trước khi chạy.

Phiên bản ChromeDriver phải khớp với phiên bản Google Chrome đang cài trên máy. Nếu Chrome được cập nhật tự động, có thể cần tải lại ChromeDriver tương ứng tại https://chromedriver.chromium.org/downloads.

Các ca kiểm thử trong cùng một file chạy tuần tự theo thứ tự `priority` và có sự phụ thuộc lẫn nhau về trạng thái UI. Ví dụ, TC1 trong `Them_CapNhatKhoaHoc.java` phải chạy sau TC2 vì nó thao tác trên dữ liệu vừa được tạo.

---

## Thông tin dự án

Dự án được thực hiện trong khuôn khổ thực tập tại PLT Solutions, phục vụ mục đích học tập và áp dụng kiểm thử tự động vào sản phẩm thực tế.
