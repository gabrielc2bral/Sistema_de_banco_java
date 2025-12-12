package Dao;

import Entidades.Titular;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TitularDao {

    public Titular salvar(Titular titular) {
        String sql = """
            INSERT INTO titular (nome, cpf)
            VALUES (?, ?)
            RETURNING id
        """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, titular.getNome());
            ps.setString(2, titular.getCpf());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                titular.setId(rs.getLong("id"));
            }
            return titular;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Titular buscarPorCpf(String cpf) {

        String sql = """
        SELECT id, nome, cpf
        FROM titular
        WHERE cpf = ?
    """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Titular titular = new Titular();
                    titular.setId(rs.getLong("id"));
                    titular.setNome(rs.getString("nome"));
                    titular.setCpf(rs.getString("cpf"));
                    return titular;
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar titular por CPF", e);
        }
    }
}
