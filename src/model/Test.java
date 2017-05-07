package model;

public class Test {
	private int id;
	private int predmet_id;
	private int rok_id;
	private int poradie;
	
	public Test() {
		this.setPoradie(0);
	}
	
	public Test(int predmet_id, int rok_id, int poradie) {
		this.setPredmet_id(predmet_id);
		this.setRok_id(rok_id);
		this.setPoradie(poradie);
	}
	
	public Test(int id, int predmet_id, int rok_id, int poradie) {
		this.setId(id);
		this.setPredmet_id(predmet_id);
		this.setRok_id(rok_id);
		this.setPoradie(poradie);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPredmet_id() {
		return predmet_id;
	}
	
	public void setPredmet_id(int predmet_id) {
		this.predmet_id = predmet_id;
	}
	
	public int getRok_id() {
		return rok_id;
	}
	
	public void setRok_id(int rok_id) {
		this.rok_id = rok_id;
	}
	
	public int getPoradie() {
		return poradie;
	}
	
	public void setPoradie(int poradie) {
		this.poradie = poradie;
	}
	
	public String toString() {
		if(this.getPoradie() == 0) {
			return "";
		}
		return this.getPoradie() + ". test";
	}
}
