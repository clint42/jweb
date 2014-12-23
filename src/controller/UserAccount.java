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
			String address = (String) request.getParameter("Address");
			String country = (String) request.getParameter("country");
			String city = (String) request.getParameter("City");
			String zipcode = (String) request.getParameter("Zip Code");

			try {
				if (actual_user.saveToDb()) {
					response.sendRedirect("/jweb/User/Account");
					return;
				}
				else
					FlashMessenger.getMessenger(session).addErrorMessage(
							"The modification of your account failed");
			}
			catch (UserMailAlreadyUsedException e) {
				e.printStackTrace();
			}

		}
		else if (request.getParameter("action").equals("password")) {
			String password = (String) request.getParameter("Actual Password");
			String new_password = (String) request.getParameter("New Password");
			String c_new_password = (String) request
					.getParameter("Confirm New Password");

			if (password.equals("") || new_password.equals("")
					|| c_new_password.equals(""))
				FlashMessenger.getMessenger(session).addErrorMessage(
						"You have to fill all the fields");
			else if (!actual_user.getPassword().equals(password))
				FlashMessenger.getMessenger(session).addErrorMessage(
						"Wrong password");
			else if (!new_password.equals(c_new_password))
				FlashMessenger.getMessenger(session).addErrorMessage(
						"New Password and Confirm New Password don't match");
			else {
				try {
					if (actual_user.saveToDb()) {
						FlashMessenger.getMessenger(session).addSuccessMessage(
								"Your account has been changed");
						response.sendRedirect("/jweb/User/Account");
						return;
					} else
						FlashMessenger.getMessenger(session).addErrorMessage(
								"The modification of your account failed");
				} catch (UserMailAlreadyUsedException e) {
					e.printStackTrace();
				}
			}
		} else if (request.getParameter("action").equals("delete")) {
			String password = (String) request.getParameter("Password");
			if (password.equals(""))
				FlashMessenger.getMessenger(session).addErrorMessage(
						"You have to enter your password");
			else if (!actual_user.getPassword().equals(password))
				FlashMessenger.getMessenger(session).addErrorMessage(
						"Wrong password");
			else {
				if (actual_user.eraseToDb()) {
					FlashMessenger.getMessenger(session).addSuccessMessage(
							"Your account has been erase");
					response.sendRedirect("/jweb/Home");
					return;
				} else
					FlashMessenger.getMessenger(session).addErrorMessage(
							"The modification of your account failed");
			}

		}
	}
}
