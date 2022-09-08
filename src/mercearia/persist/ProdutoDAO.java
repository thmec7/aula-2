package mercearia.persist;

import mercearia.model.Produto;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends DAO {
    private Connection conn;

    public boolean save(Produto produto) {
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("insert into produto(nome, quantidade, tipo, preco, fornecedor, criado) values (?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getQuantidade());
            pstmt.setString(3, produto.getTipo());
            pstmt.setDouble(4, produto.getPreco());
            pstmt.setString(5, produto.getFornecedor());
            pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            var response = pstmt.executeUpdate();

            if (response != 0)
                return Boolean.TRUE;
            return Boolean.FALSE;


        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error on save produto. Error: " + e.getMessage());
            return Boolean.FALSE;

        } finally {
            try {
                if (conn != null)
                    conn.close();

                if (pstmt != null)
                    pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }

        }
    }

        public List<Produto> findAll() {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                conn = getConnection();
                pstmt = conn.prepareStatement("select * from produto");
                rs = pstmt.executeQuery();

                var produtos = new ArrayList<Produto>();

                while (rs.next()) {
                    var produto = new Produto();
                    produto.setNome(rs.getString("nome"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setTipo(rs.getString("tipo"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setFornecedor(rs.getString("fornecedor"));
                    produto.setCriado(rs.getTimestamp("criado"));
                    produtos.add(produto);
                }
                return produtos;


            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on list produtos. Error: " + e.getMessage());
                return new ArrayList<>();
            } finally {
                try {
                    if (pstmt != null)
                        pstmt.close();
                    if (rs !=null)
                        rs.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error on close statements. Error: " + e.getMessage());
                }
            }

        }


    public boolean update(Produto produto) {
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("update produto set nome = ?, quantidade = ?, tipo = ?, preco = ?, fornecedor = ?, criado = ? where id = ?");

            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getQuantidade());
            pstmt.setString(3, produto.getTipo());
            pstmt.setDouble(4, produto.getPreco());
            pstmt.setString(5, produto.getFornecedor());
            pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            var update = pstmt.executeUpdate();

            if (update != 0)
                return Boolean.TRUE;
            return Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error on update produto. Error: " + e.getMessage());
            return Boolean.FALSE;
        } finally {
            try {
                if (conn != null)
                    conn.close();

                if (pstmt != null)
                    pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public Produto findById(long id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from produto where id = ?");

            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            Produto produto = new Produto();

            if (rs.next()) {
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setTipo(rs.getString("tipo"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setFornecedor(rs.getString("fornecedor"));
                produto.setCriado(rs.getTimestamp("criado"));
            }
            return produto;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error on find produto. Error: " + e.getMessage());
            return new Produto();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public boolean deleteById(long id) {
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("delete from produto where id = ?");

            pstmt.setLong(1, id);

            var delete = pstmt.executeUpdate();

            if(delete != 0)
                return Boolean.TRUE;
            return Boolean.FALSE;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error on delete produto. Error: " + e.getMessage());
            return Boolean.FALSE;
        } finally {
            try {
                if(conn != null)
                    conn.close();

                if(pstmt != null)
                    pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public boolean deleteAll() {
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("delete from produto");

            var deleteAll = pstmt.executeUpdate();

            if(deleteAll != 0)
                return Boolean.TRUE;
            return Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error on delete table produto. Error: " + e.getMessage());
            return Boolean.FALSE;
        } finally {
            try {
                if(conn != null)
                    conn.close();
                if(pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }
}


