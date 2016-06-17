package at.ac.tuwien.controller.ui;

import at.ac.tuwien.controller.ui.AbstractUiController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController extends AbstractUiController {

    @Override
    protected String getViewDir() {
        return "index";
    }

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/dist/index.html";
    }
}
