package entity;

public class ThongKe {
	private String maSach;
	private String tenSach;
	private Double donGia;
	private int soLuongBan;
	private int soLuongTon;
	private Double tongTien;
	
	public ThongKe() {
		super();
	}
	public ThongKe(String maSach,String tenSach,Double donGia,int soLuongBan,int soLuongTon,Double tongTien) {
		super();
		this.maSach=maSach;
		this.tenSach=tenSach;
		this.donGia=donGia;
		this.soLuongBan=soLuongBan;
		this.soLuongTon=soLuongTon;
		this.tongTien=donGia*soLuongBan;
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
	public int getSoLuongBan() {
		return soLuongBan;
	}
	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}
	public Double getDonGia() {
		return donGia;
	}
	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public Double getTongTien() {
		return tongTien;
	}
	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}
	
	@Override
	public String toString() {
		return "ThongKe [maSach=" + maSach + ", tenSach=" + tenSach + ", donGia=" + donGia + ", soLuongBan="
				+ soLuongBan + ", soLuongTon=" + soLuongTon + ", tongTien=" + tongTien + "]";
	}
	
	
}
