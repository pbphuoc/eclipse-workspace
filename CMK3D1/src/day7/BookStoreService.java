package day7;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BookStoreService {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	Scanner scanner = new Scanner(System.in);

	public String getBorrowStatus(BorrowReturnHistory borrowReturnHistory) {
		LocalDate todayDate = LocalDate.parse(LocalDate.now().format(formatter), formatter);
		LocalDate expectedReturnDate = LocalDate.parse(borrowReturnHistory.getExpectedReturnDate(), formatter);

		if (borrowReturnHistory.getActualReturnDate().equalsIgnoreCase("")) {
			if (todayDate.isAfter(expectedReturnDate))
				return "Overdue";
			else
				return "Borrowed";
		} else {
			LocalDate actualReturnDate = LocalDate.parse(borrowReturnHistory.getActualReturnDate(), formatter);
			if (actualReturnDate.isAfter(expectedReturnDate))
				return "Late Return";
			else
				return "Returned";
		}
	}

	public ArrayList<BorrowReturnHistory> getBorrowListByCustomerID(
			ArrayList<BorrowReturnHistory> borrowRefurnHistories, String customerID) {
		ArrayList<BorrowReturnHistory> records = new ArrayList<BorrowReturnHistory>();
		for (BorrowReturnHistory record : borrowRefurnHistories) {
			if (record.getCustomerID().equalsIgnoreCase(customerID))
				records.add(record);
		}
		return records;
	}

	public void showBorrowListByAllCustomer(ArrayList<BorrowReturnHistory> borrowReturnHistories,
			ArrayList<Customer> customers, ArrayList<Book> books, ArrayList<BookType> booktypes,
			ArrayList<Type> types) {
		String format = "%-15s %-35s %-30s %-30s %-15s %-15s %-15s %-15s\n";
		System.out.printf(format, "Customer", "Book", "Author", "Type(s)", "Borrow Date", "Due Date", "Return Date",
				"Status");
		for (Customer customer : customers) {
			for (BorrowReturnHistory history : borrowReturnHistories) {
				if (history.getCustomerID().equalsIgnoreCase(customer.getCustomerID())) {
					Book b = getBookByISBN(books, history.getISBN());
					String bookTypes = getBookTypeByISBN(booktypes, types, history.getISBN());
					System.out.printf(format, customer.getCustomerName(), b.getName(), b.getAuthor(), bookTypes, history
							.getBorrowDate(), history.getExpectedReturnDate(), history.getActualReturnDate(),
							getBorrowStatus(history));
				}
			}
		}
	}

//	public Customer getCustomerByID(ArrayList<Customer> customers, String customerID) {
//		for(Customer customer : customers) {
//			if (customerID.equalsIgnoreCase(customer.getCustomerID()))
//				return customer;
//		}
//		return null;
//	}

	public Book getBookByISBN(ArrayList<Book> books, String ISBN) {
		for (Book book : books) {
			if (ISBN.equalsIgnoreCase(book.getISBN()))
				return book;
		}
		return null;
	}

	public String getBookTypeByISBN(ArrayList<BookType> bookTypes, ArrayList<Type> types, String ISBN) {
		String bookTypeByISBN = "";
		for (BookType bookType : bookTypes) {
			for (Type type : types) {
				if (ISBN.equalsIgnoreCase(bookType.getISBN())
						&& bookType.getTypeID().equalsIgnoreCase(type.getTypeID())) {
					bookTypeByISBN += "(" + type.getTypeName() + ") ";
				}
			}
		}
		return bookTypeByISBN;
	}
}
