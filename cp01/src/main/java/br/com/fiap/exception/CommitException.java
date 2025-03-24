package br.com.fiap.exception;

public class CommitException extends Exception{
    public CommitException() {
        super("Erro ao salvar funcionário no banco de dado");
    }
}
