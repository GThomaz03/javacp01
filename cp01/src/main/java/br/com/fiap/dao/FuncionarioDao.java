package br.com.fiap.dao;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.DataBaseException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.exception.ValidationException;

import javax.persistence.*;
import java.util.List;

public interface FuncionarioDao {
    void salvar(Funcionario funcionario) throws CommitException, ValidationException;

    Funcionario buscarPorId(Long id) throws IdNotFoundException;

    List<Funcionario> listarTodos() throws DataBaseException;

    void atualizar(Funcionario funcionario) throws CommitException, IdNotFoundException;

    void deletar(Long id) throws IdNotFoundException, CommitException;
}
