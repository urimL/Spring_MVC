package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // servlet 기술이 필요 없다.
    ModelView process(Map<String, String> paramap);
}
