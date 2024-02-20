package com.willd.moneyservice.adapter.in.web;

import com.willd.moneyservice.application.port.in.command.DecreaseMemberMoneyCommand;
import com.willd.moneyservice.application.port.in.command.IncreaseMemberMoneyCommand;
import com.willd.moneyservice.application.port.in.MoneyUserCase;
import com.willd.domain.money.MemberMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyUserCase moneyUserCase;

    @GetMapping("money/membership/{membershipId}")
    public MemberMoney getMemberMoney(@PathVariable Long membershipId) {
        return moneyUserCase.getMoneyInfo(membershipId);
    }

    @PostMapping("money/increase")
    public MemberMoney increase(@RequestBody IncreaseMemberMoneyRequest request) {
        IncreaseMemberMoneyCommand command = IncreaseMemberMoneyCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .increaseMoneyAmount(request.getIncreaseMoneyAmount())
                .build();
        return moneyUserCase.increaseMemberMoney(command);
    }

    @PostMapping("money/decrease")
    public MemberMoney decrease(@RequestBody DecreaseMemberMoneyRequest request) {
        DecreaseMemberMoneyCommand command = DecreaseMemberMoneyCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .decreaseMoneyAmount(request.getDecreaseMoneyAmount())
                .build();

        return moneyUserCase.decreaseMemberMoney(command);
    }
}
