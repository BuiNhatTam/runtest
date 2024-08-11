package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.connectDB;
import entity.NhaXuatBan;

public class NhaXuatBan_dao {
	private Connection con;
	private static NhaXuatBan_dao _instance;

	public NhaXuatBan_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static NhaXuatBan_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (NhaXuatBan_dao.class) {
				if (null == _instance) {
					_instance = new NhaXuatBan_dao();
				}
			}
		}
		return _instance;
	}

	// thêm nhà xuất bản
	public boolean themNhaXuatBan(NhaXuatBan nxb) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("insert into NhaXuatBan values(?,?)");
		stmt.setString(1,nxb.getMaNhaXuatBan());
		stmt.setString(2, nxb.getTenNhaXuatBan());
		n = stmt.executeUpdate();
		
		return n > 0;
	}
	// sửa thông tin nha xuat ban
	public boolean updateNhaXuatBan(NhaXuatBan nxb) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("update NhaXuatBan set TenNXB = ? where MaNXB=?");
		stmt.setString(1, nxb.getTenNhaXuatBan());
		stmt.setString(2, nxb.getMaNhaXuatBan());
		n = stmt.executeUpdate();

		return n > 0;
	}
	
	// xóa nha xuat ban
	public boolean xoaNhaXuatBan(String maNhaXuatBan) throws Exception {
		
		PreparedStatement stmt = con.prepareStatement("delete NhaXuatBan where MaNXB=?");
		stmt.setString(1, maNhaXuatBan);
		int n = stmt.executeUpdate();

		return n > 0;
	}
	
	public ArrayList<NhaXuatBan> layDsNXB() throws SQLException {
		ArrayList<NhaXuatBan> dsNXB = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from NhaXuatBan");
		while (rs.next()) {
			String maNXB = rs.getString(1);
			String tenNXB = rs.getString(2);
			NhaXuatBan nxb =  new NhaXuatBan(maNXB, tenNXB);
//			TheLoai theloai = new TheLoai(tenTheLoai);
			dsNXB.add(nxb);
		}
		return dsNXB;
	}

}
