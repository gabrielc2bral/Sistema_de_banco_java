package Dao;

import Entidades.Titular;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TitularDao {

    private final JDBCUtil JDBC = JDBCUtil.getInstance();
    private static TitularDao titularDao;

    private TitularDao() {
    }

    public static TitularDao getInstance() {
        if(titularDao == null){
            titularDao = new TitularDao();
        }
        return titularDao;
    }

    public Titular salvar(Titular titular) {
        String sql = """
            INSERT INTO titulares (nome, cpf)
            VALUES (?, ?)
            RETURNING id
        """;

        try (Connection conn = JDBC.getConnection();
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
        FROM titulares
        WHERE cpf = ?
    """;

        try (Connection conn = JDBC.getConnection();
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
