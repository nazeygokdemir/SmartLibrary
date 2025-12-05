package smartlibrary;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SmartLibraryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookRepository bookRepo = new BookRepository();
    private static final StudentRepository studentRepo = new StudentRepository();
    private static final LoanRepository loanRepo = new LoanRepository();

    public static void main(String[] args) {

        Database.initialize();

        int choice;
        do {
            printMenu();
            choice = readInt("Seçiminiz: ");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    listLoans();
                    break;
                case 7:
                    returnBook();
                    break;
                case 0:
                    System.out.println("Çıkılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }

        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("\n=== SmartLibrary Menü ===");
        System.out.println("1. Kitap Ekle");
        System.out.println("2. Kitapları Listele");
        System.out.println("3. Öğrenci Ekle");
        System.out.println("4. Öğrencileri Listele");
        System.out.println("5. Kitap Ödünç Ver");
        System.out.println("6. Ödünç Listesini Görüntüle");
        System.out.println("7. Kitap Geri Teslim Al");
        System.out.println("0. Çıkış");
    }

    private static int readInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Lütfen sayı girin: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static void addBook() {
        String title = readLine("Kitap adı: ");
        String author = readLine("Yazar: ");
        int year = readInt("Basım yılı: ");

        Book book = new Book(title, author, year);
        bookRepo.add(book);
    }

    private static void listBooks() {
        List<Book> books = bookRepo.getAll();
        System.out.println("\n--- Kitaplar ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static void addStudent() {
        String name = readLine("Öğrenci adı: ");
        String department = readLine("Bölüm: ");

        Student s = new Student(name, department);
        studentRepo.add(s);
    }

    private static void listStudents() {
        List<Student> students = studentRepo.getAll();
        System.out.println("\n--- Öğrenciler ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void borrowBook() {
        int studentId = readInt("Öğrenci ID: ");
        int bookId = readInt("Kitap ID: ");

        Student student = studentRepo.getById(studentId);
        if (student == null) {
            System.out.println("Bu ID'ye ait bir öğrenci yok.");
            return;
        }

        Book book = bookRepo.getById(bookId);
        if (book == null) {
            System.out.println("Bu ID'ye ait bir kitap yok.");
            return;
        }

        if (loanRepo.isBookOnLoan(bookId)) {
            System.out.println("Bu kitap şu anda ödünçte.");
            return;
        }

        String useToday = readLine("Bugünün tarihini kullanmak istiyor musun? (E/H): ");
        String date;
        if (useToday.equalsIgnoreCase("E")) {
            date = LocalDate.now().toString();
        } else {
            date = readLine("Tarih gir (örn: 2025-12-05): ");
        }

        Loan loan = new Loan(bookId, studentId, date);
        loanRepo.add(loan);
    }

    private static void listLoans() {
        List<Loan> loans = loanRepo.getAll();
        System.out.println("\n--- Ödünç Kayıtları ---");
        for (Loan l : loans) {
            System.out.println(l);
        }
    }

    private static void returnBook() {
        int bookId = readInt("Geri teslim edilecek kitabın ID'si: ");

        String useToday = readLine("Bugünün tarihini kullanmak istiyor musun? (E/H): ");
        String date;
        if (useToday.equalsIgnoreCase("E")) {
            date = LocalDate.now().toString();
        } else {
            date = readLine("Tarih gir (örn: 2025-12-05): ");
        }

        loanRepo.returnBook(bookId, date);
    }
}
