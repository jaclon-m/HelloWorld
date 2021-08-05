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
public class SyncQueryUploadTaskResponse {
    private final String taskId;
    private String downloadUrl;
    private String thumbnailDownloadUrl;
}