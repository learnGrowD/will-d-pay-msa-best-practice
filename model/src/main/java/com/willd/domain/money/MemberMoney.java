package com.willd.domain.money;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 행위의 대한 과정과, 그 행위의 결과는 DB에 저장한다.
// 과정의 기록이, 결과가 첨예하면 첨예 할수록 비지니스로직의 복잡도가 올라간다.
// 그러나 시스템은 견고해진다.
// 그리고 항상 비지니스 로직이 실행이 되었을때 효용성!!!을 항시 생각해야 한다.
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {
    private Long memberMoneyId;

    private Long membershipId;

    private Integer balance;

    public static MemberMoney generate(
            Long memberMoneyId,
            Long membershipId,
            Integer balance
    ) {
        return new MemberMoney(
                memberMoneyId,
                membershipId,
                balance
        );
    }
}
