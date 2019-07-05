package com.snh.samplecrud.services;

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
	
	public void insertProduct(Product p, int categoryId){
		dao.insertProduct(p, categoryId);
	}
	
	public void insertCategory(Category category){
		dao.insertCategory(category);
		
	}

	public List<Productpo> showAll(){
		
		return dao.showAll();
	}
	
	public List<Category> showCategory(){
		
		return dao.showCategory();
	}
	
	public void getId(int categoryId){
		dao.getId(categoryId);
	}
	
	public Product findById(int id){
		return dao.findById(id);
	}
	
	public void delete(Product p){
		System.out.println("Product delete "+p);
		Product delProduct=new Product(p.getId(), p.getName(), p.getQuantity(), p.getPrice());
		dao.delete(delProduct);
	}
	
	public void update(Product p){
		dao.update(p);
	}
	
	public Category categoryFindById(int id){
		return dao.categoryFindById(id);
	}
	
	public void updateCategory(Category c){
		dao.updateCategory(c);
	}
}
