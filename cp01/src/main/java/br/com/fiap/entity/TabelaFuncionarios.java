package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;

public class TabelaFuncionarios {
    public static String gerarSQL(Class<?> classe) {
        if (!classe.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("A classe não possui a anotação @Table.");
        }

        Table tabela = classe.getAnnotation(Table.class);
        StringBuilder sql = new StringBuilder("SELECT ");

        Field[] campos = classe.getDeclaredFields();
        for (int i = 0; i < campos.length; i++) {
            if (campos[i].isAnnotationPresent(Column.class)) {
                Column coluna = campos[i].getAnnotation(Column.class);
                sql.append(coluna.name()); // Usa o nome da coluna definido na anotação @Column
            } else {
                sql.append(campos[i].getName()); // Usa o nome do atributo se não houver @Column
            }

            if (i < campos.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(" FROM ").append(tabela.name());
        return sql.toString();
    }
}
