package entity;

public class ChiTietDatHang {
	private String maDatHang;
	private String maSach;
	private int soLuong;
	private double donGia;
	
	public ChiTietDatHang() {
		super();
	}

	public ChiTietDatHang(String maDatHang, String maSach, int soLuong, double donGia) {
		super();
		this.maDatHang = maDatHang;
		this.maSach = maSach;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public String getMaDatHang() {
		return maDatHang;
	}

	public void setMaDatHang(String maDatHang) {
		this.maDatHang = maDatHang;
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	@Override
	public String toString() {
		return "ChiTietDatHang [maDatHang=" + maDatHang + ", maSach=" + maSach + ", soLuong=" + soLuong + ", donGia="
				+ donGia + "]";
	}
	
	
}
