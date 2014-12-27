package controller;

import helper.FlashMessenger;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserMailAlreadyUsedException;

/**
 * Servlet implementation class AdminUser
 */
public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/users.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("mail") != null && request.getParameter("mail") != "" && request.getParameter("edit") == null) {
			User user = User.getUserByMail(request.getParameter("mail"));
			if (user != null)  {
				HashMap<String, String> formValues = new HashMap<String, String>();
				formValues.put("id", String.valueOf(user.getId()));
				formValues.put("username", user.getUsername());
				formValues.put("mail", user.getMail());
				formValues.put("firstName", user.getFirstName());
				formValues.put("lastName", user.getLastName());
				formValues.put("address", user.getAddress());
				formValues.put("city", user.getCity());
				formValues.put("zipcode", user.getZipcode());
				formValues.put("country", user.getCountry());
				request.setAttribute("formValues", formValues);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user.jsp");
				dispatcher.forward(request, response);
			}
			else {
				FlashMessenger.getMessenger(request.getSession()).addErrorMessage("No user associated to this email adress");
			}
		}
		if (request.getParameter("edit") != null && request.getParameter("id") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				User user = User.getUserById(id);
				if (user != null) {
					user.setUsername(request.getParameter("username"));
					user.setMail(request.getParameter("mail"));
					user.setFirstName(request.getParameter("firstName"));
					user.setLastName(request.getParameter("lastName"));
					user.setAddress(request.getParameter("address"));
					user.setCity(request.getParameter("city"));
					user.setZipcode(request.getParameter("zipcode"));
					user.setCountry(request.getParameter("country"));
					try {
						if (user.saveToDb()) {
							FlashMessenger.getMessenger(request.getSession()).addSuccessMessage("User informations updated !");
						}
						else {
							FlashMessenger.getMessenger(request.getSession()).addErrorMessage("An error occured. Changes not saved");
						}
					} catch (UserMailAlreadyUsedException e) {
						FlashMessenger.getMessenger(request.getSession()).addErrorMessage("This error should never happens. Please accept our apologize");
					}
					response.sendRedirect("/jweb/Admin/Users");
					return ;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			FlashMessenger.getMessenger(request.getSession()).addErrorMessage("User id is invalid");
			response.sendRedirect("/jweb/Admin/Users");
		}
	}

}
