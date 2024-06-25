package com.viniciusspring.membership.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Não é possível excluir pois há entidades relacionadas!";
    }
}
