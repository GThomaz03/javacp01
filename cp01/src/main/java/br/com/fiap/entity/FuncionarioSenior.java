package br.com.fiap.entity;

public class FuncionarioSenior extends Funcionario {
    private double bonus = 500;
    private int qntBonus;

    public FuncionarioSenior(String nome, double horasTarbalhadas, double valorHora) {
        super(nome, horasTarbalhadas, valorHora);
        this.qntBonus = (int) horasTarbalhadas/15;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getQntBonus() {
        return qntBonus;
    }

    public void setQntBonus(int qntBonus) {
        this.qntBonus = qntBonus;
    }

    @Override
    public double calcularSalario() {
        double bonus = this.bonus*this.qntBonus;
        return super.calcularSalario()+bonus;
    }

    @Override
    public String toString() {
        return super.toString()+"  bonus=" + this.bonus + " quantidade de bonus=" + qntBonus;
    }
}
