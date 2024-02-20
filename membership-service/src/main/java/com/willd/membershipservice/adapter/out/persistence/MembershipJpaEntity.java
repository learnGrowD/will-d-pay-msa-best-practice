package com.willd.membershipservice.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "membership")
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MembershipJpaEntity {
    @Id
    @GeneratedValue
    private Long membershipId;

    private String name;

    private String email;

    private String address;

    private Boolean isValid;

    private Boolean isCorp;

    public static MembershipJpaEntity register(
            String name,
            String email,
            String address,
            Boolean isValid,
            Boolean isCorp
    ) {
        return MembershipJpaEntity.builder()
                .name(name)
                .email(email)
                .address(address)
                .isValid(isValid)
                .isCorp(isCorp)
                .build();
    }

    public void modify(
            String name,
            String email,
            String address,
            Boolean isValid,
            Boolean isCorp
    ) {
        if (name != null) setName(name);
        if (email != null) setEmail(email);
        if (address != null) setAddress(address);
        if (isValid != null) setIsValid(isValid);
        if (isCorp != null ) setIsCorp(isCorp);
    }
}
