import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stock {
    private Map<String, Product> products;

    public Stock() {
        this.products = new HashMap<>();
    }

    public boolean addProduct(Product product){
        if(products.containsKey(product.getCode())){
            return false;
        }

        products.put(product.getCode(), product);
        return true;
    }

    public boolean removeProduct (String code){
        return products.remove(code) != null;
    }

    public List<Product> filterByName(String name){
        return products.values().stream()
                .filter(p -> p.getModel().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Product> filterByColor(String color){
        return products.values().stream()
                .filter(p -> p.getColor().toLowerCase().contains(color.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Product> listProducts(){
        return new ArrayList<>(products.values());
    }
}