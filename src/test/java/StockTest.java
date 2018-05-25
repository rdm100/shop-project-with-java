import models.Drink;
import models.Food;
import models.Product;
import models.Stock;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StockTest {
    Stock stock;
    Product drink;

    @Before
    public void setUp() throws Exception {
        stock = new Stock();
        drink = new Drink("Coke", 0.50, 330, 50.0, 0.05 , 40);
    }

    @Test
    public void canAddProductToStock() {
        stock.addProductToStock(drink);
        assertEquals(1, stock.stockListCount());
    }
}
