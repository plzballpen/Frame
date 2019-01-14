package first.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class SigninSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(SigninSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws ServletException, IOException {

		// 응답 방식을 accept에 담아 형식을 확인한다.
		String accept = request.getHeader("accept");

		if (StringUtils.indexOf(accept, "html") > -1) {
			
			logger.debug("로그인성공하였습니다.");
			
			super.onAuthenticationSuccess(request, response, auth);
		} else if (StringUtils.indexOf(accept, "xml") > -1) {
			response.setContentType("application/xml");
			response.setCharacterEncoding("utf-8");
			String data = StringUtils.join(new String[] {
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "<response>",
					"<error>false</error>", "<message>로그인하였습니다.</message>",
					"</response>" });
			
			
			logger.debug(data);
			
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} else if (StringUtils.indexOf(accept, "json") > -1) {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			String data = StringUtils.join(new String[] {
					" { \"response\" : {", " \"error\" : false , ",
					" \"message\" : \"로그인하였습니다.\" ", "} } " });
			
			logger.debug(data);
			
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		}
	}
}
