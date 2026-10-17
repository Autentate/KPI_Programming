// Виконайте завдання попереднього практикума, замінивши TreeSet на HashSet.
// Додайте функціональність з використанням Map (TreeMap або HashMap).

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        StoreOffer offer = (StoreOffer) o;
        return Double.compare(offer.price, price) == 0 && Objects.equals(product, offer.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, price);
    }
}

class InternetShop {
    private String shopName;
    private Set<StoreOffer> offers;

    public InternetShop(String shopName) {
        this.shopName = shopName;
        this.offers = new HashSet<>();
    }

    public String getShopName() {
        return shopName;
    }

    public Set<StoreOffer> getOffers() {
        return offers;
    }

    public void addOffer(StoreOffer offer) {
        offers.add(offer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InternetShop shop = (InternetShop) o;
        return Objects.equals(shopName, shop.shopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopName);
    }
}

class ProductSearchService {
    private Set<InternetShop> shops;

    public ProductSearchService(Set<InternetShop> shops) {
        this.shops = shops;
    }

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

    public Set<InternetShop> findShopsWithMinPrice(String productName) {
        Set<InternetShop> resultShops = new HashSet<>();
        double minPrice = findMinPrice(productName);

        if (minPrice == -1) {
            return resultShops;
        }

        Iterator<InternetShop> shopIterator = shops.iterator();
        while (shopIterator.hasNext()) {
            InternetShop shop = shopIterator.next();
            Iterator<StoreOffer> offerIterator = shop.getOffers().iterator();
            while (offerIterator.hasNext()) {
                StoreOffer offer = offerIterator.next();
                if (offer.getProduct().getName().equalsIgnoreCase(productName)) {
                    if (Double.compare(offer.getPrice(), minPrice) == 0) {
                        resultShops.add(shop);
                        break;
                    }
                }
            }
        }
        return resultShops;
    }

    public boolean hasShopWithAllProductsCheaperThanRecommended() {
        Iterator<InternetShop> shopIt = shops.iterator();

        while (shopIt.hasNext()) {
            InternetShop shop = shopIt.next();
            Set<StoreOffer> offersSet = shop.getOffers();

            if (offersSet.isEmpty()) {
                continue;
            }

            boolean allCheaper = true;
            Iterator<StoreOffer> offerIt = offersSet.iterator();

            while (offerIt.hasNext()) {
                StoreOffer offer = offerIt.next();
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

    public Map<String, Double> getBestPriceCatalog() {
        Map<String, Double> bestPrices = new HashMap<>();

        for (InternetShop shop : shops) {
            for (StoreOffer offer : shop.getOffers()) {
                String productName = offer.getProduct().getName();
                double currentPrice = offer.getPrice();

                if (!bestPrices.containsKey(productName) || currentPrice < bestPrices.get(productName)) {
                    bestPrices.put(productName, currentPrice);
                }
            }
        }
        return bestPrices;
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

        Set<InternetShop> shopSet = new HashSet<>();
        shopSet.add(shop1);
        shopSet.add(shop2);
        shopSet.add(shop3);

        ProductSearchService service = new ProductSearchService(shopSet);

        double minP = service.findMinPrice("Smartphone");
        System.out.println("1. Мінімальна ціна на Smartphone: " + minP);

        Set<InternetShop> cheapShops = service.findShopsWithMinPrice("Smartphone");
        System.out.print("2. Магазини з мінімальною ціною: ");
        Iterator<InternetShop> shopIterator = cheapShops.iterator();
        while (shopIterator.hasNext()) {
            System.out.print(shopIterator.next().getShopName() + " ");
        }
        System.out.println();

        boolean hasCheapShop = service.hasShopWithAllProductsCheaperThanRecommended();
        System.out.println("3. Чи є магазин з усіма товарами дешевшими за рекомендовані: " + hasCheapShop);

        System.out.println("\nКаталог найнижчих цін");
        Map<String, Double> catalog = service.getBestPriceCatalog();
        for (Map.Entry<String, Double> entry : catalog.entrySet()) {
            System.out.println("Товар: " + entry.getKey() + "\nНайкраща ціна: " + entry.getValue());
        }
    }
}