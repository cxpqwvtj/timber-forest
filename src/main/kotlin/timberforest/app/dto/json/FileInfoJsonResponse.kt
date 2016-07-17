package timberforest.app.dto.json

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by masahiro on 2016/07/17.
 */
class FileInfoJsonResponse(val fileSize: Long, private val updateDate: Long) {
    val formattedUpdateDate: String
        get() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Date(updateDate))
}