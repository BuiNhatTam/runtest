package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Date;
import db.connectDB;
import entity.DatHang;
import entity.Sach;

public class DatHang_dao {

	private Connection con;
	private static DatHang_dao _instance;

	public DatHang_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static DatHang_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (DatHang_dao.class) {
				if (null == _instance) {
					_instance = new DatHang_dao();
				}
			}
		}
		return _instance;
	}
	//tìm
	public ArrayList<DatHang> timTheoMaPDH(String mph) throws SQLException {
		ArrayList<DatHang> pdh = new ArrayList<DatHang>();
		PreparedStatement preparedStatement = con.prepareStatement("Select * from PhieuDatHang where MaPhieuDatHang = ?");
		preparedStatement.setString(1, mph);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String mapdh = rs.getString(1);
			String kh = rs.getString(2);
			Date ngaydh = rs.getDate(3);
			DatHang sh = new DatHang(mapdh, kh, ngaydh);
			pdh.add(sh);
			
		}
		return pdh;
	}
	//Lấy tổng số phiếu đặt hàng
	public int laySoPhieuDatHang() {
		int response = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("select count(MaPhieuDatHang) from PhieuDatHang");
			response = rs.getHoldability();
			while (rs.next())
				response = rs.getInt(1);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return response;
	}
	//Thêm
	public boolean themPhieuDatHang(DatHang p){
		int n = 0;
		try {
			String sql = " insert into PhieuDathang values(?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMaDatHang());
			stmt.setString(2, p.getMaKhachHang());
			stmt.setDate(3, p.getNgayDatHang());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	/*
	 * xóa 
	 */
	public boolean xoaPhieuDatHang(String p){

		int n = 0;
		try {
			String sql = " delete from PhieuDatHang where MaPhieuDatHang = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	
	/*
	 * sửa
	 */
	public boolean suaPhieuDatHang(DatHang p){
		int n = 0;
		try {
			String sql = " update PhieuDatHang set makhachhang = ?, NgayDatHang = ?   where MaPhieuDatHang = ?";
			PreparedStatement stmt = con.prepareStatement(sql);		
			stmt.setString(3, p.getMaDatHang());
			stmt.setString(1, p.getMaKhachHang());
			stmt.setDate(2, p.getNgayDatHang());			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	/*
	 * lấy danh sách tất cả 
	 */
	public ArrayList<DatHang> layDsDatHang() throws Exception{
		ArrayList<DatHang> dsdh = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("Select *\r\n"
				+ "From PhieuDatHang");
		while (rs.next()) {
			String maDH = rs.getString(1);
			String maKH = rs.getString(2);
			Date ngayDH = rs.getDate(3);

			DatHang dh = new DatHang(maDH, maKH, ngayDH);
			dsdh.add(dh);
		}

		return dsdh;
	}
	public ArrayList<Sach> layDsTenSach() throws Exception {
		ArrayList<Sach> dsTenSach = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select MaSach,TenSach from Sach");
		
		while (rs.next()) {
			String tenSach = rs.getString(2);
			String maSach = rs.getString(1);			
			Sach sach = new Sach(maSach,tenSach, 0, 0);
			dsTenSach.add(sach);
		}
		rs.close();
		return dsTenSach;
	}
}
