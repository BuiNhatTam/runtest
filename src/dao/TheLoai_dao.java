package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import db.connectDB;
import entity.TheLoai;

public class TheLoai_dao {
	private Connection con;
	private static TheLoai_dao _instance;

	public TheLoai_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static TheLoai_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (TheLoai_dao.class) {
				if (null == _instance) {
					_instance = new TheLoai_dao();
				}
			}
		}
		return _instance;
	}
	// lay het ds the loai
	public ArrayList<TheLoai> layDsTheLoai() throws SQLException {
		ArrayList<TheLoai> dsSach = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from TheLoai");
		while (rs.next()) {
			String maTheLoai = rs.getString(1);
			String tenTheLoai = rs.getString(2);
			TheLoai theloai =  new TheLoai(maTheLoai,tenTheLoai);
			dsSach.add(theloai);
		}
		return dsSach;
	}
	
	// thêm thể loại
	public boolean themTheLoai(TheLoai tl) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;
		stmt = con.prepareStatement("insert into TheLoai values(?,?)");
		stmt.setString(1,tl.getMaTheLoai());
		stmt.setString(2, tl.getTenTheLoai());
		n = stmt.executeUpdate();		
		return n > 0;
	}
	// sửa thông tin thể loại
	public boolean updateTheLoai(TheLoai tl) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("update TheLoai set TenTheLoai = ? where MaTheLoai=?");
		stmt.setString(1, tl.getTenTheLoai());
		stmt.setString(2, tl.getMaTheLoai());
		n = stmt.executeUpdate();

		return n > 0;
	}
	
	// xóa thể loại
	public boolean xoaTheLoai(String maTheLoai) throws Exception {
		
		PreparedStatement stmt = con.prepareStatement("delete TheLoai where MaTheLoai=?");
		stmt.setString(1, maTheLoai);
		int n = stmt.executeUpdate();

		return n > 0;
	}

}
