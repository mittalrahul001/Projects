package model;

import java.util.Date;

public class Order implements IOrder {

	int order_id;
	String customer_email;
	int order_price;
	Date date;

	// Initialisation
	public Order(int order_id, String customer_email,int order_price,Date date) {
		this.order_id = order_id;
		this.customer_email = customer_email;
		this.order_price = order_price;
		this.date = date;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public int getOrder_price() {
		return order_price;
	}

	public void setOrder_price(int totalSum) {
		this.order_price = totalSum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	}