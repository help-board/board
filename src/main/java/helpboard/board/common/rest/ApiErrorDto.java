package helpboard.board.common.rest;

import lombok.Data;

@Data
public class ApiErrorDto {

    private int status;
    private String statusMessage;
    private String message;
}
