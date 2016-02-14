package timberforest.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import timberforest.app.dto.json.LogFileJsonResponse;

@RestController
@RequestMapping("/api")
public class LogFileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(LogFileUploadController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public LogFileJsonResponse upload(@RequestPart Map<String, Object> fileInfo, @RequestParam MultipartFile zipLogFile)
            throws IOException {
        logger.trace(fileInfo.toString() + zipLogFile.toString());
        Path userDir = new File(System.getProperty("user.dir")).toPath();
        Path logDir = userDir.resolve("timber/device-name/");
        if (logDir.toFile().exists()) {
            // NOP
        } else if (logDir.toFile().mkdirs()) {
            // ログ保存ディレクトリ作成成功
        } else {
            logger.warn("ログ保存ディレクトリの作成に失敗しました");
            LogFileJsonResponse response = new LogFileJsonResponse();
            response.setSuccess(false);
            return response;
        }
        // TODO:ファイル名はリクエストから取得
        File logFile = logDir.resolve("logFileNmae.zip").toFile();
        if (logFile.exists()) {
            logger.warn("ログログファイルがすでに存在します");
            LogFileJsonResponse response = new LogFileJsonResponse();
            response.setSuccess(false);
            return response;
        }
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(logFile))) {
            bos.write(zipLogFile.getBytes());
            bos.flush();
        }

        // TODO:リクエストで来たJSONも保存する
        logger.info(String.format("ファイルを保存しました。%s", logFile.getName()));
        LogFileJsonResponse response = new LogFileJsonResponse();
        response.setSuccess(true);
        return response;
    }
}
