package com.agenda.refatoracao.exception;

import java.time.LocalDateTime;

public record ErroResponse(
    int status,
    String mensagem,
    LocalDateTime timestamp
) {
}
