package com.willd.moneyservice.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "member_money")
@Entity
@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoneyJpaEntity {
    @Id
    @GeneratedValue
    private Long memberMoneyId;

    private Long membershipId;

    private Integer balance;

    public static MemberMoneyJpaEntity register(
        Long membershipId
    ) {
        return MemberMoneyJpaEntity.builder()
                .membershipId(membershipId)
                .balance(0)
                .build();
    }

    public void modify(
            String membershipId,
            Integer balance
    ) {
        if (membershipId != null) setMemberMoneyId(memberMoneyId);
        if (balance != null) setBalance(balance);
    }
}
