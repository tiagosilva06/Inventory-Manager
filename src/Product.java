public class Product {
    private String code;
    private String model;
    private String color;
    private String size;
    private int quantity;

    public Product(String code, String model, String color, String size, int quantity) {
        this.code = code;
        this.model = model;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo='" + code + '\'' +
                "modelo='" + model + '\'' +
                ", cor='" + color + '\'' +
                ", tamanho='" + size + '\'' +
                ", quantidade='" + quantity + '\'' +
                '}';
    }
}