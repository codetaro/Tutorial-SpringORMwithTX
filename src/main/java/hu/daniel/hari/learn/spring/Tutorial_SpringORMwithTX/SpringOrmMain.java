package hu.daniel.hari.learn.spring.Tutorial_SpringORMwithTX;

import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import hu.daniel.hari.learn.spring.Tutorial_SpringORMwithTX.model.Product;
import hu.daniel.hari.learn.spring.Tutorial_SpringORMwithTX.service.ProductService;

/**
 * Hello world!
 *
 */
public class SpringOrmMain 
{
    public static void main( String[] args ) {
    	//Create Spring application context
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
    	
    	//Get service from context. (service's dependency ProductDao is autowired 
    	// in ProductService)
    	ProductService productService = ctx.getBean(ProductService.class);
    	
    	//Do some data operation
    	productService.add(new Product(1, "Bulb"));
    	productService.add(new Product(2, "Dijone mustard"));
    	System.out.println("listAll: " + productService.listAll());

    	//Test transaction rollback (duplicated key)
    	try {
    		productService.addAll(Arrays.asList(new Product(3, "Book"), 
    				new Product(4, "Soap"), new Product(1, "Computer")));
    	} catch (DataAccessException dataAccessException) {
    	}
    	
    	//Test element list after rollback
    	System.out.println("listAll: " + productService.listAll());
    	
    	ctx.close();
    }
}
