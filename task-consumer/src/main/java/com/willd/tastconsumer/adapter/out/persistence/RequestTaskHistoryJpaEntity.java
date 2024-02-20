package com.willd.tastconsumer.adapter.out.persistence;

import com.willd.tastconsumer.domain.RequestTaskHistoryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "task_history")
@Entity
@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestTaskHistoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskKey;

    @Enumerated(EnumType.STRING)
    private RequestTaskHistoryStatus status;

    private String uuid;

    private LocalDateTime createdAt;

    public static RequestTaskHistoryJpaEntity generate(
            String taskKey
    ) {
        return RequestTaskHistoryJpaEntity.builder()
                .taskKey(taskKey)
                .status(RequestTaskHistoryStatus.REQUEST)
                .uuid("")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
