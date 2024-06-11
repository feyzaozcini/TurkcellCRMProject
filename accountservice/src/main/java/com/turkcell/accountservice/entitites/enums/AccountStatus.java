package com.turkcell.accountservice.entitites.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    SUSPENDED,
    DELETED
}
