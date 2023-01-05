package com.example.demo.controller;

import com.example.demo.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;

@Controller
public class HomeController {

    private static Set<Order> orders = new HashSet<>();
//      Set - он должен добавлять, заносить, сохранять что нибудь в массив
//    HashSet - запускает возможность работы с массивом
    static {
        orders.add(new Order("iphone", 123,10));
        orders.add(new Order("book", 321,15));
        orders.add(new Order("chair", 567,20));
    }

    @GetMapping("/home")
    public String home(@RequestParam(required = false) String Tovar, Model model) {
//        @RequestParam - это значит что функция может принимать данные
        System.out.println("login is: " + Tovar);
        model.addAttribute("login", Tovar);
        return "home_page";
    }
    @GetMapping("/about")
    public String about(@RequestParam(required = false) String Login, Model model){
        if(Login.equals("john")){
            System.out.println("Вы Авторизированы");
        } else {
            System.out.println("Вы не авторизированы");
        }
        model.addAttribute("first","Hello about page");
        return "Index1";
    }
    @GetMapping("/contact")
        public String contact(@RequestParam(required = false) String errow1, Model model, HttpServletRequest request) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/store2", "root", ""); // Выполняем подключение к БД
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id,tovarname,idbasket,price from orders");
        ArrayList <String[]> Nick = new ArrayList<String[]>();
        model.addAttribute("massive2", Nick);
        System.out.println("123");
        while(resultSet.next()){
            String id = resultSet.getString("id");
            String tovarname = resultSet.getString("tovarname");
            String idbasket = resultSet.getString("idbasket");
            String price = resultSet.getString("price");
            Nick.add(new String[] {id,tovarname,price});
//            System.out.println(id+" "+tovarname+" "+idbasket+" "+price);
        }
        resultSet.close();
        statement.close();
        System.out.println(Nick);
        try (connection) {
            System.out.println("DB connected"); // Проверяем установилось ли соединение с БД
            byte[] data1 = "Hello World".getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(data1);
            System.out.println(digest);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        int[] price = {750,850,950};
//        int i = 0;
        String[] pupil = {"Taras","Anton", "Oleg"};
        request.getSession().setAttribute("Session",price[0]);
        System.out.println(request.getSession().getAttribute("Session"));
        System.out.println();
        model.addAttribute("first","Hello contacts");
        model.addAttribute("second","Welcome to Ukraine");
        model.addAttribute("third",12345);
        model.addAttribute("myarray",price);
        model.addAttribute("pupil1", pupil);
        model.addAttribute("massive",orders);
        model.addAttribute("oshibka",errow1);
//        if(errow1.equals("login")){
//            model.addAttribute("oshibka","Вы ввели неверный логин");
//            return "Index";
//        }
        model.addAttribute("color","");
        System.out.println("12345");
        if(errow1 != null){
            if(errow1.equals("login") || errow1.equals("password")){
            System.out.println("Red");
            model.addAttribute("color","red");
        }


//            System.out.println("Не верный логин");
//            System.out.println(errow1.equals("login"));
//        }
//        if(errow1.equals("login") || errow1.equals("password")){
//            System.out.println("Red");
        }
        return "Index";
    }
    @GetMapping("/player")
    public String player(@RequestParam(defaultValue="") String Age, String Age2, Model model){
        System.out.println(Age2);
        model.addAttribute("Age1", Age);
        model.addAttribute("Age3", Age2);
        return "player";
    }
    @GetMapping("/lk")
        public String lk(Model model, HttpSession session,HttpServletRequest request){
//        request.getSession().setAttribute("MY_SESSION_MESSAGES", "Jon");
//        request.getSession().getAttribute("MY_SESSION_MESSAGES");
        System.out.println("Привет " + request.getSession().getAttribute("MY_SESSION_MESSAGES"));
        model.addAttribute("name", request.getSession().getAttribute("MY_SESSION_MESSAGES") );
            return "lk";
        }
    @GetMapping("/enter")
    public String enter(HttpServletRequest request){
        request.getSession().setAttribute("MY_SESSION_MESSAGES", "");
        System.out.println(request.getSession().getAttribute("MY_SESSION_MESSAGES"));
        return "redirect:/contact";
    }

    @PostMapping("/Mypost")
    public String Mypost(@RequestParam() String Login5, String password, Model model, HttpServletRequest request) throws URISyntaxException {
        System.out.println(Login5);
        System.out.println(password);
        if(Login5.equals("gubar89") && password.equals("galerey")){
            System.out.println("lk");
            request.getSession().setAttribute("MY_SESSION_MESSAGES", Login5);
            return "redirect:/lk";
        } else
        if(!Login5.equals("gubar89")){
            model.addAttribute("errow1","login");
            return "redirect:/contact?errow1=login";
        }
        else
        if(!password.equals("galerey")){
            model.addAttribute("errow1","password");
            return "redirect:/contact?errow1=password";
        }else
        {
            System.out.println("Index");
            model.addAttribute("errow1","Оба параметра не верны");
//            String urlRedirect = "contact"  + "?errow1=login";

//            return ResponseEntity.created(new URI(urlRedirect)).build();
//            return new ModelAndView("redirect:/contact", model);
            return "redirect:/contact?errow1=login";
        }
//        return "player";
    }
    @GetMapping("/rozetka")
     public String rozetka(Model model){
        String[][] nested = {{ "Apple", "Iphone","Black","1199usd"}, { "Samsung", "S22Ultra","White","889usd"},{ "Xiaomi", "Mi11lite","blue","299usd"}};
        model.addAttribute("price",nested);
        return "rozetka";
    }
    @GetMapping("/tovar")
    public String tovar(@RequestParam(defaultValue="") String Top, Model model){
        System.out.println(Top.length());
        System.out.println("123");
        String[][] nested = {{ "Apple", "Iphone","Black","1199usd","/Img/iphone.png"}, { "Samsung", "S22Ultra","White","889usd","/Img/samsung.png"},{ "Xiaomi", "Mi11lite","blue","299usd","/Img/xiaomi .png"}};
          System.out.println(Top);
//        if(Top.equals("Apple")){
//            model.addAttribute("price", nested[0]);
//        }
//        if(Top.equals("Samsung")){
//            model.addAttribute("price", nested[1]);
//        }
        int i=0;
        int b=0;
        for(i=0;i< nested.length;i++){
            System.out.println(nested[i][0]);
            if(Top.equals(nested[i][0])){
                b= b+1;
                System.out.println("Привет");
                model.addAttribute("price",nested[i]);
                return "tovar";
            }
        }
        System.out.println(b);
        if(b==0){
            System.out.println("Ошибка");
            model.addAttribute("price1","Вы ввели неправильное значение");
            return "tovar";
        }
        return "tovar";
    }
    @GetMapping("/Anton")
    public String Anton(HttpServletRequest request){
        System.out.println(request.getSession().getAttribute("MY_SESSION_MESSAGES"));
        return "errow";
    }
}
