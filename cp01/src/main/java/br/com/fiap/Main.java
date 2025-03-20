package br.com.fiap;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.FuncionarioSenior;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario("Gabriel", 33, 12.31);

        System.out.println(funcionario.toString()+ " Salario="+funcionario.calcularSalario());

        FuncionarioSenior funcionarioSenior = new FuncionarioSenior("Gabriel", 33, 12.31);

        System.out.println(funcionarioSenior.toString()+" Salario="+funcionarioSenior.calcularSalario());
    }
}