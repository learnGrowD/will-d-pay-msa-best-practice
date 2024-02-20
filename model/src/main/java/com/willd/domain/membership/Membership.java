package com.willd.domain.membership;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private Long membershipId;
    private String name;
    private String email;
    private String address;
    private Boolean isValid;
    private Boolean isCorp;

    public static Membership generate(
            Long membershipId,
            String membershipName,
            String membershipEmail,
            String membershipAddress,
            Boolean membershipIsValid,
            Boolean membershipIsCorp
    ) {
        return new Membership(
                membershipId,
                membershipName,
                membershipEmail,
                membershipAddress,
                membershipIsValid,
                membershipIsCorp
        );
    }
}
