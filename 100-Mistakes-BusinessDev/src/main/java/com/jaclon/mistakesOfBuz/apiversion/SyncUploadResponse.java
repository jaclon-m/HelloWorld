package com.jaclon.mistakesOfBuz.apiversion;

import lombok.Data;

/**
 *
 * @author jaclon
 * @since 2021/8/5 20:37
 */
@Data
public class SyncUploadResponse {
    private String downloadUrl;
    private String thumbnailDownloadUrl;
}