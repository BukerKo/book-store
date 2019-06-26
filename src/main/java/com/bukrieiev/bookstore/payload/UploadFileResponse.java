package com.bukrieiev.bookstore.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public class UploadFileResponse {
    @Getter
    @Setter
    @NonNull
    private String fileName;

    @Getter
    @Setter
    @NonNull
    private String fileDownloadUri;

    @Getter
    @Setter
    @NonNull
    private String fileType;

    @Getter
    @Setter
    @NonNull
    private long size;
}
