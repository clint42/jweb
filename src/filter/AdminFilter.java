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

import model.User;

/**
 * Servlet Filter implementation class AdminFilter
 */
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
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
			HttpServletRequest httpReq = (HttpServletRequest)(request);
			HttpServletResponse httpResp = (HttpServletResponse)(response);
			HttpSession session = httpReq.getSession(false);
			if (session != null) {
				if (session.getAttribute("user") != null) {
					User user = (User)(session.getAttribute("user"));
					if (user.getRole().equals("A")) {
						chain.doFilter(request, response);
					}
					else {
						FlashMessenger.getMessenger(session).addErrorMessage("You are not allowed to view this page (admin privilege required)");
					}
				}
				else {
					httpResp.sendRedirect("/jweb/User/Login");
				}
			}
			else {
				httpResp.sendRedirect("/jweb/User/Login");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
