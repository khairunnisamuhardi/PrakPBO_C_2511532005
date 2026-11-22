package shop.tantangan;

import java.util.List;
import shop.notify.SmsChannel;
import shop.notify.WhatsAppChannel;
import shop.service.Notifier;

public class TantanganB {
    public static void main(String[] args) {
        Notifier notifier = new Notifier(List.of(new SmsChannel(), new WhatsAppChannel()));
        notifier.notifyPaid("WA-1", 95_000L);
    }
}