package com.turkcell.accountservice.core.utils.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessExceptionDetails {
    private String title = "Business Exception";
    private String message;
}
