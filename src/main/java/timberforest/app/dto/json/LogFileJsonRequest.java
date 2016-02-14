package timberforest.app.dto.json;

import lombok.Data;

@Data
public class LogFileJsonRequest {
    private String name;
    private String model;
    private String localizeModel;
    private String systemName;
    private String systemVersion;
}
