package model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import managers.PredmetManager;
import managers.VolitelnyPredmetManager;
import model.Predmet;

public class User {
	private WebClient webClient;
	private ArrayList<Predmet> predmety;
	private ArrayList<VolitelnyPredmet> volitelne_predmety;
	private HtmlPage currentPage;
	
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public User() {
		this.webClient = new WebClient(); //Initiate a WebClient variable.
		this.webClient.setThrowExceptionOnFailingStatusCode(false);
		this.predmety = new ArrayList<Predmet>();
		this.volitelne_predmety = new ArrayList<VolitelnyPredmet>();
	}

	public void loadpage() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		this.webClient.setJavaScriptEnabled(false);
		this.webClient.setPrintContentOnFailingStatusCode(false);
		currentPage = this.webClient.getPage("https://is.stuba.sk/auth/?lang=sk"); //Load page at the STRING address
	}
	
	public int getRok() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		this.webClient.setJavaScriptEnabled(false);
		this.webClient.setPrintContentOnFailingStatusCode(false);
		
		//load Portal studenta
		currentPage = this.webClient.getPage("https://is.stuba.sk/auth/student/moje_studium.pl?;lang=sk");
		String text = ((DomNode) currentPage.getFirstByXPath("//*[@id='titulek']/div/b[2]")).getTextContent();
		
		return Integer.parseInt(text.substring(3, 7));
	}

	//Login into AIS and return the client(Saves the cookies).
	public boolean aisLogin(String str_username, String str_password) throws Exception {
		HtmlInput username = currentPage.getElementByName("credential_0"); //Find element called credential_0 for username
		username.setValueAttribute(str_username); //Set value for username
		HtmlInput password = currentPage.getElementByName("credential_1"); //Find element called credential_1 for password
		password.setValueAttribute(str_password); //Set value for password
		HtmlSubmitInput submitBtn = currentPage.getElementByName("login"); //Find element called login to submit form
		currentPage = submitBtn.click(); //Click on the button
		try {
			currentPage.getElementByName("credential_0");
		}
		catch(Exception e) {
			System.out.println("Success");
			return true;
		}
		return false;
	}

	public void get_predmety() throws Exception {
		this.webClient.setJavaScriptEnabled(false);
	    HtmlPage currentPage = this.webClient.getPage("https://is.stuba.sk/auth/student/pruchod_studiem.pl?;lang=sk"); //Load page at the STRING address
	    //String pageSource = currentPage.asXml();
	    HtmlTable table = (HtmlTable) currentPage.getElementById("tmtab_1");
	    //System.out.println(table.asText());
	    String[] ar = table.asText().split("	|\n");
	    this.predmety = new ArrayList<Predmet>();
	    
	    PredmetManager predmetManager = new PredmetManager();
	    for(int i = 12; i < ar.length; i += 11) {
	    	predmetManager.insertPredmet(new Predmet(ar[i - 1], ar[i]));
	    	this.predmety.add(predmetManager.getPredmet(ar[i - 1]).get(0));
	    }
	}

	public void get_volitelne_predmety() throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {
		this.webClient.setJavaScriptEnabled(false);
	    HtmlPage currentPage = this.webClient.getPage("https://is.stuba.sk/auth/studijni/studijni_povinnosti.pl?;lang=sk");
	    //String pageSource = currentPage.asXml();
	    HtmlTable table = (HtmlTable) currentPage.getElementById("tmtab_2");
	    //System.out.println(table.asText());
	    String[] ar = table.asText().split("	|\n");
	    this.volitelne_predmety = new ArrayList<VolitelnyPredmet>();
	    Boolean selection = false;
	    int selection_num = 0;
	    for(int i = 0; i < ar.length - 3; i++) {
	    	//System.out.println(i + " : " + ar[i]);
	    	if(ar[i].contains("volite¾ných")) {
	    		selection = true;
	    		selection_num = -2;
	    	}
	    	else if(ar[i + 3].contains("Názov predmetu")) {
	    		selection = false;
	    	}
	    	if(selection == true) {
	    		if(selection_num % 6 == 0) {
	    			//System.out.println(ar[i - 1] + " : " + ar[i]);
	    			this.volitelne_predmety.add(new VolitelnyPredmet(ar[i - 1], ar[i]));
	    		}
	    		selection_num++;
	    	}
	    }

	    get_predmet_data();
	}

	public void get_predmet_data() throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {
		this.webClient.setJavaScriptEnabled(false);
	    HtmlPage currentPage = this.webClient.getPage("https://is.stuba.sk/auth/katalog/index.pl?;ustav=0;vypsat=Vyp%C3%ADsa%C5%A5%20predmety;fakulta=21070;jak=dle_pracovist;lang=sk");
	    VolitelnyPredmetManager volitelnypredmetmanager = new VolitelnyPredmetManager();


	    for(VolitelnyPredmet volitelnyPredmet : this.volitelne_predmety) {
	    	try {
	    		System.out.println("searching " + volitelnyPredmet.getKod() + " " + volitelnyPredmet.getNazov());
	    		HtmlPage currentPage1 = currentPage.getAnchorByText(volitelnyPredmet.getKod() + " " + volitelnyPredmet.getNazov()).click();
	    		System.out.println("ACTIVE " + volitelnyPredmet.getKod());
	    		String output = ((DomNode) currentPage1.getFirstByXPath("//*[@id='base-right']/div/table/tbody/tr[32]/td/small")).getTextContent();
	    		volitelnyPredmet.setPocet_studentov(Integer.parseInt(output.substring(output.indexOf(": ") + 2)));

	    		output = ((DomNode) currentPage1.getFirstByXPath("//*[@id='base-right']/div/table/tbody/tr[32]")).getTextContent();

	    		if(output.contains("FX")) {
	    			output = output.substring(output.indexOf("FX") + 2);
	    		}
	    		else {
	    			output = output.substring(output.indexOf("ABCDEF") + 6);
	    		}

	    		String[] percenta = output.split(" %");
	    		Double[] double_percenta = new Double[6];
	    		for(int i = 0; i < percenta.length; i++) {
	    			percenta[i] = percenta[i].replaceAll(",", ".");
	    			double_percenta[i] = Double.parseDouble(percenta[i]);
	    		}
	    		volitelnyPredmet.setZnamky(double_percenta);
	    	}
	    	catch(ElementNotFoundException e) {
	    		System.out.println("INACTIVE " + volitelnyPredmet.getKod());
	    	}
	    	try {
				volitelnypredmetmanager.insertVolitelnyPredmet(volitelnyPredmet);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    }
	}

	public ArrayList<Predmet> getPredmety() {
		return this.predmety;
	}
	
	public void setVolitelne_predmety(ArrayList<VolitelnyPredmet> volitelne_predmety) {
		this.volitelne_predmety = volitelne_predmety;
	}
	
	public ArrayList<VolitelnyPredmet> getVolitelne_predmety() {
		return this.volitelne_predmety;
	}
}
