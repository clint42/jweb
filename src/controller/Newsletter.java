package controller;

import helper.FlashMessenger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Abonewsletter;

/**
 * Servlet implementation class Newsletter
 */
public class Newsletter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newsletter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			HttpSession session = request.getSession();
			if (request.getParameter("action").equals("subscribe")) {
				String mail = request.getParameter("mail");
				if (mail != null && !mail.equals("")) {
					if (Abonewsletter.isMailRegistered(mail)) {
						FlashMessenger.getMessenger(session).addErrorMessage("This mail address is already registered to our newsletters");
					}
					else {
						Abonewsletter abonewsletter = new Abonewsletter(0, mail, "", "", 0);
						if (abonewsletter.saveToDb()) {
							FlashMessenger.getMessenger(session).addSuccessMessage("You're now registered to our newsletter");
						}
						else {
							FlashMessenger.getMessenger(session).addErrorMessage("An error occurred, impossible to register you to our newsletter");
						}
					}
					response.sendRedirect("/jweb/Home");
				}
			}
		}
	}

}
