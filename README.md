SmartLibrary – Java + SQLite Kütüphane Yönetim Sistemi

SmartLibrary, Java ile geliştirilmiş basit bir konsol tabanlı kütüphane yönetim sistemidir.
Proje kapsamında OOP prensipleri, JDBC kullanımı, SQLite veri tabanı bağlantısı ve CRUD işlemleri uygulanmıştır.
🎯Özellikler

📚 Kitap ekleme, listeleme, güncelleme, silme

👤 Öğrenci ekleme ve listeleme

🔄 Kitap ödünç verme ve geri teslim alma

🗃 SQLite üzerinde kalıcı veri saklama (smartlibrary.db)

🧱 OOP sınıf yapıları

🔌 JDBC + PreparedStatement kullanımı

📂 Repository yapısı ile CRUD işlemleri

🏛 Kullanılan Teknolojiler

Java (JDK 8+)

SQLite

JDBC

VS Code

📂 Proje Yapısı
SmartLibrary
 ├── src
 │   └── smartlibrary
 │        ├── Book.java
 │        ├── Student.java
 │        ├── Loan.java
 │        ├── Database.java
 │        ├── BookRepository.java
 │        ├── StudentRepository.java
 │        ├── LoanRepository.java
 │        └── SmartLibraryApp.java
 ├── lib
 │   └── sqlite-jdbc.jar
 └── smartlibrary.db
▶️ Çalıştırma

Projeyi çalıştırmak için şu adımlar yeterlidir:

lib klasöründe gerekli olan sqlite-jdbc.jar dosyasının bulunduğunu kontrol edin.

SmartLibraryApp.java dosyasını çalıştırın.

Uygulama açıldığında konsolda çıkan menü üzerinden kitap, öğrenci ve ödünç işlemlerini yapabilirsiniz.

👤 Geliştirici

Nazey Gökdemir
