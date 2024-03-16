package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutoDAO {

    private Connection connect() {
        // URL de conexão com o banco de dados SQLite
        String url = "jdbc:sqlite:nome_do_seu_banco_de_dados.db"; // Substitua "nome_do_seu_banco_de_dados.db" pelo caminho correto do seu banco
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ProdutoDAO() {
        createNewTable();
    }

    private void createNewTable() {
        // Comando SQL para criar uma nova tabela, se ela ainda não existir
        String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                "ProdutoID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nome TEXT NOT NULL," +
                "Descricao TEXT," +
                "Preco REAL NOT NULL," +
                "Codigo INTEGER UNIQUE NOT NULL," +
                "Estoque INTEGER NOT NULL DEFAULT 0" +
                ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'produtos' criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertProduto(Produto produto) {
        String sql = "INSERT INTO produtos(Nome, Descricao, Preco, Codigo, Estoque) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setInt(4, produto.getCodigo());
            pstmt.setInt(5, produto.getEstoque());
            pstmt.executeUpdate();
            System.out.println("Produto inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Métodos adicionais para atualizar, deletar e buscar produtos podem ser implementados aqui
}
