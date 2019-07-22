package com.snh.samplecrud.controller;

import java.io.File;
import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.snh.samplecrud.entity.Category;
import com.snh.samplecrud.entity.Product;
import com.snh.samplecrud.entity.Productpo;
import com.snh.samplecrud.services.ProductService;

/**
 * Created by Sai Nyan Htay
 */

@Controller

public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/productForm")
	public ResponseEntity<String> createProduct(@RequestBody Productpo p) {

		Product product = new Product(p.getName(), p.getQuantity(), p.getPrice());
		Product insertp = productService.insertProduct(product, p.getCategoryName());
		Gson gson = new Gson();
		String str = gson.toJson(insertp);
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
		
	}

	@PostMapping("/category")
	public ResponseEntity<String> createCategory(@RequestBody String categoryName) {
		
		Category category = new Category();
		category.setName(categoryName);
		Category c = productService.insertCategory(category);
		
		Gson gson =  new Gson();
		String str = gson.toJson(c);
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@PostMapping("/submitProduct")
	public ResponseEntity<String> process(@Valid @RequestBody Productpo po, BindingResult bindingResult,Model model) {
	
		Product product = new Product();
		product.setId(po.getId());
		product.setName(po.getName());
		product.setQuantity(po.getQuantity());
		product.setPrice(po.getPrice());
	
		Category category = productService.categoryFindByName(po.getCategoryName());
		category.setName(po.getCategoryName());

		product.setCategory(category);
		Product updatedProduct=productService.update(product);
		Gson gson = new Gson();
		String str = gson.toJson(updatedProduct);
		
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}

	@GetMapping("/product-list")
	public ResponseEntity<String> show() {
	
		Gson gson = new Gson();
		
		String json = gson.toJson(productService.showAll());
		
		return new ResponseEntity<String>(json, HttpStatus.OK);
		
	}

	@GetMapping("/delete/{id}")
	public void deleteProduct(@PathVariable("id") int id) {
		System.out.println("delete method >>> ");
		Product product = productService.findById(id);

		productService.delete(product);
		
	}

	@GetMapping("/category-list")
	public ResponseEntity<String> showCategoryList() {
		System.out.println("CategoryList");
	
		Gson gson = new Gson();
		
		String json = gson.toJson(productService.showCategory());
		
		return new ResponseEntity<String>(json, HttpStatus.OK);
	
	}

	
//	@GetMapping("/editcategory/{id}")
//	public String editCategory(@PathVariable("id") int id, Model model){
//		System.out.println("edit category >>>>");
//		model.addAttribute("category", productService.categoryFindById(id));
//		
//		return "category-edit";
//	}
//	
	@PostMapping("/updatecategory")
	public ResponseEntity<String> updateCategory(@RequestBody Category c){
		
		System.out.println("Update category" + c.toString());
		
		Category category;
		try {
			category = productService.updateCategory(c);
			Gson gson = new Gson();
			String str = gson.toJson(category);
			
			return new ResponseEntity<String> ( str, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
}