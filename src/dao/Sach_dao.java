package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import db.connectDB;
import entity.Sach;

public class Sach_dao {
	private Connection con;
	private Map<String, Sach> bookMap;
	private static Sach_dao _instance;

	public Sach_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static Sach_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (Sach_dao.class) {
				if (null == _instance) {
					_instance = new Sach_dao();
				}
			}
		}
		return _instance;
	}

// Lấy ds sách
	public ArrayList<Sach> layDsSach() throws SQLException {
		ArrayList<Sach> dsSach = new ArrayList<>();
		Statement statement = con.createStatement();

		ResultSet rs = statement.executeQuery("select s.MaSach, s.TenSach, s.SoLuong, s.DonGia, tg.TenTG, nxb.TenNXB, tl.TenTheLoai\r\n"
				+ "from Sach s\r\n"
				+ "JOIN TheLoai tl\r\n"
				+ "	ON s.MaTheLoai = tl.MaTheLoai\r\n"
				+ "JOIN TacGia tg\r\n"
				+ "	ON s.MaTG = tg.MaTG\r\n"
				+ "JOIN NhaXuatBan nxb\r\n"
				+ "	ON s.MaNXB = nxb.MaNXB");
		while (rs.next()) {
			String maSach = rs.getString(1);
			String tenSach = rs.getString(2);
			int soLuong = rs.getInt(3);
			double donGia = rs.getDouble(4);
			String tacgia = rs.getString(5);
			String nhaxuatban = rs.getString(6);
			String theloai = rs.getString(7);
			Sach sach = new Sach(maSach,tenSach,soLuong,donGia,tacgia,nhaxuatban,theloai);
			dsSach.add(sach);
		}
		return dsSach;

	}

	
	// tìm sách
	public ArrayList<Sach> searchSach(Sach sach){
		ArrayList<Sach> ls = new ArrayList<Sach>();
		String sql = "select s.MaSach, s.TenSach, s.SoLuong, s.DonGia, tg.TenTG, nxb.TenNXB, tl.TenTheLoai\r\n"
				+ "from Sach s\r\n"
				+ "JOIN TheLoai tl\r\n"
				+ "	ON s.MaTheLoai = tl.MaTheLoai\r\n"
				+ "JOIN TacGia tg\r\n"
				+ "	ON s.MaTG = tg.MaTG\r\n"
				+ "JOIN NhaXuatBan nxb\r\n"
				+ "	ON s.MaNXB = nxb.MaNXB";
		String where = "";
		try {
			if(sach.getMaSach()!=null) {
				where += "s.MaSach like ? AND ";
			}
			if(sach.getTenSach()!=null) {
				where += "s.TenSach like ? AND ";
			}
			if(sach.getMaTacGia()!=null) {
				where += "tg.TenTG like ? AND ";
			}
			if(sach.getMaNXB()!=null) {
				where += "nxb.TenNXB like ? AND ";
			}
			if(where.length()>0) {
				sql += " where "+where.substring(0,where.length()-5);
			}

			PreparedStatement ps = con.prepareStatement(sql);
			int i =1;
			if(sach.getMaSach()!=null) {
				ps.setString(i++, "%"+sach.getMaSach());
			}
			if(sach.getTenSach()!=null) {
				ps.setString(i++, sach.getTenSach()+"%");
			}
			if(sach.getMaTacGia()!=null) {
				ps.setString(i++, sach.getMaTacGia()+"%");
			}
			if(sach.getMaNXB()!=null) {
				ps.setString(i++, sach.getMaNXB()+"%");
			}

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Sach s = new Sach();
				s.setMaSach(rs.getString(1));
				s.setTenSach(rs.getString(2));
				s.setSoLuong(rs.getInt(3));
				s.setDonGia(rs.getDouble(4));
				s.setMaTacGia(rs.getString(5));
				s.setMaNXB(rs.getString(6));
				s.setMaTheLoai(rs.getString(7));
				ls.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
	public ArrayList<Sach> searchSachTheoProperties(String ma,String ten,String tacgia,String nhaxuatban){
		Sach s = new Sach();
		s.setMaSach(ma);
		s.setTenSach(ten);
		s.setMaTacGia(tacgia);
		s.setMaNXB(nhaxuatban);
		Sach_dao dao = new Sach_dao();
		return dao.searchSach(s);
	}
		
	
	//thêm  sách
	public boolean themSach(Sach sh) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;
		stmt = con.prepareStatement("insert into Sach values(?,?,?,?,?,?,?)");
		stmt.setString(1, sh.getMaSach());
		stmt.setString(2, sh.getTenSach());
		stmt.setInt(3, sh.getSoLuong());
		stmt.setDouble(4, sh.getDonGia());
		stmt.setString(5, sh.getMaTacGia());
		stmt.setString(6, sh.getMaNXB());
		stmt.setString(7, sh.getMaTheLoai());
		
		n = stmt.executeUpdate();
		
		return n > 0;
	}
	// sửa thông tin sách 
	public boolean updateSach(Sach sh) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("update Sach set TenSach=?,SoLuong=?,DonGia=?,MaTG = ?,MaNXB = ?,MaTheLoai =? where MaSach=?");
		stmt.setString(7, sh.getMaSach());
		stmt.setString(1, sh.getTenSach());
		stmt.setInt(2, sh.getSoLuong());
		stmt.setDouble(3, sh.getDonGia());
		stmt.setString(4, sh.getMaTacGia());
		stmt.setString(5, sh.getMaNXB());
		stmt.setString(6, sh.getMaTheLoai());
		n = stmt.executeUpdate();

		return n > 0;
	}
	
	// xóa
	public boolean xoaSachTheoMa(String maSach) throws Exception {
		
		PreparedStatement stmt = con.prepareStatement("delete Sach where MaSach=?");
		stmt.setString(1, maSach);
		int n = stmt.executeUpdate();
		return n > 0;
	}
	
	//tìm theo mã
	public Sach timSachTheoMaSach(String ma){
    	Sach s = new Sach();
    	try {

			String sql = "SELECT * FROM Sach where MaSach = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	String maSach = rs.getString(1);
            	String tenSach = rs.getString(2);
            	int soLuong = rs.getInt(3);
            	Double donGia = rs.getDouble(4);
            	s = new Sach(maSach, tenSach, soLuong, donGia);
            	return s;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}	
	public Sach timSachTheoTenSach(String ma){
    	Sach s = new Sach();
    	try {

			String sql = "SELECT * FROM Sach where TenSach = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	String maSach = rs.getString(1);
            	String tenSach = rs.getString(2);
            	int soLuong = rs.getInt(3);
            	Double donGia = rs.getDouble(4);
            	s = new Sach(maSach, tenSach, soLuong, donGia);
            	return s;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}	
	
	
}
