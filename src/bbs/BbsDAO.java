package bbs;

import java.sql.*;

public class BbsDAO {
    private Connection conn;
    private ResultSet rs;

    public BbsDAO() {
        try {
            String dbURL = "jdbc:mariadb://localhost:3306/BBS";
            String dbID = "root";
            String dbPassword = "1234";
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            System.out.println("DB 연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB 연결 실패");
        }
    }

    // 현재 날짜 및 시간 반환 (Timestamp 형식)
    public Timestamp getDate() {
        String SQL = "SELECT NOW()";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp(1);  // Timestamp로 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // 오류 발생 시 null 반환
    }

    public int getNext() {
        String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1; // 첫 번째 게시물인 경우
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    public int write(String bbsTitle, String userID, String bbsContent) {
        if (userID == null || userID.trim().isEmpty()) {
            System.out.println("userID가 null이거나 비어 있습니다.");
            return -1;
        }

        String SQL = "INSERT INTO BBS (bbsID, bbsTitle, userID, bbsDate, bbsContent, bbsAvailable) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext());
            pstmt.setString(2, bbsTitle);
            pstmt.setString(3, userID);
            pstmt.setTimestamp(4, getDate()); // 수정된 getDate() 사용
            pstmt.setString(5, bbsContent);
            pstmt.setInt(6, 1);

            int result = pstmt.executeUpdate(); // executeUpdate() 사용
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }
}
