package entity;

public class TheLoai {
	private String maTheLoai;
	private String tenTheLoai;
	public String getMaTheLoai() {
		return maTheLoai;
	}
	public void setMaTheLoai(String maTheLoai) {
		if(maTheLoai.startsWith("TL") && maTheLoai.length()== 6 && !maTheLoai.isEmpty()) {
			this.maTheLoai=maTheLoai;
		}else {
			this.maTheLoai ="Mã thể loại không họp lệ";
		}
	}
	public String getTenTheLoai() {
		return tenTheLoai;
	}
	public void setTenTheLoai(String tenTheLoai) {
		if(!tenTheLoai.isEmpty()) {
			this.tenTheLoai=tenTheLoai;
		}else {
			this.tenTheLoai ="Tên thể loại không được rỗng";
		}
	}
	
	/**
	 * @param maTheLoai
	 * @param tenTheLoai
	 */
	public TheLoai(String maTheLoai,String tenTheLoai) {
		super();
		this.maTheLoai = maTheLoai;
		this.tenTheLoai = tenTheLoai;
	}
	
	public TheLoai(String tenTheLoai) {
		super();
		this.tenTheLoai = tenTheLoai;
	}
	
//	public TheLoai(String maTheLoai) {
//		super();
//		this.maTheLoai = maTheLoai;
//	}
	public TheLoai() {
		
	}
	@Override
	public String toString() {
//		return "TheLoai [maTheLoai=" + maTheLoai + ", tenTheLoai=" + tenTheLoai + "]";
//		return maTheLoai;
		return tenTheLoai;
	}
	
	
}
