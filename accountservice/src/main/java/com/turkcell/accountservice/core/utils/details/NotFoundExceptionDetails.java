package com.turkcell.accountservice.core.utils.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundExceptionDetails {
    private String title = "Not Found Exception";
    private String message;
}
