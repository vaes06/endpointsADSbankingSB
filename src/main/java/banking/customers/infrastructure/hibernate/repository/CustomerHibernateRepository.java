package banking.customers.infrastructure.hibernate.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import banking.accounts.domain.entity.BankAccount;
import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;
import banking.users.domain.entity.User;

@Transactional
@Repository
public class CustomerHibernateRepository extends BaseHibernateRepository<Customer> implements CustomerRepository {
	public Customer get(long customerId) {
		Customer customer = null;
		customer = getSession().get(Customer.class, customerId);
		return customer;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> get(int page, int pageSize) {
		List<Customer> customers = null;
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pageSize);
		customers = criteria.list();
		return customers;
	}
	
	public Customer save(Customer customer) {
		return super.save(customer);
	}

	@Override
	public Customer getByLastName(String lastName) {
		Customer customer = null;
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("lastName", lastName));
		customer = (Customer) criteria.uniqueResult();
		return customer; 
		
		
	}
	
}
