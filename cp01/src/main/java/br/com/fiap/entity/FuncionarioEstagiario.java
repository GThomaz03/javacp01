package br.com.fiap.entity;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ESTAGIÁRIO")
public class FuncionarioEstagiario extends Funcionario{

    @Column(name = "desconto")
    private double desconto = 0.8;

    public FuncionarioEstagiario(String nome, double horasTrabalhadas, double valorHora) {
        super(nome, horasTrabalhadas, valorHora);
    }


    @Override
    public double calcularSalario() {
        return super.calcularSalario()*desconto;
    }

    @Override
    public void imprimirInformacao() {
        System.out.println("Funcionário estagiário: " + getNome());
        System.out.println("Salario com desconto de benefícios: "+ calcularSalario());
    }
}
