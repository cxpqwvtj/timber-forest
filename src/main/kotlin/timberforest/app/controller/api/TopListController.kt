package timberforest.app.controller.api

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import timberforest.app.dto.json.FileInfoJsonResponse
import timberforest.app.dto.json.LogFileJsonResponse
import timberforest.app.dto.json.RootJsonResponse
import java.io.File
import java.util.*

/**
 * Created by masahiro on 2016/04/14.
 */
@RestController
@RequestMapping("/api")
open class TopListController {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = "/timber/list")
    open fun index(): RootJsonResponse {
        val currentPath = File(System.getProperty("user.dir")).toPath()
        val logFileInfo = mutableMapOf<String, FileInfoJsonResponse>()
        for (file in nestedFiles(currentPath.resolve("timber").toFile())) {
            val filePath = currentPath.relativize(file.toPath()).toString()
            val fileInfo = FileInfoJsonResponse(file)
            logFileInfo.put(filePath, fileInfo)
        }

        return RootJsonResponse(LogFileJsonResponse(logFileInfo))
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