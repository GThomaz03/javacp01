package br.com.fiap;

import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.FuncionarioDaoImpl;
import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.FuncionarioEstagiario;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.entity.TabelaFuncionarios;
import br.com.fiap.exception.CommitException;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws CommitException {
        FuncionarioDao dao = new FuncionarioDaoImpl();

        Funcionario f1 = new Funcionario("Ana", 40, 50);
        Funcionario f2 = new FuncionarioSenior("Carlos", 45, 60);
        Funcionario f3 = new FuncionarioEstagiario("Pedro", 30, 20);



        dao.salvar(f1);
        dao.salvar(f2);
        dao.salvar(f3);

        System.out.println("Gerando SQL automático: " + TabelaFuncionarios.gerarSQL(Funcionario.class));

        List<Funcionario> funcionarioArray = dao.listarTodos();
        for (Funcionario f : funcionarioArray) {
            f.imprimirInformacao();
        }
    }
}