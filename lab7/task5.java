// Виконайте завдання попереднього практикума, замінивши списки List (ArrayList та LinkedList) на множини Set (TreeSet). 
// При цьому необхідно щонайменше один раз використати інтерфейс Comparable та щонайменше один раз — Comparator.

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class Product implements Comparable<Product> {
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
    public int compareTo(Product other) {
        return name.compareTo(other.name);
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

class InternetShop implements Comparable<InternetShop> {
    private String shopName;
    private Set<StoreOffer> offers;

    public InternetShop(String shopName) {
        this.shopName = shopName;
        this.offers = new TreeSet<>(new Comparator<StoreOffer>() {
            @Override
            public int compare(StoreOffer first, StoreOffer second) {
                int priceComparison = Double.compare(first.getPrice(), second.getPrice());
                if (priceComparison != 0) {
                    return priceComparison;
                }
                return first.getProduct().getName().compareTo(second.getProduct().getName());
            }
        });
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
    public int compareTo(InternetShop other) {
        return shopName.compareTo(other.shopName);
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
        Set<InternetShop> resultShops = new TreeSet<>();
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

        Set<InternetShop> shopSet = new TreeSet<>();
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
    }
}