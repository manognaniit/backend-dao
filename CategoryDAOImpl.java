package com.niit.shoppingcart.dao;


import java.util.List;




import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.Category;
@EnableTransactionManagement
@Repository(value="categoryDAO")

public class CategoryDAOImpl implements CategoryDAO{
	private static final Logger log=LoggerFactory.getLogger(CategoryDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	public CategoryDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
@Transactional
public boolean save(Category category){
	try {
		log.debug("starting of the save method");
		
		sessionFactory.getCurrentSession().save(category);
		log.debug("ending of the save method");
		return true;
	} catch (Exception e) {
		log.error("error occured in save method "+ e.getMessage());
		e.printStackTrace();
		return false;}
	}
	@Transactional
	public boolean update(Category category){
		try {log.debug("starting of the update method");
		
			sessionFactory.getCurrentSession().update(category);
			log.debug("ending of the update method");
			
			return true;
		} catch (Exception e) {
			log.error("error occured in update method "+ e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;}
		}
		@Transactional
		public boolean delete(Category category){
			try {log.debug("starting of the delete method");
			sessionFactory.getCurrentSession().delete(category);
			log.debug("ending of the delete method");
			
				return true;
			} catch (Exception e) {
				log.error("error occured in delete method "+ e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
}
		@Transactional
public Category get(String id)
{log.debug("starting of the get method");
log.info("Trying to get category based on id"+id);
	String hql="from Category where id="+"'"+id+"'";
	log.info("The hql query is:"+hql);
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			List<Category> list=query.list();
			if(list==null || list.isEmpty()){
					log.info("No category is available with this id"+id);
				return null;
			}
			else{
				return list.get(0);
				 
			}
} @Transactional
public	List<Category> list(){
	log.debug("starting of the list method");
	String hql="from Category";
	Query query=sessionFactory.getCurrentSession().createQuery(hql);
	List<Category> list=query.list();
	if(list==null || list.isEmpty()){
		log.info("No category is available");
	return null;
}
else{
	return list;
	 
}
		
	}
}


