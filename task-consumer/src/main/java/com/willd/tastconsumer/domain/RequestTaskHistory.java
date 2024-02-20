package com.willd.tastconsumer.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestTaskHistory {
    private Long taskHistoryId;

    private String taskKey;

    private RequestTaskHistoryStatus status;

    private String uuid;

    private LocalDateTime createdAt;

    public static RequestTaskHistory generate(
            Long taskHistoryId,
            String taskKey,
            RequestTaskHistoryStatus status,
            String uuid,
            LocalDateTime createdAt
    ) {
        return RequestTaskHistory.builder()
                .taskHistoryId(taskHistoryId)
                .taskKey(taskKey)
                .status(status)
                .uuid(uuid)
                .createdAt(createdAt)
                .build();
    }
}
