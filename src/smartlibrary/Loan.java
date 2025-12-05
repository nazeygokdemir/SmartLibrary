package smartlibrary;

public class Loan {
    private int id;
    private int bookId;
    private int studentId;
    private String dateBorrowed;
    private String dateReturned;

    public Loan(int bookId, int studentId, String dateBorrowed) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.dateBorrowed = dateBorrowed;
    }

    public Loan(int id, int bookId, int studentId, String dateBorrowed, String dateReturned) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getStudentId() { return studentId; }
    public String getDateBorrowed() { return dateBorrowed; }
    public String getDateReturned() { return dateReturned; }

    public void setId(int id) { this.id = id; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setDateBorrowed(String dateBorrowed) { this.dateBorrowed = dateBorrowed; }
    public void setDateReturned(String dateReturned) { this.dateReturned = dateReturned; }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", studentId=" + studentId +
                ", dateBorrowed='" + dateBorrowed + '\'' +
                ", dateReturned='" + dateReturned + '\'' +
                '}';
    }
}
