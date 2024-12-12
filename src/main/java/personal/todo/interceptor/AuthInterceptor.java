package personal.todo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import personal.todo.controller.TodoController;

public class AuthInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    private String getLoggedUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("AuthInterceptor.preHandle");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");

        if (userName == null) {
            userName = getLoggedUserName();
            if(userName != null) { // prevent null pointer exception
                session.setAttribute("userName", userName); // Set the username in the session
            }
        }
        return true; // Continue with the request processing

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) { // Check if a ModelAndView exists
            String userName = (String) modelAndView.getModel().get("userName");
            logger.info("AuthInterceptor.postHandle {}", userName);
            if (userName == null) {
                userName = getLoggedUserName();
                modelAndView.addObject("userName", userName);
            }
        }
    }
}
