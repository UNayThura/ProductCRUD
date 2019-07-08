package com.snh.samplecrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping("/")
	public String welcome(Model model) {

		return "redirect:/product-list";
	}

	@GetMapping("/productForm")
	public String createProduct(Model model) {

		model.addAttribute("productpo", new Productpo());

		model.addAttribute("categoryList", productService.showCategory());
		model.addAttribute("value", "create");

		return "product-form";
	}

	@PostMapping("/category")
	public String createCategory(Category category) {
		System.out.println("category :" + category);
		Category c = new Category();
		c.setName(category.getName());
		productService.insertCategory(category);
		
		
		return "redirect:/product-list";
	}

	@PostMapping("/submitProduct")
	public String process(@Valid @ModelAttribute Productpo po, BindingResult bindingResult,Model model) {
		System.out.println("Reached ctrl >>>>"+po.toString());
	
		
		if (bindingResult.hasErrors()) {

			model.addAttribute("categoryList", productService.showCategory());
			model.addAttribute("value", "create");
			return "product-form";
		}else{

			if (po.getId() == 0) {
				
				Product product = new Product();
				product.setName(po.getName());
				product.setQuantity(po.getQuantity());
				product.setPrice(po.getPrice());
				int categoryId = po.getCategoryId();
	
				System.out.println("Product : " + po.toString());
				productService.insertProduct(product, categoryId);
	
				return "redirect:/product-list";
			} else {
	
				Product product = new Product();
				product.setId(po.getId());
				product.setName(po.getName());
				product.setQuantity(po.getQuantity());
				product.setPrice(po.getPrice());
	
				Category category = productService.categoryFindById(po.getCategoryId());
	
				product.setCategory(category);
	
				productService.update(product);
	
				return "redirect:/product-list";
			}
		}

	}

	@GetMapping("/product-list")
	public String show(Model model) {
		
		model.addAttribute("category", new Category());
		model.addAttribute("product", productService.showAll());

		return "product-list";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {

		Product p = productService.findById(id);
		Productpo productpo = new Productpo(p.getId(), p.getName(), p.getQuantity(), p.getPrice(),
				p.getCategory().getId(), p.getCategory().getName());


		model.addAttribute("productpo", productpo);

		model.addAttribute("categoryList", productService.showCategory());

		model.addAttribute("value", "edit");

		return "product-form";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id, Model model) {
		
		Product product = productService.findById(id);

		productService.delete(product);
		model.addAttribute("category", new Category());
		model.addAttribute("product", productService.showAll());

		return "product-list";
	}

	@GetMapping("/category-list")
	public String showCategoryList(Model model) {

		
		model.addAttribute("categoryList", productService.showCategory());

		return "category-list";
	}
	
	@GetMapping("/editcategory/{id}")
	public String editCategory(@PathVariable("id") int id, Model model){
		System.out.println("edit category >>>>");
		model.addAttribute("category", productService.categoryFindById(id));
		
		return "category-edit";
	}
	
	@PostMapping("/updatecategory")
	public String updateCategory(Category c){
		
		productService.updateCategory(c);
		
		return "redirect:/product-list";
	}
	
}