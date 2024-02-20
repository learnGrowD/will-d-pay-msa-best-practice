package com.willd.tastconsumer.adapter.out.persistence;

import com.willd.common.PersistenceAdapter;
import com.willd.tastconsumer.application.port.out.RequestTaskHistoryPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RequestRequestTaskHistoryPersistenceAdapter implements RequestTaskHistoryPort {
    private final SpringDataTaskHistoryRepository taskHistoryRepository;

    @Override
    public RequestTaskHistoryJpaEntity create(String taskKey) {
        RequestTaskHistoryJpaEntity entity = RequestTaskHistoryJpaEntity.generate(taskKey);
        return taskHistoryRepository.save(entity);
    }

    @Override
    public void modify(RequestTaskHistoryJpaEntity entity) {
        taskHistoryRepository.save(entity);
    }
}
