package shop.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String orderId;
    private final Address shippingAddress;
    private final List<Line> lines = new ArrayList<>();

    public Order(String orderId, Address shippingAddress) {
        if (orderId == null || orderId.isBlank() || shippingAddress == null) {
            throw new IllegalArgumentException("Order id and address are required");
        }

        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderId() { return orderId; }
    public Address getShippingAddress() { return shippingAddress; }
    public void addLine(Product product, int quantity) { lines.add(new Line(product, quantity)); }
    public List<Line> getLines() { return Collections.unmodifiableList(lines); }
   
    public static class Line {
        private final Product product;
        private final int quantity;

        public Line(Product product, int quantity) {
            if (product == null || quantity <= 0) {
                throw new IllegalArgumentException("Data tidak valid");
            }

            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }
        public int getQuantity() {
            return quantity;
        }
        public long subtotal() {
            return product.getFinalPrice() * quantity;
        }
    }

    public long getItemsTotal() {
        long total = 0;
        for (Line line : lines) {
            total = total + line.subtotal();
        } return total; }

    public class Receipt {

        public String toText() {
            String hasil = "STRUK PESANAN " + Order.this.orderId + "\n";
            for (Line line : lines) {
                hasil = hasil + "- " + line.getProduct().getName() + " x" + line.getQuantity()
                        + " = Rp" + line.subtotal() + "\n"; }

            hasil = hasil + "Total pesanan: Rp" + Order.this.getItemsTotal() + "\n";
            hasil = hasil + "Kirim ke: " + Order.this.shippingAddress;
            return hasil;
        }
    }

    public Receipt createReceipt() {
        return new Receipt();
    }
}