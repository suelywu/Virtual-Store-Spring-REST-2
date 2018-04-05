package com.example.virtualstore.application.service.finishOrder;

import com.example.virtualstore.application.service.ClientService;
import com.example.virtualstore.application.service.OrderService;
import com.example.virtualstore.application.service.ShoppingCartService;
import com.example.virtualstore.application.service.StoreService;
import com.example.virtualstore.domain.entity.Client;
import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.domain.valueObjects.payment.Payment;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public abstract class FinishShoppingService {

    private ShoppingCartService shoppingCartService;
    private StoreService storeService;
    private OrderService orderService;
    private ClientService clientService;

    private int clientId;
    private Payment payment;

    @Autowired
    public FinishShoppingService(ShoppingCartService shoppingCartService, StoreService storeService, OrderService orderService, ClientService clientService) {
        this.shoppingCartService = shoppingCartService;
        this.storeService = storeService;
        this.orderService = orderService;
        this.clientService = clientService;
        this.clientId = 0;
        this.payment = null;
    }

    /*
    passo para ele
        clientId (para atualizar lista de orders do cliente)
        payment option e payment information de cada tipo (para inserir no order)
        
    o que preciso fazer
        fazer decremento no store (a partir dos prodHolders do shoppingCart)
        armazenar esses prodHolders no order
        limpar o shoppingCart (esvaziar a lista de prodHolders)
        pegar um cliente valido (a partir do CLIENT ID)
            pegar PAYMENT OPTION
            pegar PAYMENT INFORMATION necessarias de PAYMENT OPTION correspondente
            gerar um objeto payment
        criar um order
        add order na lista de orders do cliente
        
        
        
     */

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    

    public void finish() {
        if (clientId == 0 | payment == null) {
            throw new RuntimeException("Missing information to finish the order!");
        }
        List<ProductHolderWrapper> productHolderWrappers = shoppingCartService.getClientShoppingCartProdHolderWrappers(clientId);
        shoppingCartService.clearClientShoppingCart(clientId);
        productHolderWrappers.forEach(storeService::decreaseProdQuantity);
        List<ProductHolder> productHolders = new LinkedList<>();
        for (ProductHolderWrapper productHolderWrapper : productHolderWrappers) {
            ProductHolder productHolder = productHolderWrapper.getProductHolder();
            productHolders.add(productHolder);
        }
        int orderId = orderService.addNewOrder(clientId, productHolders, payment);
        Order order = orderService.getOrderWrapper(orderId).getOrder();
        Client client = clientService.getClientWrapperById(clientId).getClient();
        client.addOrder(order);
    }

}
