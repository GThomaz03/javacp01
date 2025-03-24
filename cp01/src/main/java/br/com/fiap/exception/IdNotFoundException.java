package br.com.fiap.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id) {
        super("Erro: Id não encontrado (" + id + ")");
    }
}
