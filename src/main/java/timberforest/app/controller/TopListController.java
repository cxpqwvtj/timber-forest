package timberforest.app.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/list")
public class TopListController {

    private static final Logger logger = LoggerFactory.getLogger(TopListController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> index() {
        logger.trace("index");
        return new LinkedHashMap<>();
    }
}
