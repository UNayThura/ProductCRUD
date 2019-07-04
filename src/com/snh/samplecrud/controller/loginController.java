package com.snh.samplecrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snh.samplecrud.entity.Category;
import com.snh.samplecrud.entity.Product;
import com.snh.samplecrud.entity.Productpo;
import com.snh.samplecrud.services.ProductService;

/**
 * Created by Sai Nyan Htay
 */

@Controller

public class loginController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public String welcome(Model model){
		
		model.addAttribute("tagline", "Welcome");
		
		return "index";
	}

	@GetMapping("/product")
	public String createProduct( Model model){
		
		model.addAttribute("productpo", new Productpo());
		model.addAttribute("categoryList", productService.showCategory());
		return "createProductForm";
	}
	
	@PostMapping("/product")
	public String process(@ModelAttribute Productpo po, Model model, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			
		}
		
		Product product = new Product();
		product.setName(po.getName());
		product.setQuantity(po.getQuantity());
		product.setPrice(po.getPrice());
		int categoryId = po.getCategoryId();
	
		productService.insertProduct(product, categoryId);
		productService.getId(categoryId);
		System.out.println("test");
		model.addAttribute("product", productService.showAll());
		
		return "productForm";
	}
	
	@GetMapping("/products")
	public String show(Model model){
		
		model.addAttribute("product", productService.showAll());
		
		return "productForm";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model){
		
		Product p = productService.findById(id);
		Productpo productpo = new Productpo(p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCategory().getId(), p.getCategory().getName());
		
		System.out.println(productpo);
		
		model.addAttribute("productpo", productpo);
		
		model.addAttribute("categoryList", productService.showCategory());
		
		return "createProductForm";
	}
	
	@PostMapping("/update/{id}")
	public String updateProduct(Model model, @PathVariable("id") int id, @ModelAttribute Productpo po ){
		
		Product product = new Product();
		product.setId(po.getId());
		product.setName(po.getName());
		product.setQuantity(po.getQuantity());
		product.setPrice(po.getPrice());
		
		Category category = productService.categoryFindById(po.getCategoryId()); 
		
		product.setCategory(category);
		
		productService.update(product);

		model.addAttribute("product", productService.showAll());
		
		return "productForm";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id, Model model){
		System.out.println("delete Product");
		Product product = productService.findById(id);
		
		productService.delete(product);
		
		model.addAttribute("product", productService.showAll());
		
		return "productForm";
	}

}