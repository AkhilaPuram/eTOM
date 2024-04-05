package com.tom.OrderManagementServices.service;

import com.tom.OrderManagementServices.model.*;
import org.springframework.stereotype.Service;

@Service
public class OrderProcess {
  /*
   *
   * @Autowired private ZeebeClient zeebe; public String BPMN_PROCESS_ID =
   * "Process_Orderfullfillment";
   *
   * @Autowired CustomerRepository customerRepository;
   *
   * @Autowired AddressRepository addressRepository;
   *
   * @Autowired OrderRepository orderRepository;
   *
   * @Autowired OrderItemRepository orderItemRepository;
   *
   * public String createProcess(CreateRequest request) { // String Loanprocess =
   * "LoanApplication";
   *
   *
   * LOG.info("Starting process `" + Loanprocess + "` with variables: " +
   * variables); System.out.println("Starting process `" + Loanprocess +
   * "` with variables: " + variables);
   *
   * LoanProcess loaninfo = new LoanProcess();
   *
   * loaninfo.setLoanamount(Double.parseDouble(variables.get("loanamount").
   * toString())); loaninfo.setFirstname(variables.get("firstname").toString());
   * loaninfo.setLastname(variables.get("lastname").toString());
   * loaninfo.setEmail(variables.get("email").toString());
   * loaninfo.setRequestFrom("React");
   * loaninfo.setLoantype(variables.get("loantype").toString());
   * loaninfo.setPhonenumber(variables.get("phonenumber").toString());
   *
   *
   * try { long processID = zeebe .newCreateInstanceCommand()
   * .bpmnProcessId(BPMN_PROCESS_ID) .latestVersion() .variables(request) .send()
   * .join() .getProcessInstanceKey();
   *
   * System.out.println("process instnace id is " + processID);
   *
   * store Customer data into customer table Customer customer = new Customer();
   * customer.setFirstName(request.getFirstName());
   * customer.setLastName(request.getLastName());
   * customer.setCustomrType(request.getCustomerType());
   * customer.setBusinessPhone(request.getBusinessPhone());
   * customer.setHomePhone(request.getHomePhone());
   * customer.setFax(request.getFax());
   * customer.setMiddleName(request.getMiddleName());
   * customer.setTitle(request.getTitle());
   * customer.setSuffix(request.getSuffix());
   *
   * customerRepository.save(customer);
   *
   * Address address = new Address();
   * address.setAddressType(request.getAddress().getAddressType());
   * address.setCity(request.getAddress().getCity());
   * address.setCountry(request.getAddress().getCountry());
   * address.setState(request.getAddress().getState());
   * address.setStreet1(request.getAddress().getStreet1());
   * address.setStreet2(request.getAddress().getStreet2());
   * address.setPobox(request.getAddress().getPobox());
   * address.setPostalCode(request.getAddress().getPostalCode());
   *
   * address.setCustomer(customer); addressRepository.save(address);
   *
   * // Order Order order = request.getOrder(); order.setCustomer(customer);
   * createOrder(order);
   *
   * // OrderItem List<OrderItem> orderItemList = request.getOrderitem();
   * Iterator<OrderItem> orderItemListIter = orderItemList.iterator();
   *
   * while (orderItemListIter.hasNext()) {
   *
   * OrderItem orderItem = orderItemListIter.next(); //
   * System.out.println("--------item1---" + orderItem.getTaxAmount());
   * orderItem.setOrder(order); orderItemRepository.save(orderItem); }
   *
   * return processID + "";
   *
   * } catch (Exception e) { System.out.println("error while creating order case "
   * + e.getMessage()); return ""; } }
   *
   *
   *
   *
   * public Customer createCustomer(Customer customer) {
   *
   * customerRepository.save(customer); return customer; }
   *
   *
   *
   *
   *
   * public Order createOrder(Order order) {
   *
   * orderRepository.save(order); return order; }
   */ }
