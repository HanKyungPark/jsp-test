package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public UserDao() {
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


    public int login(String userID, String userPassword) {
        String SQL = "SELECT userPassword FROM User WHERE userID = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(userPassword)) {
                    return 1;//로그인 성공
                }
                else
                    return 0;//비밀번호 불일치
            }
            return -1; //아이디가 없음
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2;// 데이터베이스 오류
    }

    public int join(User user) {
        String SQL = "INSERT INTO User (userID,userPassword,userName,userGender,userEmail) VALUES (?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user.getUserID());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserGender());
            pstmt.setString(5, user.getUserEmail());
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
