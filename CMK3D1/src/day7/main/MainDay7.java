package day7.main;

import day7.service.BookStoreService;

public class MainDay7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookStoreService dataService = new BookStoreService();
		dataService.showBorrowListByAllCustomer();
	}
}
