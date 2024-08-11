package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import db.connectDB;
import entity.HoaDon;

public class HoaDon_Dao {
	private Connection con;
	private static HoaDon_Dao _instance;

	public HoaDon_Dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static HoaDon_Dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (HoaDon_Dao.class) {
				if (null == _instance) {
					_instance = new HoaDon_Dao();
				}
			}
		}
		return _instance;
	}
	
	// Thêm
	public boolean themHD(HoaDon hd) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("insert into HoaDon values(?,?,?,?,?)");
		stmt.setString(1, hd.getMaHoaDon());
		stmt.setString(2, hd.getMaNhanVien());
		stmt.setString(3, hd.getMaKhachHang());
		stmt.setString(4, hd.getTenHoaDon());
		stmt.setDate(5, hd.getNgayLapHoaDon());
		
		n = stmt.executeUpdate();

		return n > 0;
	}
	
//	Xóa
	public boolean xoaHD(String maHD) throws Exception {
		
		PreparedStatement stmt = con.prepareStatement("delete HoaDon where MaHoaDon=?");
		stmt.setString(1, maHD);
		int n = stmt.executeUpdate();

		return n > 0;
	}
	//Lấy danh sách
	public ArrayList<HoaDon> layDsHoaDon() throws SQLException {
		ArrayList<HoaDon> dshd = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from HoaDon");
		while (rs.next()) {
			String maHD = rs.getString(1);
			String maNV = rs.getString(2);
			String maKH = rs.getString(3);
			String tenHD = rs.getString(4);
			Date ngayLapHD = rs.getDate(5);

			HoaDon hd = new HoaDon(maHD, maKH, maNV, tenHD, ngayLapHD);
			dshd.add(hd);
		}

		return dshd;

	}
	//Lấy tổng số hóa đơn
	public int laySoHoaDon() {
		int response = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("select count(MaHoaDon) from HoaDon");
			response = rs.getHoldability();
			while (rs.next())
				response = rs.getInt(1);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return response;
	}
	public Object[] timHoaDonById(String maHD) throws SQLException {

		String sql = "SELECT HoaDon.MaHoaDon,HoaDon.MaKH,KhachHang.TenKH, KhachHang.SDT, HoaDon.MaNV,NhanVien.TenNV,HoaDon.NgayXuatHD, SUM(ChiTietHoaDon.SoLuong * ChiTietHoaDon.DonGia) AS TongTien\r\n"
				+ "FROM     HoaDon INNER JOIN\r\n"
				+ "                  KhachHang ON HoaDon.MaKH = KhachHang.MaKH INNER JOIN\r\n"
				+ "                  NhanVien ON HoaDon.MaNV = NhanVien.MaNV INNER JOIN\r\n"
				+ "                  ChiTietHoaDon ON HoaDon.MaHoaDon = ChiTietHoaDon.MaHoaDon\r\n"
				+ "					 where HoaDon.MaHoaDon= ?\r\n"
				+ "					 GROUP BY HoaDon.MaHoaDon,HoaDon.MaKH,KhachHang.TenKH, KhachHang.SDT, HoaDon.MaNV,NhanVien.TenNV,HoaDon.NgayXuatHD";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maHD);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Object[] o = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8)};

			return o;

		}
		return null;
	}

	public List<Object> getAllOrderBySDT(String sdt, Object object) {
		List<Object> response = new ArrayList<Object>();
		String sql = "SELECT HoaDon.MaHoaDon,HoaDon.MaKH,KhachHang.TenKH, KhachHang.SDT, HoaDon.MaNV,NhanVien.TenNV,HoaDon.NgayXuatHD, SUM(ChiTietHoaDon.SoLuong * ChiTietHoaDon.DonGia) AS TongTien\r\n"
				+ "FROM     HoaDon INNER JOIN\r\n"
				+ "                  KhachHang ON HoaDon.MaKH = KhachHang.MaKH INNER JOIN\r\n"
				+ "                  NhanVien ON HoaDon.MaNV = NhanVien.MaNV INNER JOIN\r\n"
				+ "                  ChiTietHoaDon ON HoaDon.MaHoaDon = ChiTietHoaDon.MaHoaDon\r\n"
				+ "					 where KhachHang.SDT = ?\r\n"
				+ "					 GROUP BY HoaDon.MaHoaDon,HoaDon.MaKH,KhachHang.TenKH, KhachHang.SDT, HoaDon.MaNV,NhanVien.TenNV,HoaDon.NgayXuatHD";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sdt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Object[] o = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8)};
				response.add(o);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return response;
	}
	
	public List<Object> getAllOrderByDate(java.sql.Date ngayLap) throws ParseException {

		List<Object> response = new ArrayList<Object>();

		String sql = "SELECT HoaDon.MaHoaDon,HoaDon.MaKH,KhachHang.TenKH, KhachHang.SDT, HoaDon.MaNV,NhanVien.TenNV,HoaDon.NgayXuatHD, SUM(ChiTietHoaDon.SoLuong * ChiTietHoaDon.DonGia)\r\n"
				+ "FROM     HoaDon INNER JOIN\r\n"
				+ "                  KhachHang ON HoaDon.MaKH = KhachHang.MaKH INNER JOIN\r\n"
				+ "                  NhanVien ON HoaDon.MaNV = NhanVien.MaNV INNER JOIN\r\n"
				+ "                  ChiTietHoaDon ON HoaDon.MaHoaDon = ChiTietHoaDon.MaHoaDon\r\n"
				+ "					 where NgayXuatHD = ?\r\n"
				+ "					 GROUP BY HoaDon.MaHoaDon,HoaDon.MaKH,KhachHang.TenKH, KhachHang.SDT, HoaDon.MaNV,NhanVien.TenNV,HoaDon.NgayXuatHD";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setDate(1, ngayLap);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Object[] o = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8)};
				response.add(o);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return response;
	}
	
	public HoaDon timHoaDon(String maHoaDon) {
		HoaDon hoaDon = null;
		String sql = "Select * from HoaDon where maHoaDon = ?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maHoaDon);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String maHD = rs.getString(1);
				String maNV = rs.getString(2);
				String maKH = rs.getString(3);
				String tenHD = rs.getString(4);
				Date ngayLapHD = rs.getDate(5);
				
				hoaDon = new HoaDon(maHD, maKH, maNV, tenHD, ngayLapHD);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return hoaDon;
	}
	
	
}
