package first.sample.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {
    Logger log = Logger.getLogger(this.getClass());
    /* 
    @RequestMapping(value="/sample/openSampleList.do")
    public ModelAndView openSampleList(Map<String,Object> commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/test");
        log.debug("인터셉트 테스트");

        return mv;
    }
    
    @RequestMapping(value = "/sample/admin.do", method = RequestMethod.GET)
    public String admin() {
         return "admin";
    }
    */
}