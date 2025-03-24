package br.com.fiap.dao;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.TabelaFuncionarios;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.DataBaseException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.exception.ValidationException;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDaoImpl implements FuncionarioDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
    private EntityManager em = emf.createEntityManager();

    @Override
    public void salvar(Funcionario funcionario) throws CommitException, ValidationException {
        EntityTransaction transaction = null;
        validarFuncionario(funcionario);
        try {
            transaction = em.getTransaction();
            transaction.begin();

            em.persist(funcionario);

            transaction.commit();  // ✅ Faz commit apenas se der certo
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // ✅ Só faz rollback se a transação ainda estiver ativa
            }
            throw new DataBaseException();
        }
    }

    @Override
    public Funcionario buscarPorId(Long id) throws IdNotFoundException {
        Funcionario funcionario = em.find(Funcionario.class, id);
        if (funcionario == null) {
            throw new IdNotFoundException(id);
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> listarTodos() throws DataBaseException {
        EntityManager em = emf.createEntityManager();
        List<Funcionario> funcionarios;

        try {
            TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
            funcionarios = query.getResultList();
        } catch (NoResultException e) {
            System.out.println("Nenhum funcionário encontrado no banco.");
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new DataBaseException();
        } catch (Exception e) {
            throw new DataBaseException();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return funcionarios;
    }

    @Override
    public void atualizar(Funcionario funcionario) throws CommitException, IdNotFoundException, ValidationException {
        validarFuncionario(funcionario);
        if (buscarPorId(funcionario.getId()) == null) {
            throw new IdNotFoundException(funcionario.getId());
        }
        try {
            em.getTransaction().begin();
            em.merge(funcionario);
            em.getTransaction().commit();
            System.out.println("UPDATE SQL: " + gerarUpdateSQL(funcionario));
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new CommitException();
        }
    }

    @Override
    public void deletar(Long id) throws IdNotFoundException, CommitException {
        Funcionario funcionario = buscarPorId(id);
        if (funcionario == null) {
            throw new IdNotFoundException(id);
        }
        try {
            em.getTransaction().begin();
            em.remove(funcionario);
            em.getTransaction().commit();
            System.out.println("DELETE SQL: DELETE FROM TAB_FUNCIONARIO WHERE id_funcionario = " + id);
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new CommitException();
        }
    }

    private void validarFuncionario(Funcionario funcionario) throws ValidationException {
        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new ValidationException("Nome do funcionário não pode ser vazio.");
        }
        if (funcionario.getHorasTrabalhadas() < 0) {
            throw new ValidationException("Horas trabalhadas não pode ser negativa.");
        }
        if (funcionario.getValorHora() <= 0) {
            throw new ValidationException("Valor por hora deve ser maior que zero.");
        }
    }

    private String gerarInsertSQL(Funcionario funcionario) {
        return String.format("INSERT INTO TAB_FUNCIONARIO (nome, horas_trabalhadas, valor_por_hora) VALUES ('%s', %d, %.2f);",
                funcionario.getNome(), funcionario.getHorasTrabalhadas(), funcionario.getValorHora());
    }

    private String gerarUpdateSQL(Funcionario funcionario) {
        return String.format("UPDATE TAB_FUNCIONARIO SET nome = '%s', horas_trabalhadas = %d, valor_por_hora = %.2f WHERE id_funcionario = %d;",
                funcionario.getNome(), funcionario.getHorasTrabalhadas(), funcionario.getValorHora(), funcionario.getId());
    }
}
