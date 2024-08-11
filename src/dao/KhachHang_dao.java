package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import db.connectDB;
import entity.KhachHang;

public class KhachHang_dao {
	private Connection con;
	private static KhachHang_dao _instance;

	public KhachHang_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static KhachHang_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (KhachHang_dao.class) {
				if (null == _instance) {
					_instance = new KhachHang_dao();
				}
			}
		}
		return _instance;
	}

//	TÌM KHÁCH HÀNG
	//Tìm Khách hàng theo mã hoặc SDT hoạc tên 
	public ArrayList<KhachHang> searchKhachHang(KhachHang kh){
    	ArrayList<KhachHang> ls = new ArrayList<KhachHang>();
    	try {

			String sql = "SELECT * FROM KhachHang";
            String where = "";
			if (kh.getMaKH() != null) {
                where += "MaKH like ? AND ";
            }

            if (kh.getSoDT() != null) {
                where += "SDT =? AND ";
            }
            if (kh.getTenKH() !=null) {
            	where += "TenKH like ? AND ";
            }
            if (where.length() > 0) {
                sql += " WHERE " + where.substring(0, where.length() - 5);
            }
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = 1;
            if (kh.getMaKH() != null) {
                stmt.setString(i++, "%" + kh.getMaKH() + "%");
            }
            if (kh.getSoDT() != null) {
                stmt.setString(i++, kh.getSoDT());
            }
            if (kh.getTenKH() != null) {
                stmt.setString(i++, "%" + kh.getTenKH() + "%");
            }
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	KhachHang s = new KhachHang();
            	s.setMaKH(rs.getString(1));
            	s.setTenKH(rs.getString(2));
            	s.setSoDT(rs.getString(3));
            	s.setDiaChi(rs.getString(4));
            	s.setGioiTinh(rs.getBoolean(5));
            	ls.add(s);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ls;
    }
	public ArrayList<KhachHang> searchKhachHang(String ma,String sdt,String ten){
		KhachHang kh = new KhachHang();
		kh.setMaKH(ma);
		kh.setSoDT(sdt);
		kh.setTenKH(ten);
		KhachHang_dao dao = new KhachHang_dao();
		return dao.searchKhachHang(kh);
	}
	public KhachHang getKhachHangBySDT(String soDienThoai) {
		KhachHang khachHang = null;
		try {

			String sql = "select * from KhachHang where SDT = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, soDienThoai);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String soDT = rs.getString(3);
				String diaChi = rs.getString(4);
				Boolean gioiTinh = rs.getBoolean(5);
				khachHang = new KhachHang(maKH, tenKH, soDT, diaChi, gioiTinh);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return khachHang;
	}
	//tìm theo mã
	public KhachHang searchMaKhachHang(String ma){
    	KhachHang kh = new KhachHang();
    	try {

			String sql = "SELECT * FROM KhachHang where MaKH = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	String MaKH = rs.getString(1);
            	String tenKH = rs.getString(2);
            	String soDienThoai = rs.getString(3);
            	String diaChi = rs.getString(4);
            	Boolean gioiTinh = rs.getBoolean(5);
            	kh = new KhachHang(MaKH, tenKH, soDienThoai, diaChi, gioiTinh);
            	return kh;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}
// 	thêm
	public boolean themKH(KhachHang kh) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("insert into KhachHang values(?,?,?,?,?)");
		stmt.setString(1, kh.getMaKH());
		stmt.setString(2, kh.getTenKH());
		stmt.setString(3, kh.getSoDT());
		stmt.setString(4,kh.getDiaChi());
		stmt.setBoolean(5, kh.isGioiTinh());

		n = stmt.executeUpdate();

		return n > 0;
	}

//	sửa
	public boolean update(KhachHang kh) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("update KhachHang set TenKH=?,SDT=?,DiaChi = ?,GioiTinh=? where MaKH=?");
		stmt.setString(1, kh.getTenKH());
		stmt.setString(2, kh.getSoDT());
		stmt.setString(3, kh.getDiaChi());
		stmt.setBoolean(4, kh.isGioiTinh());
		stmt.setString(5, kh.getMaKH());
		n = stmt.executeUpdate();

		return n > 0;
	}

//	xóa
	public boolean xoaKhachHangTheoMa(String maKH) throws Exception {
		
		PreparedStatement stmt = con.prepareStatement("delete KhachHang where MaKH=?");
		stmt.setString(1, maKH);
		int n = stmt.executeUpdate();

		return n > 0;
	}

//	Lấy danh sách
	public ArrayList<KhachHang> layDsKhachHang() throws SQLException {
		ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from KhachHang");
		while (rs.next()) {
			String maKH = rs.getString(1);
			String tenKH = rs.getString(2);
			String soDT = rs.getString(3);
			String diaChi = rs.getString(4);
			boolean gioiTinh = rs.getBoolean(5);
			
			KhachHang khachHang = new KhachHang(maKH, tenKH, soDT, diaChi, gioiTinh);
			dsKhachHang.add(khachHang);
		}

		return dsKhachHang;

	}
	 //lấy danh sách mã khách hàng
	public ArrayList<KhachHang> layDsMaKhachHang() throws SQLException {
		ArrayList<KhachHang> dsMaKhachHang = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select MaKH from KhachHang");
		while (rs.next()) {
			String maKH = rs.getString(1);

			KhachHang khachHang = new KhachHang(maKH);
			dsMaKhachHang.add(khachHang);
		}

		return dsMaKhachHang;
	}
}
