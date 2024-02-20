package com.willd.moneyservice.application.port.in;

import com.willd.moneyservice.application.port.in.command.DecreaseMemberMoneyCommand;
import com.willd.moneyservice.application.port.in.command.IncreaseMemberMoneyCommand;
import com.willd.domain.money.MemberMoney;

public interface MoneyUserCase {
    MemberMoney getMoneyInfo(Long membershipId);
    MemberMoney increaseMemberMoney(IncreaseMemberMoneyCommand command);

    MemberMoney decreaseMemberMoney(DecreaseMemberMoneyCommand command);
}
