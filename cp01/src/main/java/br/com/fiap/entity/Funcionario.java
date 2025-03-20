package br.com.fiap.entity;

public class Funcionario{
    private String nome;
    private double horasTrabalhadas;
    private double valorHora;

    public Funcionario(String nome, double horasTarbalhadas, double valorHora) {
        this.nome = nome;
        this.horasTrabalhadas = horasTarbalhadas;
        this.valorHora = valorHora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getHorasTarbalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTarbalhadas(double horasTarbalhadas) {
        this.horasTrabalhadas = horasTarbalhadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double calcularSalario(){
        return this.horasTrabalhadas*this.valorHora;
    }

    @Override
    public String toString() {
        return "Funcionario " + '\n' +
                "nome='" + nome + '\n' +
                ", horasTarbalhadas=" + horasTrabalhadas +
                ", valorHora=" + valorHora;
    }


}
