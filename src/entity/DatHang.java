package entity;

import java.sql.Date;

public class DatHang {
	private String maDatHang;
	private Date ngayDatHang;
	private String maKhachHang;
	
	public DatHang() {
		super();
	}
	
	public DatHang(String maDatHang, String maKhachHang, Date ngayDatHang) {
		super();
		this.maDatHang = maDatHang;
		this.maKhachHang = maKhachHang;
		this.ngayDatHang = ngayDatHang;
	}

	public DatHang(String maDatHang) {
		super();
		this.maDatHang = maDatHang;
	}

	public String getMaDatHang() {
		return maDatHang;
	}
	public void setMaDatHang(String maDatHang) {
		this.maDatHang = maDatHang;
	}

	public Date getNgayDatHang() {
		return ngayDatHang;
	}
	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	@Override
	public String toString() {
		return "DatHang [maDatHang=" + maDatHang + ", ngayDatHang=" + ngayDatHang + ", maKhachHang=" + maKhachHang
				+ "]";
	}

}
