package Dao;

import Entidades.Conta;
import Entidades.Titular;
import util.JDBCUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDao {

    private final JDBCUtil JDBC = JDBCUtil.getJdbc();
    private static ContaDao contaDao;

    private ContaDao() {
    }

    public static ContaDao getInstance() {
        if (contaDao == null) {
            contaDao = new ContaDao();
        }
        return contaDao;
    }

    public Conta salvar(Conta conta) {
        String sql = """
                    INSERT INTO contas (titular_id, saldo)
                    VALUES (?, ?)
                    RETURNING id
                """;
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, conta.getTitular().getId());
            ps.setBigDecimal(2, conta.getSaldo());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
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
                    SELECT c.id AS conta_id, c.saldo, t.nome AS titular, t.cpf
                    FROM contas c
                    INNER JOIN titulares t on c.titular_id = t.id
                    WHERE c.id = ?
                """;

        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Titular titular = new Titular(rs.getString("titular"), rs.getString("cpf"));
                Conta conta = new Conta(titular, rs.getBigDecimal("saldo"));
                conta.setId(rs.getInt("conta_id"));
                return conta;
            } else return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void atualizarSaldoDaConta(Conta conta) {
        String sql = "UPDATE contas SET saldo = ? WHERE id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, conta.getSaldo());
            ps.setLong(2, conta.getId());
            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas != 0) {
                System.out.println("Deposito realizado com sucesso");
            } else System.out.println("Conta não existe");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


}
