package cosmos.login.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import cosmos.login.domain.LoginVO;
import cosmos.login.service.LoginService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	private LoginService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();		

		if (session.getAttribute("login") == null) {
			saveDest(request); // 로그인이 안된 상태에서 로그인세션이 없으면 login으로 튕기기 전에 내 url
								// 주소를 가지고 튕기도록
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie!=null) {
				LoginVO loginVO = service.checkLoginBefore(loginCookie.getValue());
				
				if(loginVO != null) {
					session.setAttribute("login", loginVO);
					return true;
				}
			}
			
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
