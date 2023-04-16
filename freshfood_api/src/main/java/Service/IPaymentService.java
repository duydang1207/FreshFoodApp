package Service;

import Service.Impl.PaymentService;
import com.freshfood.API_FreshShop.Entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface IPaymentService  {
    BigDecimal totalPrice(List<OrderItem> listItem);
}
