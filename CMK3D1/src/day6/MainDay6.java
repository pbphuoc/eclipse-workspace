package day6;

public class MainDay6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookStoreDataSource dataSource = new BookStoreDataSource();
		BookStoreService dataService = new BookStoreService();
		dataService.showBorrowListByAllCustomer(dataSource.getBorrowReturnHistories(), dataSource.getCustomers(),
				dataSource.getBooks(), dataSource.getBookTypes(), dataSource.getTypes());
	}
}