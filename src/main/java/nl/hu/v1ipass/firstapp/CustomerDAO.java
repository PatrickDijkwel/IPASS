package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Address;
import domain.Customer;

public class CustomerDAO extends BaseDAO {
	private AddressDAO addressDAO = new AddressDAO();
	
	/***************************** CREATE & UPDATE methods *****************************/
	
	/***************************** READ methods *****************************/
	
	private List<Customer> selectCustomers(String query) {
		List<Customer> results = new ArrayList<Customer>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int custId = dbResultSet.getInt("customerId");
				String name = dbResultSet.getString("name");
				String account = dbResultSet.getString("account");

				int addressId = dbResultSet.getInt("FK_addressID");
				Address address = addressDAO.findById(addressId);
				
				Customer newCustomer = new Customer(name, custId, account);
				newCustomer.setAddress(address);
				
				results.add(newCustomer);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	
	public List<Customer> findAll() {
		return selectCustomers("SELECT customerId, name, account, FK_addressID FROM customer");
	}
	
	public Customer findById(int customerId) {
		return selectCustomers("SELECT customerId, name, account, FK_addressID FROM customer WHERE customerid = " +customerId).get(0);
	}
	
	
	/***************************** DELETE methods *****************************/
	
	public boolean delete(Customer customer) {
		boolean result = false;
		boolean customerExists = findById(customer.getCustomerId()) != null;
		
		if (customerExists) {
			String query = "DELETE FROM customer WHERE customerid = " +customer.getCustomerId(); 
					
			try (Connection con = getConnection()) {
				
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = addressDAO.delete(customer.getAddress());
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return result;
	}
}
