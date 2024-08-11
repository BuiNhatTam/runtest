package entity;

public class TacGia {
	private String maTacGia;
	private String tenTacgia;
	public String getMaTacGia() {
		return maTacGia;
	}
	public void setMaTacGia(String maTacGia) {
		if(maTacGia.startsWith("TG") && maTacGia.length() == 6 && !maTacGia.isEmpty()) {
			this.maTacGia = maTacGia;
		}else {
			this.maTacGia = "Mã tác giả không họp lệ";
		}
	}
	public String getTenTacgia() {
		return tenTacgia;
	}
	public void setTenTacgia(String tenTacgia) {
		if(!tenTacgia.isEmpty()) {
			this.tenTacgia = tenTacgia;
		}else {
			this.tenTacgia = "Tên tác giả không được rỗng";
		}
	}
	
	/**
	 * @param maTacGia
	 * @param tenTacGia
	 */
	
	public TacGia(String maTacGia,String tenTacGia) {
		super();
		this.maTacGia = maTacGia;
		this.tenTacgia = tenTacGia;
	}
	/**
	 * 
	 * 
	 * @param maTacGia
	 */
	public TacGia(String maTacGia) {
		super();
		this.maTacGia = maTacGia;
	}

	public TacGia() {
		
	}
	@Override
	public String toString() {
//		return "TacGia [maTacGia=" + maTacGia + ", tenTacgia=" + tenTacgia + "]";
		return maTacGia;
	}
	
	
}
