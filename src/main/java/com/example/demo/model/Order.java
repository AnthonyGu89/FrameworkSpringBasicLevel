package com.example.demo.model;

import java.util.Objects;

public class Order {
    private String title;
    private Integer price;
    private Integer sale;

    public Order(String title, Integer price, Integer sale) {
        this.title = title;
        this.price = price;
        this.sale = sale;
//        this.title - это свойства Мы создали
    }

    public String getTitle() {
        return title;
    }
    public Integer getSale() {
        return sale;
    }
    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
//        Тут Мы записываем в Наш массив значение переменной о
        return Objects.equals(title, order.title) &&
                Objects.equals(price, order.price) &&
                Objects.equals(sale, order.sale);
    }

    @Override
    public int hashCode() {
//        hashCode - хеширование, безопасное внесение данных
        return Objects.hash(title, price,sale);
    }

    @Override
    public String toString() {
//        toString - это функция возвращает результат всего массива, который Мы накапливаем
        return "Order{" +
                "title='" + title + '\'' +
                ", price=" + price + '\'' +
                ", sale=" + sale +
                '}';
//        '\'' - Так в Java записывается перенос на новую строку
    }
}
