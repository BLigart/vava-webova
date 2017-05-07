package model;

import java.util.ArrayList;

public class Plan implements Comparable<Plan> {
	private int id;
	private String creator_name;
	private String nazov;
	private ArrayList<PlanPredmet> planPredmety;
	
	private double pocetStudentov;
	private double priemer;
	private double absolvovanie;
	private int sortFactor;
	
	public Plan(int id, String creator_name, String nazov) {
		this.id = id;
		this.creator_name = creator_name;
		this.nazov = nazov;
		this.planPredmety = new ArrayList<PlanPredmet>();
	}
	
	public void setSortValues() {
		this.sortFactor = 0;
		this.pocetStudentov = 0;
		this.priemer = 0;
		this.absolvovanie = 0;
		for(PlanPredmet planPredmet : this.planPredmety) {
			this.pocetStudentov += planPredmet.getVolitelnyPredmet().getPocet_studentov();
			
			this.priemer += planPredmet.getVolitelnyPredmet().getA();
			this.priemer += planPredmet.getVolitelnyPredmet().getB() * 1.5;
			this.priemer += planPredmet.getVolitelnyPredmet().getC() * 2.0;
			this.priemer += planPredmet.getVolitelnyPredmet().getD() * 2.5;
			this.priemer += planPredmet.getVolitelnyPredmet().getE() * 3.0;
			this.priemer += planPredmet.getVolitelnyPredmet().getFx() * 3.5;
			
			this.absolvovanie += planPredmet.getVolitelnyPredmet().getFx();
		}
		this.pocetStudentov /= this.planPredmety.size();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCreator_name() {
		return creator_name;
	}
	
	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}
	
	public String getNazov() {
		return nazov;
	}
	
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	public ArrayList<PlanPredmet> getPlanPredmety() {
		return planPredmety;
	}

	public void setPlanPredmety(ArrayList<PlanPredmet> planPredmety) {
		this.planPredmety = planPredmety;
	}
	
	public void addPlanPredmet(PlanPredmet planPredmet) {
		this.planPredmety.add(planPredmet);
	}

	@Override
	public int compareTo(Plan plan) {
		switch(this.getSortFactor()) {
			case 1: {
				return Double.compare(plan.getPocetStudentov(), this.getPocetStudentov());
			}
			case 2: {
				return Double.compare(this.getPriemer(), plan.getPriemer());
			}
			case 3: {
				return Double.compare(this.getAbsolvovanie(), plan.getAbsolvovanie());
			}
			case 4: {
				return this.getCreator_name().compareTo(plan.getCreator_name());
			}
		}
		return 0;
	}

	public double getPocetStudentov() {
		return pocetStudentov;
	}

	public void setPocetStudentov(double pocetStudentov) {
		this.pocetStudentov = pocetStudentov;
	}

	public double getPriemer() {
		return priemer;
	}

	public void setPriemer(double priemer) {
		this.priemer = priemer;
	}

	public double getAbsolvovanie() {
		return absolvovanie;
	}

	public void setAbsolvovanie(double absolvovanie) {
		this.absolvovanie = absolvovanie;
	}

	public int getSortFactor() {
		return sortFactor;
	}

	public void setSortFactor(int sortFactor) {
		this.sortFactor = sortFactor;
	}
	
	public String toString() {
		return this.nazov + "(by " + this.creator_name + ")";
	}
}
