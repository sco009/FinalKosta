package cosmos.common.filter;

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

import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter{
   @Override
   public void destroy() {
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      
      httpResponse.setHeader("Access-Control-Allow-Origin", "*");
      httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
      httpResponse.setHeader("Access-Control-Max-Age", "3600");
      httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
      
        chain.doFilter(request, response);
   }

   @Override
   public void init(FilterConfig config) throws ServletException {
   }
}