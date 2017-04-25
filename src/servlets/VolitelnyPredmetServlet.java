package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PlanPredmet;
import model.VolitelnyPredmet;

/**
 * Servlet implementation class VolitelnyPredmetServlet
 */
@WebServlet("/selectvolitelnypredmet")
public class VolitelnyPredmetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String selectedSemesterStr = request.getParameter("semesterSelect");
		int selectedSemester = Integer.parseInt(selectedSemesterStr);
		String selectedVolitelnyPredmet = request.getParameter("volitelnypredmetSelect");
		if(selectedSemester != 0 && !selectedVolitelnyPredmet.equals("")) {
			ArrayList<PlanPredmet> predmety_v_plane = (ArrayList<PlanPredmet>) request.getSession().getAttribute("predmety_v_plane");
			
			ArrayList<VolitelnyPredmet> volitelne_predmety = new ArrayList<VolitelnyPredmet>();
			volitelne_predmety = (ArrayList<VolitelnyPredmet>) request.getSession().getAttribute("volitelne_predmety");
			
			for(VolitelnyPredmet volitelnyPredmet: volitelne_predmety) {
				System.out.println(volitelnyPredmet.toString() + " : " + selectedVolitelnyPredmet);
				if(volitelnyPredmet.toString().equals(selectedVolitelnyPredmet)) {
					if(predmety_v_plane == null) {
						predmety_v_plane = new ArrayList<PlanPredmet>();
					}
					predmety_v_plane.add(new PlanPredmet(selectedSemester, volitelnyPredmet));
					request.getSession().setAttribute("predmety_v_plane", predmety_v_plane);
					volitelne_predmety.remove(volitelnyPredmet);
					request.getSession().setAttribute("volitelne_predmety", volitelne_predmety);
					Collections.sort(predmety_v_plane);
					break;
				}
 			}
			
		}
		
		response.sendRedirect("novy_plan_screen.jsp");
		return;
	}

}
