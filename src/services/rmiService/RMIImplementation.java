package services.rmiService;

import bean.About;
import bean.Book;
import bean.Feedback;
import bean.Request;
import javafx.collections.ObservableList;
import services.daoServices.AboutService;
import services.daoServices.BookService;
import services.daoServices.FeedbackService;
import services.daoServices.RequestService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;



public class RMIImplementation extends UnicastRemoteObject implements RMIInterface {
	
	protected RMIImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = -3763231206310448L;

	@Override
	public ObservableList<Book> getAllAvailableBooks() throws RemoteException {
		BookService bookService = new BookService();
		return bookService.loadAllData();
	}

	@Override
	public boolean giveFeedback(Feedback feedback) throws RemoteException {
		FeedbackService feedbackService = new FeedbackService();
		return feedbackService.insertFeedback(feedback);
	}

	@Override
	public boolean requestBook(Request request) throws RemoteException {
		RequestService requestService = new RequestService();
		return requestService.insertRequest(request);
	}

	@Override
	public About getLibraryInformation() throws RemoteException {
		AboutService aboutService = new AboutService();
		return aboutService.getLibraryInformation();
	}

	@Override
	public ArrayList<Book> getAllBooks() throws RemoteException {
		BookService bookService = new BookService();
		return new ArrayList<Book>(bookService.loadAllData());
	}

	@Override
	public boolean addBookView(Integer id) throws RemoteException {
		BookService bookService = new BookService();
		return bookService.addBookView(id);
	}

}
