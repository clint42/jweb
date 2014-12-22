package controller;

import helper.FlashMessenger;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.digester.Digester;

import model.Product;
import model.Review;
import model.User;

/**
 * Servlet implementation class UserReview
 */
@WebServlet("/UserReview")
public class UserReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getPathInfo();
		String[] segments = uri.split("/");
		if (segments.length > 1) {
			try {
				int productId = Integer.parseInt(segments[1]);
				Product product = new Product();
				if (product.fetchById(productId)) {
					request.setAttribute("product", product);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/user/review.jsp");
					dispatcher.forward(request, response);
				}
				else {
					FlashMessenger.getMessenger(request.getSession()).addErrorMessage("The product you want to review doesn't exist");
					response.sendRedirect("/jweb/Home");
				}
				
			}
			catch (NumberFormatException e){
				e.printStackTrace();
				return;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getPathInfo();
		String[] segments = uri.split("/");
		HttpSession session = request.getSession(false);
		if (segments.length > 1 && session.getAttribute("user") != null) {
			try {
				int productId = Integer.parseInt(segments[1]);
				double rank = Double.parseDouble(request.getParameter("rank"));
				User user = (User)(session.getAttribute("user"));
				Review review = new Review(request.getParameter("title"), request.getParameter("comment"), rank, user.getId(), productId);
				if (review.saveToDb()) {
					FlashMessenger.getMessenger(session).addSuccessMessage("Your review has been posted. Thanks !");
					response.sendRedirect("/jweb/Product/" + productId);
				}
				else {
					FlashMessenger.getMessenger(session).addErrorMessage("An error occured. Please try again");
					Product product = new Product();
					if (product.fetchById(productId)) {
						request.setAttribute("product", product);
						request.setAttribute("reviewTitle", request.getParameter("title"));
						request.setAttribute("reviewText", request.getParameter("comment"));
						request.setAttribute("reviewRank", request.getParameter("rank"));
						RequestDispatcher dispatcher = request.getRequestDispatcher("/user/Review.jsp");
						dispatcher.forward(request, response);
					}
					else {
						response.sendRedirect("/jweb/User/Review/" + productId);
					}
				}
				return ;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return ;
			}
		}
		FlashMessenger.getMessenger(request.getSession()).addErrorMessage("Sorry, we cannot process your request. (invalid request)");
		response.sendRedirect("/jweb/Home");
	}

}
