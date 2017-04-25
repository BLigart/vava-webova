package model;

public class Rok {
	private int id;
	private int rok_1;
	private int rok_2;
	
	public Rok() {
		this.setRok_1(0);
	}
	
	public Rok(int id, int rok_1, int rok_2) {
		this.setId(id);
		this.setRok_1(rok_1);
		this.setRok_2(rok_2);
	}
	
	public Rok(int rok_1, int rok_2) {
		this.setRok_1(rok_1);
		this.setRok_2(rok_2);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRok_1() {
		return rok_1;
	}
	public void setRok_1(int rok_1) {
		this.rok_1 = rok_1;
	}
	public int getRok_2() {
		return rok_2;
	}
	public void setRok_2(int rok_2) {
		this.rok_2 = rok_2;
	}
	
	public String toString() {
		if(this.getRok_1() == 0) {
			return "";
		}
		return this.getRok_1() + "/" + this.getRok_2();
	}
}
