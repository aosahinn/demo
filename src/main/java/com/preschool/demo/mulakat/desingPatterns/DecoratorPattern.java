package com.preschool.demo.mulakat.desingPatterns;

// Temel bileşen
interface Coffee {
    String getDescription();

    double getCost();
}

// Temel bileşen
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Basit kahve";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

// Decorator sınıfı
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Decorator sınıfları
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", süt";
    }

    @Override
    public double getCost() {
        return super.getCost() + 2.0;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", şeker";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.0;
    }
}

// Kullanım
public class DecoratorPattern {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("İlk sipariş: " + coffee.getDescription() + ", fiyat: " + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println("Süt eklenmiş sipariş: " + coffee.getDescription() + ", fiyat: " + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println("Şeker eklenmiş sipariş: " + coffee.getDescription() + ", fiyat: " + coffee.getCost());
    }
}

