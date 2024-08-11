package dao;

import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import db.connectDB;
import entity.ChiTietDatHang;

public class ChiTietDatHang_dao {
	private ArrayList<ChiTietDatHang> ds;
	private Connection con;
	private static ChiTietDatHang_dao _instance;

	public ChiTietDatHang_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static ChiTietDatHang_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (ChiTietDatHang_dao.class) {
				if (null == _instance) {
					_instance = new ChiTietDatHang_dao();
				}
			}
		}
		return _instance;
	}
	//Thêm
	public boolean themCTPhieuDatHang(ChiTietDatHang p) throws SQLException {

		PreparedStatement stmt = null;
		int n = 0;
		stmt = con.prepareStatement("insert into ChiTietPhieuDatHang values(?,?,?,?)");
		stmt.setString(1, p.getMaDatHang());
		stmt.setString(2, p.getMaSach());
		stmt.setInt(3, p.getSoLuong());
		stmt.setDouble(4, p.getDonGia());
		n = stmt.executeUpdate();
		return n > 0;
	}
	//Xóa
	public boolean xoaCTPhieuDatHang(String maph) throws SQLException {
		int n = 0;
		String sql = " delete from ChiTPhieuDatHang where MaPhieuDatHang = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maph);
		n = stmt.executeUpdate();

		return n > 0;
	}
	//Sửa
	public boolean suaCTPhieuDatHang(ChiTietDatHang p) throws SQLException {

		int n = 0;

		String sql = " update ChiTietPhieuDatHang set SoLuong = ? where where MaPhieuDatHang = ? and MaSach = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, p.getSoLuong());
		stmt.setString(2, p.getMaDatHang());
		stmt.setString(3, p.getMaSach());
		n = stmt.executeUpdate();
		return n > 0;
	}
	//Lấy danh sách
	public ArrayList<ChiTietDatHang> layDsCTPhieuDatHang() throws SQLException {
		ds = new ArrayList<ChiTietDatHang>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from ChiTietPhieuDatHang");
		while (rs.next()) {
			ChiTietDatHang kh = new ChiTietDatHang(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
			ds.add(kh);
		}
		return ds;
	}

	// tìm
	public ArrayList<ChiTietDatHang> timTheoMaCTDH(String mph) throws SQLException {
		ArrayList<ChiTietDatHang> ctdh = new ArrayList<ChiTietDatHang>();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM ChiTietPhieuDatHang where MaPhieuDatHang = ?");
		preparedStatement.setString(1, mph);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String mapdh = rs.getString(1);
			String maSach = rs.getString(2);
			int soLuuong = rs.getInt(3);
			Double donGia = rs.getDouble(4);
			ChiTietDatHang ct = new ChiTietDatHang(mapdh, maSach, soLuuong, donGia);
			ctdh.add(ct);

		}
		return ctdh;
	}

}
