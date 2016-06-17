package at.ac.tuwien.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController extends AbstractUiController {
    @Override
    protected String getViewDir() {
        return "login";
    }

    /**
     * displays the login page
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showLogin() {
        return createViewPath("login");
    }

    /**
     * shows the login page and displays an error message
     * @return
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String showLoginError(Model model) {
        model.addAttribute("loginError", true);
        return createViewPath("login");
    }
}
