import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StockGUI extends JFrame {
    private Stock stock = new Stock();

    private JTextField codeField = new JTextField(10);
    private JTextField modelField = new JTextField(10);
    private JTextField colorField = new JTextField(10);
    private JTextField sizeField = new JTextField(10);
    private JTextField quantityField = new JTextField(5);
    private JTable tableProducts;
    private DefaultTableModel tableModel;

    public StockGUI() {
        setTitle("Controle de Estoque - Donna Vanda Modas");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();
        setVisible(true);
    }

    private void createComponents() {
        JPanel painelTopo = new JPanel(new GridLayout(2, 5, 10, 10));
        painelTopo.setBorder(BorderFactory.createTitledBorder("Dados do Produto"));

        painelTopo.add(new JLabel("Código:"));
        painelTopo.add(new JLabel("Modelo:"));
        painelTopo.add(new JLabel("Cor:"));
        painelTopo.add(new JLabel("Tamanho:"));
        painelTopo.add(new JLabel("Quantidade:"));

        painelTopo.add(codeField);
        painelTopo.add(modelField);
        painelTopo.add(colorField);
        painelTopo.add(sizeField);
        painelTopo.add(quantityField);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnListar = new JButton("Listar");
        JButton btnFiltrarNome = new JButton("Filtrar por Nome");
        JButton btnFiltrarCor = new JButton("Filtrar por Cor");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnFiltrarNome);
        painelBotoes.add(btnFiltrarCor);

        String[] colunas = {"Código", "Modelo", "Cor", "Tamanho", "Quantidade"};
        tableModel = new DefaultTableModel(colunas, 0);
        tableProducts = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableProducts);

        // Eventos
        btnAdicionar.addActionListener(e -> addProduct());
        btnRemover.addActionListener(e -> removeProduct());
        btnListar.addActionListener(e -> updateTable(stock.listarProdutos()));
        btnFiltrarNome.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Digite o nome:");
            if (nome != null) {
                updateTable(stock.filterByName(nome));
            }
        });
        btnFiltrarCor.addActionListener(e -> {
            String cor = JOptionPane.showInputDialog("Digite a cor:");
            if (cor != null) {
                updateTable(stock.filterByColor(cor));
            }
        });

        setLayout(new BorderLayout());
        add(painelTopo, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void addProduct() {
        try {
            String code = codeField.getText();
            String model = modelField.getText();
            String color = colorField.getText();
            String size = sizeField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            Product product = new Product(code, model, color, size, quantity);
            if (stock.addProduct(product)) {
                JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
                cleanFields();
                updateTable(stock.listarProdutos());
            } else {
                JOptionPane.showMessageDialog(this, "Código já existente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeProduct() {
        String codigo = JOptionPane.showInputDialog("Digite o código do produto:");
        if (codigo != null && stock.removeProduct(codigo)) {
            JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
            updateTable(stock.listarProdutos());
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(java.util.List<Product> lista) {
        tableModel.setRowCount(0);
        for (Product p : lista) {
            tableModel.addRow(new Object[]{
                    p.getCode(), p.getModel(), p.getColor(), p.getSize(), p.getQuantity()
            });
        }
    }

    private void cleanFields() {
        codeField.setText("");
        modelField.setText("");
        colorField.setText("");
        sizeField.setText("");
        quantityField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StockGUI::new);
    }
}