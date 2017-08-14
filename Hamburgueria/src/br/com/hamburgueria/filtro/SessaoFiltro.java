package br.com.hamburgueria.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessaoFilter
 */
public class SessaoFiltro implements Filter {

    /**
     * Default constructor. 
     */
    public SessaoFiltro() {
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
		
		String context = request.getServletContext().getContextPath();
		
		try{
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			String nome = null;
			if(session != null){
				nome = (String) session.getAttribute("nome");
			}
			
			if(nome == null){
				((HttpServletResponse) response).sendRedirect(context + "/error.html");
			}else{
				chain.doFilter(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			((HttpServletResponse) response).sendRedirect(context + "/error.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
