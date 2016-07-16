package timberforest.app.controller.api

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import timberforest.app.dto.json.LogFileListJsonResponse
import timberforest.app.dto.json.RootJsonResponse
import java.io.File
import java.util.*

/**
 * Created by masahiro on 2016/04/14.
 */
@RestController
@RequestMapping("/api")
class TopListController {

    private val logger = LoggerFactory.getLogger(TopListController::class.java)

    @RequestMapping(value = "/filelist", method = arrayOf(RequestMethod.GET))
    fun index(): RootJsonResponse {
        logger.trace("index")
        val currentPath = File(System.getProperty("user.dir")).toPath()
        val response = LogFileListJsonResponse()
        for (file in nestedFiles(currentPath.resolve("timber").toFile())) {
            response.logFileList.add(currentPath.relativize(file.toPath()).toString())
        }

        return RootJsonResponse(response)
    }

    private fun nestedFiles(rootDir: File): List<File> {
        val files = rootDir.listFiles()
        val fileList = ArrayList<File>()
        if (files == null) {
            return fileList
        } else {
            for (file in files) {
                if (file.isDirectory) {
                    fileList.addAll(nestedFiles(file))
                } else {
                    fileList.add(file)
                }
            }
        }
        return fileList
    }
}