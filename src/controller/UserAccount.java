package controller;

import helper.FlashMessenger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserMailAlreadyUsedException;

/**
 * Servlet implementation class UserAccount
 */
public class UserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public static boolean isEmailAddress(String email) {
		Pattern p = Pattern
				.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}
	
	private void changeInformation(HttpServletRequest request, HttpSession session, User actual_user) {
		String address = (String) request.getParameter("Address");
		String country = (String) request.getParameter("country");
		String city = (String) request.getParameter("City");
		String zipcode = (String) request.getParameter("Zip Code");

		try {
			actual_user.setNewInformation(address, country, city, zipcode);
			if (actual_user.saveToDb()) {
				FlashMessenger.getMessenger(session).addSuccessMessage(
						"Your account has been changed");
			}
			else
				FlashMessenger.getMessenger(session).addErrorMessage(
						"The modification of your account failed");
		}
		catch (UserMailAlreadyUsedException e) {
			e.printStackTrace();
		}
	}

	private void changePassword(HttpServletRequest request, HttpSession session, User actual_user) {
		String password = (String) request.getParameter("Actual Password");
		String new_password = (String) request.getParameter("New Password");
		String c_new_password = (String) request
				.getParameter("Confirm New Password");

		if (password.equals("") || new_password.equals("")
				|| c_new_password.equals("")) {
			FlashMessenger.getMessenger(session).addErrorMessage(
					"You have to fill all the fields");
		}
		else if (!new_password.equals(c_new_password)) {
			FlashMessenger.getMessenger(session).addErrorMessage(
					"New Password and Confirm New Password don't match");
		}
		else if (!(actual_user.isSamePassword(password))) {
			FlashMessenger.getMessenger(session).addErrorMessage(
					"Wrong password");
		}
		else {
			if (actual_user.changePasswordToDb(new_password)) {
				FlashMessenger.getMessenger(session).addSuccessMessage(
						"Your account has been changed");
			}
			else {
				FlashMessenger.getMessenger(session).addErrorMessage(
						"The modification of your account failed");
			}
		}
	}
	
	private boolean deleteAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session, User actual_user) throws IOException {
		String password = (String) request.getParameter("Password");
		if (password.equals(""))
			FlashMessenger.getMessenger(session).addErrorMessage(
					"You have to enter your password");
		else if (!actual_user.isSamePassword(password))
			FlashMessenger.getMessenger(session).addErrorMessage(
					"Wrong password");
		else {
			if (actual_user.delete()) {
				FlashMessenger.getMessenger(session).addSuccessMessage(
						"Your account has been erase");
				session.setAttribute("user", null);
				response.sendRedirect("/jweb/Home");
				return true;
			} else
				FlashMessenger.getMessenger(session).addErrorMessage(
						"The modification of your account failed");
		}
		return false;
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null
				&& request.getParameter("action").equals("logout")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.setAttribute("user", null);
				FlashMessenger.getMessenger(session).addSuccessMessage(
						"You have been logout");
				response.sendRedirect("/jweb/Home");
			}
		} else {
			RequestDispatcher dispatcher = null;
			dispatcher = request.getRequestDispatcher("/user/account.jsp");
			dispatcher.forward(request, response);
			HttpSession session = request.getSession();
			User user_info = (User) session.getAttribute("user");
			request.setAttribute("usr", user_info);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User actual_user = (User) (session.getAttribute("user"));
		if (request.getParameter("action").equals("information")) {
			this.changeInformation(request, session, actual_user);
		}
		else if (request.getParameter("action").equals("password")) {
			this.changePassword(request, session, actual_user);
		}
		else if (request.getParameter("action").equals("delete")) {
			if (this.deleteAccount(request, response, session, actual_user))
				return ;
		}
		response.sendRedirect("/jweb/User/Account");
	}
}
