package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}
	//사전처리나 사후처리 할 일이 있으면 doFilter()메서드에서 구현한다.
	//ex)로그인유무 인코딩 등등
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		//post방식 한글 처리
		request.setCharacterEncoding("utf-8");
		System.out.println("EncodingFilter doFilter()호출됨...");
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
