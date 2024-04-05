package com.tom.OrderManagementServices.service;

import com.tom.OrderManagementServices.dto.CreateOrderRequest;
import com.tom.OrderManagementServices.dto.CustomerOrders;
import com.tom.OrderManagementServices.model.Address;
import com.tom.OrderManagementServices.model.Customer;
import com.tom.OrderManagementServices.model.Order;
import com.tom.OrderManagementServices.model.OrderItem;
import com.tom.OrderManagementServices.repository.AddressRepository;
import com.tom.OrderManagementServices.repository.CustomerRepository;
import com.tom.OrderManagementServices.repository.OrderItemRepository;
import com.tom.OrderManagementServices.repository.OrderRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired OrderItemRepository orderItemRepository;
  @Autowired CustomerRepository customerRepository;
  @Autowired OrderRepository orderRepository;
  @Autowired AddressRepository addressRepository;

  public Order createOrder(CreateOrderRequest createrequest) {

    // Customer
    Customer customer;
    customer = createrequest.getCustomer();
    createCustomer(customer);

    // Address
    List<Address> AddressList = createrequest.getAddress();
    Iterator<Address> addressListIter = AddressList.iterator();

    while (addressListIter.hasNext()) {

      Address address = addressListIter.next();
      // System.out.println("--------item1---" + orderItem.getTaxAmount());
      address.setCustomer(customer);
      addressRepository.save(address);
    }

    // Order
    Order order = createrequest.getOrder();
    order.setCustomer(customer);
    createOrder(order);

    // OrderItem
    List<OrderItem> orderItemList = createrequest.getOrderitem();
    Iterator<OrderItem> orderItemListIter = orderItemList.iterator();

    while (orderItemListIter.hasNext()) {

      OrderItem orderItem = orderItemListIter.next();
      // System.out.println("--------item1---" + orderItem.getTaxAmount());
      orderItem.setOrder(order);
      orderItemRepository.save(orderItem);
    }

    return order;
  }

  public List<CustomerOrders> getCustomerOrder(String emailID) {

    List<Customer> customer = customerRepository.findByEmailID(emailID);
    List<Order> orderList = null;
    List<CustomerOrders> customerOrderList = new ArrayList<CustomerOrders>();

    if (!customer.equals(null)) {

      orderList = orderRepository.findByCustomerId(customer.get(0).getId());

      Iterator<Order> orderIter = orderList.iterator();
      while (orderIter.hasNext()) {

        CustomerOrders customerOrder = new CustomerOrders();
        Order order = orderIter.next();

        System.out.println("----order-----" + order);
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
        customerOrder.setOrder(order);

        Iterator<OrderItem> orderItemIter = orderItems.iterator();
        while (orderItemIter.hasNext()) {
          orderItemIter.next().setOrder(null);
        }

        customerOrder.setOrderitem(orderItems);
        customerOrderList.add(customerOrder);
      }
    }

    return customerOrderList;
  }

  public Customer createCustomer(Customer customer) {

    customerRepository.save(customer);
    return customer;
  }

  public Order createOrder(Order order) {

    orderRepository.save(order);
    return order;
  }

  public Order getOrder(String orderID) {

    Order order = orderRepository.findById(Long.parseLong(orderID)).get();
    return order;
  }
}
