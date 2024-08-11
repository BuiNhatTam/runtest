package entity;

import java.sql.Date;

public class NhanVien {
	private String maNV, tenNV, soDT, diaChi;
	private boolean gioiTinh;
	private float heSoLuong;
	private Date ngaySinh, ngayVaoLam;

	public String getMaNV() {
		return maNV;
	}

	public String getMaNV(String maNV) {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public float getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;

	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) throws Exception {
		Date date = new java.sql.Date(System.currentTimeMillis());
		int age = date.toLocalDate().getYear() - ngaySinh.toLocalDate().getYear();
		if (age >= 18)
			this.ngaySinh = ngaySinh;
		else
			throw new Exception("Nhân viên chưa đủ 18 tuổi");
	}

	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}




	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	/**
	 * 
	 * @param maNV
	 * @param tenNV
	 * @param soDT
	 * @param diaChi
	 * @param heSoLuong
	 * @param ngaySinh
	 * @param ngayVaoLam
	 * @param gioiTinh
	 */

	public NhanVien(String maNV, String tenNV, String soDT, String diaChi, float heSoLuong, boolean gioiTinh,
			Date ngaySinh, Date ngayVaoLam) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.soDT = soDT;
		this.diaChi = diaChi;
		this.heSoLuong = heSoLuong;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.ngayVaoLam = ngayVaoLam;
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", soDT=" + soDT + ", diaChi=" + diaChi + ", heSoLuong="
				+ heSoLuong + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", ngayVaoLam=" + ngayVaoLam + "]"
				+ "\n";
	}

}