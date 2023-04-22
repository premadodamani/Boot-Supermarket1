package org.jsp.super_market.repository;

import java.util.List;

import org.jsp.super_market.dto.Merchant;
import org.jsp.super_market.dto.Product;
import org.jsp.super_market.helper.ResponseStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{

	ResponseStructure<List<Product>> findByStatus(boolean b);

	Product findByName(String name);
}
