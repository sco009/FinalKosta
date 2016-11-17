package cosmos.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		System.out.println("혹시 여기는 지나가니");

		if (session.getAttribute("login") == null) {
			saveDest(request); // 로그인이 안된 상태에서 로그인세션이 없으면 login으로 튕기기 전에 내 url
								// 주소를 가지고 튕기도록
			response.sendRedirect("/login/login");
			return false;
		}
		return true;
	}
	
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		System.out.println(uri);
		String query = request.getQueryString();
		if(query == null || query.equals(null)) {
			query = "";
		} else {
			query = "?"+query;
		}
		
		if(request.getMethod().equals("GET")) {
			request.getSession().setAttribute("dest", uri+query); // session에 내가 register로 가고자 하는 정보를 저장
		}
	}
}
