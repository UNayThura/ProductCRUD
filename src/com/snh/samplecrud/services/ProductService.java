package com.snh.samplecrud.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snh.samplecrud.dao.DAO;
import com.snh.samplecrud.entity.Category;
import com.snh.samplecrud.entity.Product;
import com.snh.samplecrud.entity.Productpo;

@Service("productService")
public class ProductService {

	@Autowired
	private DAO dao;
	
	public Product insertProduct(Product p, String categoryName){
		try{
			dao.insertProduct(p, categoryName);
		}catch (Exception e) {
			System.out.println("insert product error");
		}
		return p;
	}
	
	public Category insertCategory(Category category){
		
		try{
			dao.insertCategory(category);
		}catch (Exception e) {
			System.out.println("insert product error");
		}
		return category;
		
	}

	public List<Productpo> showAll(){
		
		return dao.showAll();
	}
	
	public List<Category> showCategory(){
		
		return dao.showCategory();
	}
	
	public void getCategoryName(String categoryName){
		dao.getCategoryName(categoryName);
	}
	
	public Product findById(int id){
		return dao.findById(id);
	}
	
	public void delete(Product p){
		System.out.println("Product delete "+p);
		Product delProduct=new Product(p.getId(), p.getName(), p.getQuantity(), p.getPrice());
		dao.delete(delProduct);
	}
	
	public Product update(Product p){
		try{
			dao.update(p);
			System.out.println("product work");
		}catch(Exception e){
			System.out.println("Exception >> " + "product update error");
		}
		System.out.println("product service return obj >> " + p.toString());
		return p;
	}
	
	public Category categoryFindByName(String categoryName){
		return dao.categoryFindByName(categoryName);
	}
	
	public Category updateCategory(Category c) throws SQLException{
		try{
			dao.updateCategory(c);
			System.out.println("product work");
			System.out.println("category service return obj >> " + c.toString());
			return c;
		}catch(Exception e){
			System.out.println("Exception >> " + "category update error");
			throw new SQLException(e);
		}
		
	}
}
