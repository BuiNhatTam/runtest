package entity;

public class ChiTietHoaDon {
	private String maHoaDon;
	private String maSach;
	private int soLuong;
	private double donGia;
	
	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(String maHoaDon, String maSach, int soLuong, double donGia) {
		super();
		this.maHoaDon = maHoaDon;
		this.maSach = maSach;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
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
	
	public double thanhTien() {
		return getDonGia() * getSoLuong();
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [maHoaDon=" + maHoaDon + ", maSach=" + maSach + ", soLuong=" + soLuong + "sonGia=" + donGia +"]";
	}
}
