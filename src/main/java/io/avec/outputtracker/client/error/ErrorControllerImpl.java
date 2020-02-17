package io.avec.outputtracker.client.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerImpl implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        var errorCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        var errorExceptionMsg = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        var errorReason = HttpStatus.valueOf(Integer.parseInt(errorCode.toString())).getReasonPhrase();

        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorReason", errorReason);
        model.addAttribute("errorExceptionMsg", errorExceptionMsg != null ? errorExceptionMsg.toString() : "");
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
