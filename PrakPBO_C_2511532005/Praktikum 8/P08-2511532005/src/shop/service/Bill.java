package shop.service;

/* Rincian tagihan satu per satu */
public record Bill(long subtotal, long discount, long shipping, long total) {
}
