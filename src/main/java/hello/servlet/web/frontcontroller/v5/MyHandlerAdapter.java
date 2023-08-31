package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    //adapter가 해당 controller 처리할 수 있는지 판단
    boolean supports(Object handler);

    //adapter가 실제 controller 호출하고 그 결과 modelView 반환
    ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException;
}
