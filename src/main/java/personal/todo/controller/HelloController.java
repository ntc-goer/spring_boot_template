package personal.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello")
    public String SayHello(@RequestParam String name, ModelMap map){
        System.out.println("Hello " + name);
        logger.debug("Hello {}", name);
        logger.info("Hello {}", name);
        logger.warn("Hello {}", name);
        map.put("name", name);
        return "hello";
    }
}
