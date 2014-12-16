package controller;

import java.io.IOException;
import java.util.regex.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;


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
		request.setAttribute("error", "");
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = (String) request.getParameter("First Name");
		String lname = (String) request.getParameter("Last Name");
		String login = (String) request.getParameter("Login");
		String password = (String) request.getParameter("Password");
		String cpassword = (String) request.getParameter("Confirm Password");
		String email = (String) request.getParameter("E-Mail");
		String address = (String) request.getParameter("Address");
		String country = (String) request.getParameter("country");
		String city = (String) request.getParameter("City");
		String zipcode = (String) request.getParameter("Zip Code");
		
		if (fname.equals("First Name") || lname.equals("Last Name") || login.equals("Login") ||
			password.equals("Password") || email.equals("E-Mail") || address.equals("Address") ||
			country == null || city.equals("City") || zipcode.equals("Zip Code")) {
			request.setAttribute("error", "Error : You have to fill all the fields");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		else if (!password.equals(cpassword)) {
			request.setAttribute("error", "Error : Password and Confirm Password don't match");
			request.getRequestDispatcher("/register.jsp").forward(request, response);			
		}
		else if (!isEmailAddress(email)) {
			request.setAttribute("error", "Error : You provide an invalid e-mail");
			request.getRequestDispatcher("/register.jsp").forward(request, response);						
		}
		else {
			User new_user = new User(0, login, password, fname, lname, email, address, country, city, zipcode);
			new_user.saveToDb();
			request.getRequestDispatcher("/").forward(request, response);			
		}
		// TODO Auto-generated method stub
	}

}
