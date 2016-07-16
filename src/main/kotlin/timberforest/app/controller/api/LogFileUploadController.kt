package timberforest.app.controller.api

import org.slf4j.LoggerFactory
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import timberforest.app.dto.json.ErrorJsonResponse
import timberforest.app.dto.json.LogFileJsonRequest
import timberforest.app.dto.json.RootJsonResponse
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by masahiro on 2016/04/14.
 */
@RestController
@RequestMapping("/api")
class LogFileUploadController {

    private val logger = LoggerFactory.getLogger(LogFileUploadController::class.java)

    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.POST))
    fun upload(@RequestPart fileInfo: LogFileJsonRequest,
               @RequestParam zipLogFile: MultipartFile): RootJsonResponse {
        val userDir = File(System.getProperty("user.dir")).toPath()
        if (StringUtils.isEmpty(fileInfo.name)) {
            val message = "デバイス名がありません" + fileInfo.toString()
            logger.warn(message)
            return RootJsonResponse(ErrorJsonResponse("1", message))
        }
        val logDir = userDir.resolve(String.format("timber/%s/", fileInfo.name))
        if (logDir.toFile().exists()) {
            // NOP
            logger.trace(String.format("ディレクトリ存在確認 %s", logDir.toFile().path))
        } else if (logDir.toFile().mkdirs()) {
            // ログ保存ディレクトリ作成成功
            logger.info(String.format("ディレクトリ作成 %s", logDir.toFile().path))
        } else {
            val message = "ログ保存ディレクトリの作成に失敗しました" + logDir.normalize().toString()
            logger.warn(message)
            return RootJsonResponse(ErrorJsonResponse("1", message))
        }
        // TODO:ファイル名はリクエストから取得
        val logFile = logDir.resolve(zipLogFile.originalFilename).toFile()
        if (logFile.exists()) {
            val message = "ログファイルがすでに存在します " + zipLogFile.originalFilename
            logger.warn(message)
            return RootJsonResponse(ErrorJsonResponse("1", message))
        }
        try {
            BufferedOutputStream(FileOutputStream(logFile)).use { bos ->
                bos.write(zipLogFile.bytes)
                bos.flush()
            }
        } catch (e: IOException) {
            val message = "ファイルの保存に失敗しました " + logFile.name
            logger.warn(message)
            return RootJsonResponse(ErrorJsonResponse("1", message))
        }

        // TODO:リクエストで来たJSONも保存する
        logger.info(String.format("ファイルを保存しました。%s", logFile.name))
        return RootJsonResponse(Object())
    }
}