package timberforest.app.dto.json

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by masahiro on 2016/07/17.
 */
class FileInfoJsonResponse(private val file: File) {
    private val FILE_NAME_DELIMITER = "_"
    val hostName: String
        get() = file.parentFile.name
    val appName: String
        get() = file.nameWithoutExtension.split(FILE_NAME_DELIMITER)[0]
    val createdDate: String
        get() {
            val fileNameSplit = file.nameWithoutExtension.split(FILE_NAME_DELIMITER)
            return if (fileNameSplit.size >= 3) "${fileNameSplit[1]} ${fileNameSplit[2]}" else ""
        }
    val fileSize: Long
        get() = file.length()
    val uploadDate: String
        get() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Date(file.lastModified()))
}