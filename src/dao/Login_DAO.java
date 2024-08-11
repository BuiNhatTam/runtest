package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.connectDB;

public class Login_DAO {
	public Login_DAO() {
		
	}
    public static boolean validate(String username, String password) {
        boolean status = false;
        try {
        	Connection con = connectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM [Account] WHERE username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

            con.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return status;
    }
}

