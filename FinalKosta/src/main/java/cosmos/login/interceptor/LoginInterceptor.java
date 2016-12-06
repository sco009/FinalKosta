package cosmos.login.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cosmos.login.domain.LoginVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {
   private static final String LOGIN = "login";

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();

		LoginVO vo = (LoginVO) modelMap.get("loginVO");

		if (vo != null) {
			session.setAttribute(LOGIN, vo);
			System.out.println("세션에담겼니? 아이디는 : " + vo.getMemberID());

			if (request.getParameter("useCookie") != null) {
				System.out.println("useCookie 출력!!!");
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginCookie);
			}

			Object dest = session.getAttribute("dest");
			response.sendRedirect(dest != null ? (String) dest : "/login/log_main");
			session.removeAttribute("dest");
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute(LOGIN) != null) {
			session.removeAttribute(LOGIN);
		}

		return true;
	}
}
