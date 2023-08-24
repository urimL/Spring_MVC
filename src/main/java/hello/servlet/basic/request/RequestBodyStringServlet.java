package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet",urlPatterns = "/request-body-string")

public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //msg body의 내용을 byte 코드로 바로 얻을 수 있다.
        ServletInputStream inputStream = req.getInputStream();
        //byte를 string으로 변환
        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("msgBody = " + msgBody);

        resp.getWriter().write("ok");
    }
}
