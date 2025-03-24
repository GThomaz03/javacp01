package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SENIOR")
public class FuncionarioSenior extends Funcionario {

    @Column(name = "bonus")
    private double bonus = 500;

    public FuncionarioSenior(String nome, double horasTarbalhadas, double valorHora) {
        super(nome, horasTarbalhadas, valorHora);
    }


    @Override
    public double calcularSalario() {
        int qntBonus = (int) (getHorasTrabalhadas()/15);
        return super.calcularSalario()+(this.bonus * qntBonus);
    }

    @Override
    public void imprimirInformacao() {
        System.out.println("Funcionario Sênior: " + getNome());
        System.out.println("Salário com bônus: " + calcularSalario());
    }
}
