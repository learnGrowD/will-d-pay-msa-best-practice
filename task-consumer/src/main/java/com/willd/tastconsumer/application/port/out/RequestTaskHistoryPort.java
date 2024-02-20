package com.willd.tastconsumer.application.port.out;

import com.willd.tastconsumer.adapter.out.persistence.RequestTaskHistoryJpaEntity;

public interface RequestTaskHistoryPort {

    RequestTaskHistoryJpaEntity create(String taskKey);

    void modify(
            RequestTaskHistoryJpaEntity entity
    );
}
