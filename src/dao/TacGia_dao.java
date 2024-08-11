package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import db.connectDB;
import entity.TacGia;
import entity.TheLoai;

public class TacGia_dao {
	private Connection con;
	private static TacGia_dao _instance;

	public TacGia_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static TacGia_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (TacGia_dao.class) {
				if (null == _instance) {
					_instance = new TacGia_dao();
				}
			}
		}
		return _instance;
	}


	// thêm tác giả
	public boolean themTacGia(TacGia tg) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("insert into TacGia values(?,?)");
		stmt.setString(1,tg.getMaTacGia());
		stmt.setString(2, tg.getTenTacgia());
		n = stmt.executeUpdate();
		
		return n > 0;
	}
	// sửa thông tin tác giả
	public boolean updateTacGia(TacGia tg) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("update TacGia set TenTG = ? where MaTG=?");
		stmt.setString(1, tg.getTenTacgia());
		stmt.setString(2, tg.getMaTacGia());
		n = stmt.executeUpdate();

		return n > 0;
	}
	
	// xóa tác giả
	public boolean xoaTacGiaTheoMa(String maTacGia) throws Exception {
		
		PreparedStatement stmt = con.prepareStatement("delete TacGia where MaTG=?");
		stmt.setString(1, maTacGia);
		int n = stmt.executeUpdate();

		return n > 0;
	}

	
	public ArrayList<TacGia> layDsTacGia() throws SQLException {
		ArrayList<TacGia> dsTacGia = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from TacGia");
		while (rs.next()) {
			String maTacGia = rs.getString(1);
			String tenTacGia = rs.getString(2);
			TacGia tacgia =  new TacGia(maTacGia, tenTacGia);
			dsTacGia.add(tacgia);
		}
		return dsTacGia;
	}
}
