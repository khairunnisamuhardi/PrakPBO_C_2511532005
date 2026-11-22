package shop.discount;

public interface DiscountStrategy {
    /*  Besar potongan rupiah untuk subtotal tertentu */
    long discountFor(long subtotal);

}
