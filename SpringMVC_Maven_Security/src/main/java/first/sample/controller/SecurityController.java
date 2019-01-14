package first.sample.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.sample.domain.UserVO;
import first.sample.service.HomeService;

@Controller
public class SecurityController {
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

	//@Autowired
	//HomeService homeService;
	
	/** * Simply selects the home view to render by returning its name. */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, ModelAndView model,@RequestParam(value="user", defaultValue="", required=true) String user) {
		ModelAndView mv = new ModelAndView("home");
		
		// 파라메터 값을 얻어 삽입 
		UserVO userVO = new UserVO(); 
		userVO.setUser_name(user); 
		//homeService.getUser(userVO);
		
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		//mv.addObject("serverTime", formattedDate);
		return mv;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/admin/test", method = RequestMethod.GET)
	public String admin_test() {
		return "guest";
	}
	

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Principal principal) {
		//첫번째 방법
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("auth.toString="+auth.toString());
		
		//두번째 방법
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("user.toString()="+user.toString());
		
		// 세번째 방법 
		logger.info(principal.toString()); 
		return "home";
	}

}
