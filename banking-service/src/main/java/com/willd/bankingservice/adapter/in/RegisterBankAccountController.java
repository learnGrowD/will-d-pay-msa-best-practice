package com.willd.bankingservice.adapter.in;

import com.willd.bankingservice.application.port.in.GetBankAccountByBankAccountNumberCommand;
import com.willd.bankingservice.application.port.in.GetBankAccountByMembershipIdCommand;
import com.willd.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.willd.bankingservice.application.port.in.RegisteredUseCase;
import com.willd.domain.bank.RegisteredBankAccount;
import com.willd.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisteredUseCase registeredUseCase;

    @GetMapping(path = "/banking/account/membership-id/{membershipId}")
    public RegisteredBankAccount getRegisteredBankAccount(@PathVariable Long membershipId) {
        GetBankAccountByMembershipIdCommand command = GetBankAccountByMembershipIdCommand.builder()
                .membershipId(membershipId)
                .build();
        return registeredUseCase.getRegisterBankAccount(command);
    }

    @GetMapping(path = "banking/account/bank-account-number/{bankAccountNumber}")
    public RegisteredBankAccount getRegisteredBankAccount(@PathVariable String bankAccountNumber) {
        GetBankAccountByBankAccountNumberCommand command = GetBankAccountByBankAccountNumberCommand.builder()
                .bankAccountNumber(bankAccountNumber)
                .build();
        return registeredUseCase.getRegisteredBankAccount(command);
    }

    @PostMapping(path = "/banking/account/register")
    public RegisteredBankAccount register(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankAccountNumber(request.getBankAccountNumber())
                .bankName(request.getBankName())
                .build();
        return registeredUseCase.registerBankAccount(command);
    }
}
