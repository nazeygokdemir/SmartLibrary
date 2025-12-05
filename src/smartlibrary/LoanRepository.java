package smartlibrary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    public void add(Loan loan) {
        String sql = "INSERT INTO loans(bookId, studentId, dateBorrowed, dateReturned) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, loan.getBookId());
            ps.setInt(2, loan.getStudentId());
            ps.setString(3, loan.getDateBorrowed());
            ps.setString(4, loan.getDateReturned()); // genelde null

            ps.executeUpdate();
            System.out.println("Ödünç kaydı eklendi.");

        } catch (SQLException e) {
            System.out.println("Ödünç eklenirken hata: " + e.getMessage());
        }
    }

    public void update(Loan loan) {
        String sql = "UPDATE loans SET bookId = ?, studentId = ?, dateBorrowed = ?, dateReturned = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, loan.getBookId());
            ps.setInt(2, loan.getStudentId());
            ps.setString(3, loan.getDateBorrowed());
            ps.setString(4, loan.getDateReturned());
            ps.setInt(5, loan.getId());

            ps.executeUpdate();
            System.out.println("Ödünç kaydı güncellendi.");

        } catch (SQLException e) {
            System.out.println("Ödünç güncellenirken hata: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM loans WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Ödünç kaydı silindi.");

        } catch (SQLException e) {
            System.out.println("Ödünç silinirken hata: " + e.getMessage());
        }
    }

    public Loan getById(int id) {
        String sql = "SELECT * FROM loans WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Loan(
                            rs.getInt("id"),
                            rs.getInt("bookId"),
                            rs.getInt("studentId"),
                            rs.getString("dateBorrowed"),
                            rs.getString("dateReturned")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Ödünç getirilirken hata: " + e.getMessage());
        }

        return null;
    }

    public List<Loan> getAll() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Loan loan = new Loan(
                        rs.getInt("id"),
                        rs.getInt("bookId"),
                        rs.getInt("studentId"),
                        rs.getString("dateBorrowed"),
                        rs.getString("dateReturned")
                );
                loans.add(loan);
            }

        } catch (SQLException e) {
            System.out.println("Ödünç kayıtları listelenirken hata: " + e.getMessage());
        }

        return loans;
    }

    public boolean isBookOnLoan(int bookId) {
        String sql = "SELECT COUNT(*) AS cnt FROM loans WHERE bookId = ? AND dateReturned IS NULL";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cnt") > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Kitap ödünçte mi kontrolü hatası: " + e.getMessage());
        }

        return false;
    }

    public void returnBook(int bookId, String returnDate) {
        String sql = "UPDATE loans SET dateReturned = ? WHERE bookId = ? AND dateReturned IS NULL";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, returnDate);
            ps.setInt(2, bookId);

            int affected = ps.executeUpdate();
            if (affected > 0) {
                System.out.println("Kitap geri teslim alındı.");
            } else {
                System.out.println("Bu kitabın aktif ödünç kaydı bulunamadı.");
            }

        } catch (SQLException e) {
            System.out.println("Kitap iadesinde hata: " + e.getMessage());
        }
    }
}
