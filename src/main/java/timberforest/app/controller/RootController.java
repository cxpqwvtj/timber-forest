package timberforest.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController {

    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list(Model model) {
        logger.trace("");
        model.addAttribute("message", "aaa");
        return "card/list";
    }
}
