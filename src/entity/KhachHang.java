package entity;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String soDT;
	private String diaChi;
	private boolean gioiTinh;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public KhachHang(String maKH, String tenKH, String soDT, String diaChi, boolean gioiTinh) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.soDT = soDT;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
	}
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}
	public KhachHang() {
		super();
	}
	

}
