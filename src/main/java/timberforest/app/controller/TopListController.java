package timberforest.app.controller;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import timberforest.app.dto.json.LogFileListJsonResponse;

@RestController
@RequestMapping("/api")
public class TopListController {

    private static final Logger logger = LoggerFactory.getLogger(TopListController.class);

    @RequestMapping(value = "/filelist", method = RequestMethod.GET)
    public LogFileListJsonResponse index() {
        logger.trace("index");
        Path currentPath = new File(System.getProperty("user.dir")).toPath();
        LogFileListJsonResponse response = new LogFileListJsonResponse();
        for (File file : nestedFiles(currentPath.resolve("timber").toFile())) {
            response.getLogFileList().add(currentPath.relativize(file.toPath()).toString());
        }
        return response;
    }

    private List<File> nestedFiles(File rootDir) {
        File[] files = rootDir.listFiles();
        List<File> fileList = new ArrayList<>();
        if (files == null) {
            return fileList;
        } else {
            for (File file : files) {
                if (file.isDirectory()) {
                    fileList.addAll(nestedFiles(file));
                } else {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }
}
