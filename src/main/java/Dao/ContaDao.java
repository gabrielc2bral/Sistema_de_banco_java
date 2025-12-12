package Dao;

import Entidades.Conta;
import Entidades.Titular;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDao {
    public Conta salvar(Conta conta) {
        String sql = """
            INSERT INTO conta (titular_id, saldo)
            VALUES (?, ?)
            RETURNING id
        """;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1,conta.getTitular().getId());
            ps.setBigDecimal(2, conta.getSaldo());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                conta.setId(rs.getLong("id"));
                System.out.println("Conta criada com sucesso");
            }
            return conta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Conta buscarContaPorId(Long id) {

        String sql = """
                    SELECT c.id, c.saldo, t.nome AS titular, t.cpf
                    FROM conta c
                    INNER JOIN titular t on c.titular_id = t.id
                    WHERE c.id = ?
                """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Titular titular = new Titular(rs.getString("titular"), rs.getString("cpf"));
                Conta conta = new Conta(titular, rs.getBigDecimal("saldo"));
                return conta;
            } else return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
