package mercearia.app;

import mercearia.model.Produto;
import mercearia.service.ProdutoService;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        var service = new ProdutoService();
        Produto produto = new Produto();

        // interface para salvar um produto no banco de dados - completa

        produto.setNome(JOptionPane.showInputDialog(null, "Informe o nome do produto: ", "Input Nome", JOptionPane.QUESTION_MESSAGE));

        produto.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade do produto: ", "Input Quantidade", JOptionPane.QUESTION_MESSAGE)));

        produto.setTipo(JOptionPane.showInputDialog(null, "Informe o tipo do produto: ", "Input Tipo", JOptionPane.QUESTION_MESSAGE));

        produto.setPreco(Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o preço do produto: ", "Input Preço", JOptionPane.QUESTION_MESSAGE)));

        produto.setFornecedor(JOptionPane.showInputDialog(null, "Informe o fornecedor do produto: ", "Input Fornecedor", JOptionPane.QUESTION_MESSAGE));

        var response = service.save(produto);

        var situacao = "Ocorreu uma falha na gravação. Verifique o log";
        var iconeStatus = JOptionPane.ERROR;

        if(response){
            situacao = "Gravado com sucesso";
            iconeStatus = JOptionPane.INFORMATION_MESSAGE;
        }

        var msg = "Situação da gravação no banco: " + situacao + "\n\n" + "produto.nome = " + produto.getNome() + "\n" + "produto.quantidade = " + produto.getQuantidade() + "\n" + "produto.tipo = " + produto.getTipo() + "\n" + "produto.preco = " + produto.getQuantidade() + "\n" + "produto.fornecedor = " + produto.getFornecedor();
        JOptionPane.showMessageDialog(null, msg, "Resposta", iconeStatus );

        var produtoList = service.findAll().stream().map(produtos -> "produto.nome: " + produtos.getNome() + "\n" + "produto.quantidade: " + produtos.getQuantidade() + "\n" + "produto.tipo: " + produtos.getTipo() + "\n" + "produto.preco: " + produtos.getPreco() +  "\n" + "produto.fornecedor: " + produtos.getFornecedor() + "\n" + "produto.created: " + produtos.getCriado() + "\n\n").toList();
        JOptionPane.showMessageDialog(null, produtoList, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}
