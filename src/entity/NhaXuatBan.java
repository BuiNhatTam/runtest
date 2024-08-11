package entity;

public class NhaXuatBan {
	private String maNhaXuatBan;
	private String tenNhaXuatBan;
	public String getMaNhaXuatBan() {
		return maNhaXuatBan;
	}
	public void setMaNhaXuatBan(String maNhaXuatBan) {
		if(maNhaXuatBan.startsWith("XB") && maNhaXuatBan.length()==6 & !maNhaXuatBan.isEmpty()) {
			this.maNhaXuatBan = maNhaXuatBan;
		}else {
			this.maNhaXuatBan = "Mã nhà xuất bản không hợp lệ";
		}
	}
	public String getTenNhaXuatBan() {
		return tenNhaXuatBan;
	}
	public void setTenNhaXuatBan(String tenNhaXuatBan) {
		if(!tenNhaXuatBan.isEmpty()) {
			this.tenNhaXuatBan = tenNhaXuatBan;
		}else {
			this.tenNhaXuatBan = "Tên nhà xuất bản không được rỗng";
			}
	}
	/*
	 * @param maNhaXuatBan
	 * @param tenNhaXuatBan
	 */
	public NhaXuatBan(String maNhaXuatBan,String tenNhaXuatBan) {
		super();
		this.maNhaXuatBan = maNhaXuatBan;
		this.tenNhaXuatBan = tenNhaXuatBan;
	}
	
	public NhaXuatBan(String maNhaXuatBan) {
		super();
		this.maNhaXuatBan = maNhaXuatBan;
	}
	
	public NhaXuatBan() {
		
	}
	@Override
	public String toString() {
//		return "NhaXuatBan [maNhaXuatBan=" + maNhaXuatBan + ", tenNhaXuatBan=" + tenNhaXuatBan + "]";
		return maNhaXuatBan;
	}
	
}
