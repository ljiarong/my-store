package interceptors;/**
 * ClassName: LoginInterceptor
 * Package: interceptors
 */

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: my-store
 *
 * @description: 检查session中是否有数据，没有则跳转到登录视图
 *
 * @author: ljr
 *
 * @create: 2023-09-14 23:06
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userInfo = request.getSession().getAttribute("userInfo");
        if (userInfo!=null){
            return true;  //已经登录
        }
        else {
            response.sendRedirect(request.getContextPath()+"/index.html");
            return false;
        }
    }
}
