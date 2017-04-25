package model;

public class Predmet {
	private int id;
	private String nazov;
	private String kod;

	public Predmet() {
		this.kod = "";
		this.nazov = "";
	}
	
	public Predmet(int id, String kod, String nazov) {
		this.setId(id);
		this.setKod(kod);
		this.setNazov(nazov);
	}

	public Predmet(int id, String nazov) {
		setId(id);
		setNazov(nazov);
	}

	public Predmet(String kod, String nazov) {
		setKod(kod);
		setNazov(nazov);
	}

	public Predmet(String nazov) {
		setNazov(nazov);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazov() {
		return nazov;
	}

	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}
	
	public String toString() {
		return this.getKod() + " " + this.getNazov();
	}
}
