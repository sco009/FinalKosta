package cosmos.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN = "login";

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object loginVO = modelMap.get("loginVO");
		
		if(loginVO != null) {
			session.setAttribute(LOGIN, loginVO);
			
			Object dest = session.getAttribute("dest");
			System.out.println("dest : "+(String)dest);
			response.sendRedirect(dest!=null ? (String)dest : "/login/log_main");
			session.removeAttribute("dest");
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute(LOGIN)!=null){
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
}
