package br.com.fiap.entity;

public class FuncionarioEstagiario extends Funcionario{
    public FuncionarioEstagiario(String nome, double horasTarbalhadas, double valorHora) {
        super(nome, horasTarbalhadas, valorHora);
    }


    @Override
    public double calcularSalario() {
        return super.calcularSalario();
    }
}
