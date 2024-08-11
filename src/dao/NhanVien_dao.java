package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import db.connectDB;
import entity.NhanVien;

public class NhanVien_dao {
	private Connection con;
	private static NhanVien_dao _instance;

	public NhanVien_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static NhanVien_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (NhanVien_dao.class) {
				if (null == _instance) {
					_instance = new NhanVien_dao();
				}
			}
		}
		return _instance;
	}

//	TÌM NHÂN VIÊN
	// Tìm Nhân viên theo Mã NV,SDT,Tên
	public ArrayList<NhanVien> searchNhanVien(NhanVien nhanvien){
		ArrayList<NhanVien> ls = new ArrayList<NhanVien>();
		String sql = "select * from NhanVien";
		String where = "";
		try {
			if(nhanvien.getMaNV()!=null) {
				where += "MaNV like ? AND ";
			}
			if(nhanvien.getSoDT()!=null) {
				where += "SDT = ? AND ";
			}
			if(nhanvien.getTenNV()!=null) {
				where += "TenNV like ? AND ";
			}
			if(where.length()>0) {
				sql += " where "+where.substring(0,where.length()-5);
			}

			PreparedStatement ps = con.prepareStatement(sql);
			int i =1;
			if(nhanvien.getMaNV()!=null) {
				ps.setString(i++, "%"+nhanvien.getMaNV()+"%");
			}
			if(nhanvien.getSoDT()!=null) {
				ps.setString(i++, nhanvien.getSoDT());
			}
			if(nhanvien.getTenNV()!=null) {
				ps.setString(i++, "%"+nhanvien.getTenNV()+"%");
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString(1));
				nv.setTenNV(rs.getString(2));
				nv.setSoDT(rs.getString(3));
				nv.setDiaChi(rs.getString(4));
				nv.setHeSoLuong(rs.getFloat(5));
				nv.setGioiTinh(rs.getBoolean(6));
				nv.setNgaySinh(rs.getDate(7));
				nv.setNgayVaoLam(rs.getDate(8));
				
				ls.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
	public ArrayList<NhanVien> searchNhanVienTheoProperties(String ma,String sdt,String tenString){
		NhanVien nv = new NhanVien();
		nv.setMaNV(ma);
		nv.setSoDT(sdt);
		nv.setTenNV(tenString);
		NhanVien_dao dao = new NhanVien_dao();
		return dao.searchNhanVien(nv);
	}

	//Thêm
	public boolean themNV(NhanVien nv) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?,?)");
		stmt.setString(1, nv.getMaNV());
		stmt.setString(2, nv.getTenNV());
		stmt.setString(3, nv.getSoDT());
		stmt.setString(4, nv.getDiaChi());
		stmt.setFloat(5, nv.getHeSoLuong());
		stmt.setBoolean(6, nv.isGioiTinh());
		stmt.setDate(7, nv.getNgaySinh());
		stmt.setDate(8, nv.getNgayVaoLam());
		n = stmt.executeUpdate();

		return n > 0;
	}

	//Sửa
	public boolean update(NhanVien nv) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement(
				"update NhanVien set TenNV=?,SDT=?,DiaChi=?,HeSoLuong=?,GioiTinh=?,NgaySinh=?,NgayVaoLam=? where MaNV=?");
		stmt.setString(1, nv.getTenNV());
		stmt.setString(2, nv.getSoDT());
		stmt.setString(3, nv.getDiaChi());
		stmt.setFloat(4, nv.getHeSoLuong());
		stmt.setBoolean(5, nv.isGioiTinh());
		stmt.setDate(6, nv.getNgaySinh());
		stmt.setDate(7, nv.getNgayVaoLam());
		stmt.setString(8, nv.getMaNV());

		n = stmt.executeUpdate();

		return n > 0;
	}

	//Xóa
	public boolean xoaNhanVienTheoMa(String maNV) throws Exception {

		PreparedStatement stmt = con.prepareStatement("delete NhanVien where MaNV=?");
		stmt.setString(1, maNV);
		int n = stmt.executeUpdate();

		return n > 0;
	}
	public NhanVien timNVTheoMa(String _maNV) throws SQLException {

		PreparedStatement preparedStatement = con.prepareStatement("select * from NhanVien where MaNV = ?");
		preparedStatement.setString(1, _maNV);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String maNV = rs.getString(1);
			String tenNV = rs.getString(2);
			String soDT = rs.getString(3);
			String diaChi = rs.getString(4);
			float heSoLuong = rs.getFloat(5);
			boolean gioiTinh = rs.getBoolean(6);
			Date ngaySinh = rs.getDate(7);
			Date ngayVaoLam = rs.getDate(8);

			NhanVien nv = new NhanVien(maNV, tenNV, soDT, diaChi, heSoLuong, gioiTinh, ngaySinh, ngayVaoLam);

			return nv;
		}

		return null;
	}

	//Lấy danh sách nhân viên
	public ArrayList<NhanVien> layDsNhanVien() throws Exception {
		ArrayList<NhanVien> dsnv = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from NhanVien");
		while (rs.next()) {
			String maNV = rs.getString(1);
			String tenNV = rs.getString(2);
			String soDT = rs.getString(3);
			String diaChi = rs.getString(4);
			float heSoLuong = rs.getFloat(5);
			boolean gioiTinh = rs.getBoolean(6);
			Date ngaySinh = rs.getDate(7);
			Date ngayVaoLam = rs.getDate(8);

			NhanVien nv = new NhanVien(maNV, tenNV, soDT, diaChi, heSoLuong, gioiTinh, ngaySinh, ngayVaoLam);
			dsnv.add(nv);
		}

		return dsnv;

	}

}
