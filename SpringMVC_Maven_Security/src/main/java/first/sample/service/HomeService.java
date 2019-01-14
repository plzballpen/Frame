package first.sample.service;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import first.sample.domain.UserVO;

/*@Service*/
public class HomeService {
	private static final Logger logger = LoggerFactory
			.getLogger(HomeService.class);

	@PreAuthorize("#userVO.user_name == authentication.name or hasRole(‘ROLE_ADMIN')")
	public void getUser(UserVO userVO) {
		logger.info("getUser success");
	}
}
