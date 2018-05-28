import models.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BasketTest {


    Stock stock;
    Product drink1;
    Product drink2;
    Basket basket;
    Customer customer;

    @Before
    public void setUp() throws Exception {
        stock = new Stock();
        stock.addProductToStock(drink1);
        stock.addProductToStock(drink2);
        customer = new Customer("Roberto", 39, 10000);
        basket = new Basket(customer);
        drink1 = new Drink("Coke", 0.50, 330, 50.0, 0.05 , 40);
        drink2 = new Drink("Pespi", 0.50, 330, 50.0, 0.05 , 40);
        basket.addProducttoBasket(drink1, stock);
        basket.addProducttoBasket(drink2, stock);

    }

    @Test
    public void checkBasketRunningTotal() {
        assertEquals(1.00, basket.calculateTotalCostOfAllItemsInBasket() );
    }

    @Test
    public void addItemsToBasket() {
        assertEquals(2, basket.countProductsInBasket());
    }

    @Test
    public void canRemoveFromBasket() {
        basket.removeProducttoBasket(drink1, stock);
        assertEquals(1, basket.countProductsInBasket());
    }
}


