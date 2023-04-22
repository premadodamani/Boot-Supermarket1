package org.jsp.super_market.service;

import java.util.List;

import org.jsp.super_market.dao.ProductDao;
import org.jsp.super_market.dto.Product;
import org.jsp.super_market.exception.AllException;
import org.jsp.super_market.helper.Login;
import org.jsp.super_market.helper.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	ProductDao dao;

	public ResponseStructure<Login> login(Login login)throws AllException
	{
	ResponseStructure<Login> structure=new ResponseStructure<>();
	if(login.getId().equals("admin")) {
		if(login.getPassword().equals("admin")) {
			structure.setData(login);
            structure.setMessage("Login Success");
            structure.setStatuscode(HttpStatus.ACCEPTED.value());
            return structure;
		}else {
			throw new AllException("Invalid Password");
		}
	}else {
		throw new AllException("Invalid Id");
	}
	}
	
	public ResponseStructure<List<Product>> fetchProducts() {
		ResponseStructure<List<Product> > structure=new ResponseStructure<>();
		
		List<Product> list=dao.findAll();
		
		structure.setMessage("Product Found");
		structure.setData(list);
		structure.setStatuscode(HttpStatus.FOUND.value());
		return structure;
	}

	public ResponseStructure<Product> changeStatus(int pid) {
		ResponseStructure<Product>structure=new ResponseStructure<>();
		
		Product product=dao.find(pid);
		if(product.isStatus())
		
			product.setStatus(false);
		
			else 
				product.setStatus(true);
			
		product.setStatus(true);
		dao.save(product);
		
		structure.setMessage("Changed Status Successfully");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		structure.setData(dao.save(product));
		
		return structure;
	}

}
