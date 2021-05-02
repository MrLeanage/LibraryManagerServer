package services.rmiService;


import bean.About;
import bean.Book;
import bean.Feedback;
import bean.Request;
import javafx.collections.ObservableList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIInterface extends Remote {


	public ObservableList<Book> getAllAvailableBooks() throws RemoteException;

	public boolean giveFeedback(Feedback feedback) throws RemoteException;

	public boolean requestBook(Request request) throws RemoteException;

	public About getLibraryInformation() throws RemoteException;

	public ArrayList<Book> getAllBooks() throws RemoteException;

	public boolean addBookView(Integer id) throws RemoteException;
}
