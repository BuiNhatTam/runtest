package entity;

public class Sach {
	private String maSach;
	private String tenSach;
	private int soLuong;
	private double donGia;
	private String maTacGia;
	private String maNXB;
	private String maTheLoai;
	
	public Sach() {
		super();
	}
	public Sach(String maSach, String tenSach, int soLuong, double donGia, String maTacGia, String maNXB,
			String maTheLoai) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.maTacGia = maTacGia;
		this.maNXB = maNXB;
		this.maTheLoai = maTheLoai;
	}

	public Sach(String maSach, String tenSach, int soLuong, double donGia) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}
	public Sach(String maSach) {
		super();
		this.setMaSach(maSach);
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
		
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
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
	
	public String getMaTacGia() {
		return maTacGia;
	}
	public void setMaTacGia(String maTacGia) {
		this.maTacGia = maTacGia;
	}
	public String getMaNXB() {
		return maNXB;
	}
	public void setMaNXB(String maNXB) {
		this.maNXB = maNXB;
	}
	public String getMaTheLoai() {
		return maTheLoai;
	}
	public void setMaTheLoai(String maTheLoai) {
		this.maTheLoai = maTheLoai;
	}
	@Override
	public String toString() {
		return "Sach [maSach=" + maSach + ", tenSach=" + tenSach + ", soLuong=" + soLuong + ", donGia=" + donGia
				+ ", maTacGia=" + maTacGia + ", maNXB=" + maNXB + ", maTheLoai=" + maTheLoai + "]";
	}

	
}
