package com.dimathicc.catsgram.exceptions;

import lombok.*;

@Getter
@AllArgsConstructor
public class IncorrectParameterException extends RuntimeException {
    private final String parameter;
}
