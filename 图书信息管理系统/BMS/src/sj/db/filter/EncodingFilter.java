package sj.db.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.management.RuntimeErrorException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

    public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;

			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			CharacterRequest characterRequest = new CharacterRequest(request);
			chain.doFilter(characterRequest, response);
	}

	class CharacterRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request;
		public CharacterRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if(value == null)
				return null;
			String method = super.getMethod();
			if("get".equalsIgnoreCase(method)) {
				try {
					value = new String(value.getBytes("iso-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
			return value;
		}
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	
}
