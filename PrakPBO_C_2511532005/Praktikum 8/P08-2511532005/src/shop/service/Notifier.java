package shop.service;

import java.util.List;
import shop.model.Order;
import shop.notify.NotificationChannel;
import shop.notify.SmsChannel;

public class Notifier {
    private final List<NotificationChannel> channels;

    public Notifier() {
        this(List.of(new SmsChannel()));
    }

    public Notifier(List<NotificationChannel> channels) {
        this.channels = channels;
    }

    public void notifyPaid(Order order, long amount) {
        String recipient = order.getAddress().getRecipient();
        String formattedAmount = formatRupiah(amount);
        String msg = "pesanan " + order.getOrderId() + " sudah dibayar (" + formattedAmount + ")";
        for (NotificationChannel channel : channels) {
            channel.send(recipient, msg);
        }
    }

    private static String formatRupiah(long amount) {
        return "Rp" + String.format("%,d", amount).replace(',', '.');
    }

    public void notifyPaid(String orderId, long amount) {
        String msg = "pesanan " + orderId + " sudah dibayar (Rp" + amount + ")";
        for (NotificationChannel channel : channels) {
            channel.send("Penguji", msg);
        }
    }
}