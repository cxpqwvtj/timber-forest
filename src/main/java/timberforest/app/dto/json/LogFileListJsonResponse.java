package timberforest.app.dto.json;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masahiro on 2016/03/25.
 */
@Data
public class LogFileListJsonResponse extends RootJsonResopnse {
    private List<String> logFileList = new ArrayList<>();
}
