package controller;

import helper.FlashMessenger;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.News;
import model.User;

/**
 * Servlet implementation class AdminNews
 */
public class AdminNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, BiConsumer<HttpServletRequest, HttpServletResponse>> getActions = new HashMap<String, BiConsumer<HttpServletRequest, HttpServletResponse>>();
    private HashMap<String, BiConsumer<HttpServletRequest, HttpServletResponse>> postActions = new HashMap<String, BiConsumer<HttpServletRequest, HttpServletResponse>>();
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNews() {
        super();
        getActions.put("edit", (request, response) -> { this.editGetAction(request, response); });
        getActions.put("add", (request, response) -> { this.addGetAction(request, response); });
        getActions.put("delete", (request, response) -> { this.deleteGetAction(request, response); });
        postActions.put("edit", (request, response) -> { this.editPostAction(request, response); });
        postActions.put("add", (request, response) -> { this.addPostAction(request, response); });
        postActions.put("delete", (request, response) -> { this.deletePostAction(request, response); });
        // TODO Auto-generated constructor stub
    }

	private void addGetAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("in addGetAction");
		HashMap<String, String> formValues = new HashMap<String, String>();
		formValues.put("title", "");
		formValues.put("content", "");
		request.setAttribute("formValues", formValues);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editNews.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editGetAction(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getPathInfo();
		String[] segments = uri.split("/");
		if (segments.length > 2) {
			try {
				int id = Integer.parseInt(segments[2]);
				News news = new News();
				if (news.fetchById(id)) {
					HashMap<String, String> formValues = new HashMap<String, String>();
					formValues.put("title", news.getTitle());
					formValues.put("content", news.getContent());
					request.setAttribute("formValues", formValues);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editNews.jsp");
					try {
						dispatcher.forward(request, response);
					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}
				}
				else {
					FlashMessenger.getMessenger(request.getSession()).addErrorMessage("Requested news doesn't exist");
					try {
						response.sendRedirect("/jweb/Admin/News/Add");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteGetAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addPostAction(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") instanceof User) {
			User user = (User)(session.getAttribute("user"));
			if (request.getParameter("title") != null && request.getParameter("content") != null &&
				user != null) {
				News news = new News(0, request.getParameter("title"), request.getParameter("content"), user.getId());
				if (news.saveToDb()) {
					FlashMessenger.getMessenger(session).addSuccessMessage("News correctly published");
					try {
						response.sendRedirect("/jweb/Admin/News/Edit/" + news.getId());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					FlashMessenger.getMessenger(session).addErrorMessage("An error occurred. News not published");
					HashMap<String, String> formValues = new HashMap<String, String>();
					formValues.put("title", request.getParameter("title"));
					formValues.put("content", request.getParameter("content"));
					request.setAttribute("formValues", formValues);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editNews.jsp");
					try {
						dispatcher.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void editPostAction(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("title") == null || request.getParameter("content") == null) {
			FlashMessenger.getMessenger(request.getSession()).addErrorMessage("Missing request parameters");
			try {
				response.sendRedirect("jweb/Admin/News/Add");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String uri = request.getPathInfo();
		String[] segments = uri.split("/");
		if (segments.length > 2) {
			int id = Integer.parseInt(segments[2]);
			News news = new News();
			if (news.fetchById(id)) {
				news.setTitle(request.getParameter("title"));
				news.setContent(request.getParameter("content"));
				if (!news.saveToDb()) {
					FlashMessenger.getMessenger(request.getSession()).addErrorMessage("An error occurred. News has not been updated");
				}
				else {
					FlashMessenger.getMessenger(request.getSession()).addSuccessMessage("News correctly udpated");
				}
				HashMap<String, String> formValues = new HashMap<String, String>();
				formValues.put("title", news.getTitle());
				formValues.put("content", news.getContent());
				request.setAttribute("formValues", formValues);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editNews.jsp");
				try {
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else {
				FlashMessenger.getMessenger(request.getSession()).addErrorMessage("Requested news doesn't exist");
				try {
					response.sendRedirect("/jweb/Admin/News/Add");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void deletePostAction(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminNews.doGet");
		String uri = request.getRequestURI();
		String[] segments = uri.split("/");
		String routeSegment = segments[3];
		if (routeSegment != null && routeSegment.equals("news")) {
			
		}
		else if (routeSegment != null && segments.length > 3) {
			getActions.get(segments[4].toLowerCase()).accept(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] segments = uri.split("/");
		String routeSegment = segments[3];
		if (routeSegment != null && routeSegment.equals("news")) {
			
		}
		else if (routeSegment != null && segments.length > 3) {
			postActions.get(segments[4].toLowerCase()).accept(request, response);
		}	
	}

}
