package entity;

import java.sql.Date;

public class HoaDon {
	private String maHoaDon;
	private String maNhanVien;
	private String maKhachHang;
	private String tenHoaDon;
	private Date ngayLapHoaDon;
	public HoaDon() {
		super();
	}
	public HoaDon(String maHoaDon, String maNhanVien, String maKhachHang, String tenHoaDon, Date ngayLapHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.tenHoaDon = tenHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenHoaDon() {
		return tenHoaDon;
	}
	public void setTenHoaDon(String tenHoaDon) {
		this.tenHoaDon = tenHoaDon;
	}
	public Date getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}
	public void setNgayLapHoaDon(Date ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang
				+ ", tenHoaDon=" + tenHoaDon + ", ngayLapHoaDon=" + ngayLapHoaDon + "]";
	}
}
