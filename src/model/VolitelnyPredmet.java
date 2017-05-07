package model;

public class VolitelnyPredmet extends Predmet {
	private int pocet_studentov;
	private double a;
	private double b;
	private double c;
	private double d;
	private double e;
	private double fx;
	
	public VolitelnyPredmet() {
		
	}
	
	public VolitelnyPredmet(int id, String kod, String nazov, int pocet_studentov, 
			double a, double b, double c, double d, double e, double fx) {
		super(id, kod, nazov);
		this.setPocet_studentov(pocet_studentov);
		this.setA(a);
		this.setB(b);
		this.setC(c);
		this.setD(d);
		this.setE(e);
		this.setFx(fx);
	}
	
	public VolitelnyPredmet(String kod, String nazov, int pocet_studentov, 
			double a, double b, double c, double d, double e, double fx) {
		super(kod, nazov);
		this.setPocet_studentov(pocet_studentov);
		this.setA(a);
		this.setB(b);
		this.setC(c);
		this.setD(d);
		this.setE(e);
		this.setFx(fx);
	}
	
	public VolitelnyPredmet(String kod, String nazov) {
		super(kod, nazov);
	}
	
	public int getPocet_studentov() {
		return pocet_studentov;
	}
	
	public void setPocet_studentov(int pocet_studentov) {
		this.pocet_studentov = pocet_studentov;
	}
	
	public double getA() {
		return a;
	}
	
	public void setA(double a) {
		this.a = a;
	}
	
	public double getB() {
		return b;
	}
	
	public void setB(double b) {
		this.b = b;
	}
	
	public double getC() {
		return c;
	}
	
	public void setC(double c) {
		this.c = c;
	}
	
	public double getD() {
		return d;
	}
	
	public void setD(double d) {
		this.d = d;
	}
	
	public double getE() {
		return e;
	}
	
	public void setE(double e) {
		this.e = e;
	}
	
	public double getFx() {
		return fx;
	}
	
	public void setFx(double fx) {
		this.fx = fx;
	}
	
	public void setZnamky(Double[] percenta) {
		this.a = percenta[0];
		this.b = percenta[1];
		this.c = percenta[2];
		this.d = percenta[3];
		this.e = percenta[4];
		this.fx = percenta[5];
	}
}
