package fast.bloc;

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

/**
 * Servlet Filter implementation class FiltroAdmins
 */
@WebFilter("/admins/*")
public class FiltroAdmins implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroAdmins() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		if (req.getSession().getAttribute("usuario") != null) {
			if(usuario.getTipo_usu() == fast.bloc.Usuario.ADMINISTRADOR) {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
			
		} else {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso prohibido");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
