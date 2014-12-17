package filter;

import helper.FlashMessenger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {
	private FilterConfig fc;
	
    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request != null && response != null && request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest)(request);
			HttpServletResponse httpResponse = (HttpServletResponse)(response);
			String uri = httpRequest.getRequestURI();
			String lastSegment = uri.substring(uri.lastIndexOf('/') + 1);
			HttpSession session = httpRequest.getSession(false);
			if (!lastSegment.equals("Login") && !lastSegment.equals("Register")) {
				if (session == null) {
					FlashMessenger.getMessenger(httpRequest.getSession()).addErrorMessage("You are not connected");
					httpResponse.sendRedirect("/jweb/User/Login");
				}
				else {
					if (session.getAttribute("user") != null) {
						chain.doFilter(request, response);
					}
					else {
						FlashMessenger.getMessenger(session).addErrorMessage("You are not connected");
						httpResponse.sendRedirect("/jweb/User/Login");
					}
				}
			}
			else if (session == null || session.getAttribute("user") == null) {
				chain.doFilter(request, response);
			}
			else if (session != null && session.getAttribute("user") != null) {
				httpResponse.sendRedirect("/jweb/User/Account");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.fc = fConfig;
	}

}