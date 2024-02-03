package dao;

import model.Product;

public class ProductDao extends EntityDao<Product> {
    public ProductDao() {
        super(Product.class);
    }
}