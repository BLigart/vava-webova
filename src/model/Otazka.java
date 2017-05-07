package model;

public class Otazka {
	private int id;
	private String otazka;
	private String odpoved;
	private int test_id;
	
	public Otazka(int id, String otazka, String odpoved, int test_id) {
		this.setId(id);
		this.setOtazka(otazka);
		this.setOdpoved(odpoved);
		this.setTest_id(test_id);
	}
	
	public Otazka(String otazka, String odpoved, int test_id) {
		this.setOtazka(otazka);
		this.setOdpoved(odpoved);
		this.setTest_id(test_id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOtazka() {
		return otazka;
	}
	
	public void setOtazka(String otazka) {
		this.otazka = otazka;
	}
	
	public String getOdpoved() {
		return odpoved;
	}
	
	public void setOdpoved(String odpoved) {
		this.odpoved = odpoved;
	}
	
	public int getTest_id() {
		return test_id;
	}
	
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
}
