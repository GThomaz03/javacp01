package br.com.fiap.exception;

public class DataBaseException extends RuntimeException {
    public DataBaseException() {
        super("Erro ao listar funcionários");
    }
}
