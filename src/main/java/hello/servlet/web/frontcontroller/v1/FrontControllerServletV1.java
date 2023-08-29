package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v1/* : v1 하위에 들어오는 어떤 url이 들어와도 일단 이 서블릿 호출
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // Mapping 정보 <매핑 URL, 호출될 컨트롤러>
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 각 패턴이 오면 미리 저장된 controller를 꺼내 쓸 수 있다.
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("FrontControolerServletV1.service");

        //요청된 URI 조회
        String requestURI = req.getRequestURI();

        //요청된 URI와 맞는 컨트롤러를 ControllerMap에서 찾는다.
        ControllerV1 controller = controllerMap.get(requestURI);

        //해당하는 controller가 없는 경우
        if(controller == null)
        {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 조회가 잘 된 경우, interface 내의 process 호출
        controller.process(req,resp);
    }
}