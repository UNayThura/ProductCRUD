package com.snh.samplecrud.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.snh.samplecrud.entity.Category;
import com.snh.samplecrud.entity.Product;
import com.snh.samplecrud.entity.Productpo;

@Repository("productDao")
@Transactional
public class DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		Session s;
		try {
			s=sessionFactory.getCurrentSession();
		} catch (Exception e) {
			s=sessionFactory.openSession();
		}
		return s;

	}
	public void insertProduct(Product p, int categoryId){
		
		p.setCategory(getId(categoryId));

		getSession().save(p);

		
	}
	
	public void insertCategory(Category category){
		
		getSession().save(category);
		
	}
	
	public List<Productpo> showAll(){
		
		List<Product> productList =getSession().createCriteria(Product.class).list();
		List<Productpo> productpoList = new ArrayList<>();
		
		for(Object obj : productList){
			Product p = (Product) obj;
			Productpo po = new Productpo(p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCategory().getId(), p.getCategory().getName());
			productpoList.add(po);
			
		}
	
		return productpoList;
	}
	
	public List<Category> showCategory(){
		
		List<Category> categoryList = getSession().createCriteria(Category.class).list();
		
		return categoryList;
	}
	
	public Category getId(int categoryId){
		
		Criteria cr= getSession().createCriteria(Category.class);
		cr.add(Restrictions.eq("id", categoryId));
		Category category = (Category) cr.uniqueResult();
		
		return category;
	}
	
	public Product findById(int id){
		
		Criteria cr = getSession().createCriteria(Product.class);
		cr.add(Restrictions.eq("id", id));
		Product product = (Product) cr.uniqueResult();
	
		return product;
	}
	
	public void delete(Product p){
		getSession().delete(p);
		
	}
	
	public void update(Product p){

		getSession().update(p);

	}
	
	public Category categoryFindById(int id){
		Criteria cr = getSession().createCriteria(Category.class);
		cr.add(Restrictions.eq("id", id));
		Category category = (Category) cr.uniqueResult();
		
		return category;
	}
	
	public void updateCategory(Category c){
		getSession().update(c);
		
	}

}
