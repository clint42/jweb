package controller;

import java.io.IOException;
import java.util.regex.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import helper.FlashMessenger;


/**
 * Servlet implementation class UserRegister
 */
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static boolean isEmailAddress(String email) {
    	Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
    	Matcher m = p.matcher(email.toUpperCase());
    	return m.matches();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String fname = (String) request.getParameter("First Name");
		String lname = (String) request.getParameter("Last Name");
		String uname = (String) request.getParameter("Username");
		String password = (String) request.getParameter("Password");
		String cpassword = (String) request.getParameter("Confirm Password");
		String email = (String) request.getParameter("E-Mail");
		String address = (String) request.getParameter("Address");
		String country = (String) request.getParameter("country");
		String city = (String) request.getParameter("City");
		String zipcode = (String) request.getParameter("Zip Code");
		
		if (fname.equals("First Name") || lname.equals("Last Name") || uname.equals("Login") ||
			password.equals("Password") || email.equals("E-Mail") || address.equals("Address") ||
			country == null || city.equals("City") || zipcode.equals("Zip Code"))
			FlashMessenger.getMessenger(session).addErrorMessage("You have to fill all the fields");
		else if (!password.equals(cpassword))
			FlashMessenger.getMessenger(session).addErrorMessage("Password and Confirm Password don't match");
		else if (!isEmailAddress(email))
			FlashMessenger.getMessenger(session).addErrorMessage("You provided an invalid e-mail");
		else {
			User new_user = new User(0, uname, password, fname, lname, email, address, city, country, zipcode);
			if (new_user.saveToDb()) {
				FlashMessenger.getMessenger(session).addSuccessMessage("Your account has been created");
				session.setAttribute("user", new_user);
				response.sendRedirect("/jweb/User/Account");
				return ;
			}
			else
				FlashMessenger.getMessenger(session).addErrorMessage("The creation of your account failed");
		}
		request.getRequestDispatcher("/register.jsp").forward(request, response);						
	}

}
