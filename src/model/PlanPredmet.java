package model;

public class PlanPredmet implements Comparable<PlanPredmet> {
	private int id;
	private int semester;
	private VolitelnyPredmet volitelnyPredmet;
	
	public PlanPredmet() {
		this.semester = 0;
		this.volitelnyPredmet = new VolitelnyPredmet();
	}
	
	public PlanPredmet(int id, int semester, VolitelnyPredmet volitelnyPredmet) {
		this.id = id;
		this.semester = semester;
		this.volitelnyPredmet = volitelnyPredmet;
	}
	
	public PlanPredmet(int semester, VolitelnyPredmet volitelnyPredmet) {
		this.semester = semester;
		this.volitelnyPredmet = volitelnyPredmet;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public VolitelnyPredmet getVolitelnyPredmet() {
		return volitelnyPredmet;
	}
	
	public void setVolitelnyPredmet(VolitelnyPredmet volitelnyPredmet) {
		this.volitelnyPredmet = volitelnyPredmet;
	}

	@Override
	public int compareTo(PlanPredmet planPredmet) {
		return Integer.compare(this.getSemester(), planPredmet.getSemester());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
