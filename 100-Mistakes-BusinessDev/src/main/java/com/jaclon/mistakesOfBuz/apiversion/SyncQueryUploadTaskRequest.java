package com.jaclon.mistakesOfBuz.apiversion;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author jaclon
 * @since 2021/8/5 20:36
 */


@Data
@RequiredArgsConstructor
public class SyncQueryUploadTaskRequest {
    private final String taskId;
}