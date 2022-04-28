package nus.iss.day25.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class LoginFilter implements Filter{

    @Override // All Request going through filter
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpServletResponse httpResp = (HttpServletResponse)response;
        
        // Get the HTTP session
        HttpSession session = httpReq.getSession();
        String username = session.getAttribute("name").toString();
        if((username== null) || (username.trim().length()<=0)){
            httpResp.sendRedirect("/index.html");
            return;
        }

        System.out.printf(">>>>>>>>>>>>>>>> url %s\n", httpReq.getRequestURI().toString());
        System.out.printf(">>>>>>>>>>>>>>>> name %s\n", session.getAttribute("name"));

        // This line is important! If not it doesnt forward the request!
        chain.doFilter(request, response);
        
    }
}
