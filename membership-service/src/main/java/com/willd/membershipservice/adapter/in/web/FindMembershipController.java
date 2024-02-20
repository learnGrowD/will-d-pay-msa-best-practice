package com.willd.membershipservice.adapter.in.web;

import com.willd.common.TaskProducer;
import com.willd.common.WebAdapter;
import com.willd.common.tasks.DecreaseMoneyTask;
import com.willd.common.tasks.IncreaseMoneyTask;
import com.willd.common.tasks.TaskCategory;
import com.willd.membershipservice.application.port.in.FindMembershipCommand;
import com.willd.membershipservice.application.port.in.FindMembershipUseCase;
import com.willd.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {
    private final FindMembershipUseCase findMembershipUseCase;
    private final TaskProducer taskProducer;

    @GetMapping(path = "/membership/{membershipId}")
    public Membership findMembership(@PathVariable Long membershipId) {
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();
        return findMembershipUseCase.findMembership(command);
    }

    @GetMapping(path = "/increase")
    public String test1() {
        IncreaseMoneyTask task = IncreaseMoneyTask.builder()
                .uuid(UUID.randomUUID())
                .targetMembershipId(4L)
                .increaseMoneyAmount(15000)
                .build();

        taskProducer.sendTask(TaskCategory.INCREASE_MONEY.getDomain(), task);
        return "async increase";
    }

    @GetMapping(path = "/decrease")
    public String test2() {
        DecreaseMoneyTask task = DecreaseMoneyTask.builder()
                .uuid(UUID.randomUUID())
                .targetMembershipId(4L)
                .decreaseMoneyAmount(15000)
                .build();

        taskProducer.sendTask(TaskCategory.DECREASE_MONEY.getDomain(), task);
        return "async decrease";
    }
}