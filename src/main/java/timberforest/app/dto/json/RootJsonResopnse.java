package timberforest.app.dto.json;

import lombok.Data;

@Data
public class RootJsonResopnse {
    private boolean success;
    private String errorMessage = "";
}
