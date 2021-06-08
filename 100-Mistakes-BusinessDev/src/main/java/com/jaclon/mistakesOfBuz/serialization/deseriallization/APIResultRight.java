package com.jaclon.mistakesOfBuz.serialization.deseriallization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/6/8 12:13
 */
@Data
public class APIResultRight {
    private boolean success;
    private int code;

    public APIResultRight() {
    }

    @JsonCreator
    public APIResultRight(@JsonProperty("code") int code) {
        this.code = code;
        if (code == 2000) success = true;
        else success = false;
    }
}
