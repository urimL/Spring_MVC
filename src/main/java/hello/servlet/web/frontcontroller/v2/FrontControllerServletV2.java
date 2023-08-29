package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // Mapping 정보 <매핑 URL, 호출될 컨트롤러>
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 각 패턴이 오면 미리 저장된 controller를 꺼내 쓸 수 있다.
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //요청된 URI 조회
        String requestURI = req.getRequestURI();

        //요청된 URI와 맞는 컨트롤러를 ControllerMap에서 찾는다.
        ControllerV2 controller = controllerMap.get(requestURI);

        //해당하는 controller가 없는 경우
        if(controller == null)
        {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // V1과 달리 MyView 객체 반환
        MyView view = controller.process(req, resp);
        view.render(req,resp);
    }
}