package nl.hu.v1ipass.firstapp.model;


public class Veld {
	private int veldNummer;
	private String veldType;
	
	public Veld(int vNum, String vType) {
		this.veldNummer = vNum;
		this.veldType = vType;
	}
	
	public int getVeldNummer() {
		return veldNummer;
	}
	
	public void setVeldNummer(int vNum) {
		this.veldNummer = vNum;
	}
	
	public String getVeldType() {
		return veldType;
	}
	
	public void setVeldType(String vType) {
		this.veldType = vType;
	}
	
	
}
