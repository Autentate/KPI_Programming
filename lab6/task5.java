// Створити класи Product, StoreOffer, InternetShop та ProductSearchService, після цього виконати завдання №1, №2 та №3. 
// Використати ітератори для обходу колекцій. Завдання 1 - знайти мінімальну ціну на товар у всіх магазинах, 
// завдання 2 - знайти магазини з мінімальною ціною на товар, завдання 3 - перевірити чи є магазин, де всі 
// товари дешевші за рекомендовані ціни.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Product {
    private String name;
    private double recommendedPrice;

    public Product(String name, double recommendedPrice) {
        this.name = name;
        this.recommendedPrice = recommendedPrice;
    }

    public String getName() {
        return name;
    }

    public double getRecommendedPrice() {
        return recommendedPrice;
    }
}

class StoreOffer {
    private Product product;
    private double price;

    public StoreOffer(Product product, double price) {
        this.product = product;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }
}

class InternetShop {
    private String shopName;
    private List<StoreOffer> offers;

    public InternetShop(String shopName) {
        this.shopName = shopName;
        this.offers = new ArrayList<>();
    }

    public String getShopName() {
        return shopName;
    }

    public List<StoreOffer> getOffers() {
        return offers;
    }

    public void addOffer(StoreOffer offer) {
        offers.add(offer);
    }
}

class ProductSearchService {
    private List<InternetShop> shops;

    public ProductSearchService(List<InternetShop> shops) {
        this.shops = shops;
    }

    // Завдання №1
    public double findMinPrice(String productName) {
        double minPrice = -1;
        Iterator<InternetShop> shopIterator = shops.iterator();

        while (shopIterator.hasNext()) {
            InternetShop shop = shopIterator.next();
            Iterator<StoreOffer> offerIterator = shop.getOffers().iterator();

            while (offerIterator.hasNext()) {
                StoreOffer offer = offerIterator.next();
                if (offer.getProduct().getName().equalsIgnoreCase(productName)) {
                    if (minPrice == -1 || offer.getPrice() < minPrice) {
                        minPrice = offer.getPrice();
                    }
                }
            }
        }
        return minPrice;
    }

    // Завдання №2
    public List<InternetShop> findShopsWithMinPrice(String productName) {
        List<InternetShop> resultShops = new ArrayList<>();
        double minPrice = findMinPrice(productName);

        if (minPrice == -1) {
            return resultShops;
        }

        for (InternetShop shop : shops) {
            for (StoreOffer offer : shop.getOffers()) {
                if (offer.getProduct().getName().equalsIgnoreCase(productName)) {
                    if (offer.getPrice() == minPrice) {
                        resultShops.add(shop);
                        break;
                    }
                }
            }
        }
        return resultShops;
    }

    // Завдання №3
    public boolean hasShopWithAllProductsCheaperThanRecommended() {
        Iterator shopIt = shops.iterator();

        while (shopIt.hasNext()) {
            InternetShop shop = (InternetShop) shopIt.next();
            List<StoreOffer> offersList = shop.getOffers();

            if (offersList.isEmpty()) {
                continue;
            }

            boolean allCheaper = true;
            Iterator offerIt = offersList.iterator();

            while (offerIt.hasNext()) {
                StoreOffer offer = (StoreOffer) offerIt.next();
                if (offer.getPrice() >= offer.getProduct().getRecommendedPrice()) {
                    allCheaper = false;
                    break;
                }
            }

            if (allCheaper) {
                return true;
            }
        }
        return false;
    }
}

public class task5 {
    public static void main(String[] args) {
        Product phone = new Product("Smartphone", 10000.0);
        Product laptop = new Product("Laptop", 25000.0);

        InternetShop shop1 = new InternetShop("TechStore");
        shop1.addOffer(new StoreOffer(phone, 9500.0));
        shop1.addOffer(new StoreOffer(laptop, 24000.0));

        InternetShop shop2 = new InternetShop("BudgetElectro");
        shop2.addOffer(new StoreOffer(phone, 9500.0));
        shop2.addOffer(new StoreOffer(laptop, 26000.0));

        InternetShop shop3 = new InternetShop("SuperDiscount");
        shop3.addOffer(new StoreOffer(phone, 9000.0));
        shop3.addOffer(new StoreOffer(laptop, 24500.0));

        List<InternetShop> shopList = new ArrayList<>();
        shopList.add(shop1);
        shopList.add(shop2);
        shopList.add(shop3);

        ProductSearchService service = new ProductSearchService(shopList);

        // Тест задачі №1
        double minP = service.findMinPrice("Smartphone");
        System.out.println("1. Мінімальна ціна на Smartphone: " + minP);

        // Тест задачі №2
        List<InternetShop> cheapShops = service.findShopsWithMinPrice("Smartphone");
        System.out.print("2. Магазини з мінімальною ціною: ");
        for (InternetShop s : cheapShops) {
            System.out.print(s.getShopName() + " ");
        }
        System.out.println();

        // Тест задачі №3
        boolean hasCheapShop = service.hasShopWithAllProductsCheaperThanRecommended();
        System.out.println("3. Чи є магазин з усіма товарами дешевшими за рекомендовані: " + hasCheapShop);
    }
}