package com.plurasight.sneakerdrops;

import com.plurasight.sneakerdrops.data.BrandRepository;
import com.plurasight.sneakerdrops.data.SneakerRepository;
import com.plurasight.sneakerdrops.models.Brand;
import com.plurasight.sneakerdrops.models.Sneaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final SneakerRepository sneakerRepository;

    @Autowired
    public StartupRunner(BrandRepository brandRepository, SneakerRepository sneakerRepository) {
        this.brandRepository = brandRepository;
        this.sneakerRepository = sneakerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running){
            System.out.println("\n ===== Sneaker =====");
            System.out.println("1) List all sneakers");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            switch (scanner.nextInt()){
                case 1 -> listSneaker();
                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }


    }

    private void listSneaker(){
        System.out.println("You have " + sneakerRepository.count() + " sneakers:");
        for (Sneaker s : sneakerRepository.findAll()){
            System.out.println(s.getId() + " - " + s.getModel() + " (" + s.getPrice() + ")");
        }
    }

    private void seedData(){
        if(brandRepository.count() == 0){
            brandRepository.save(new Brand("Nike"));
            brandRepository.save(new Brand("Adidas"));
            brandRepository.save(new Brand("New Balance"));

        }

        if(sneakerRepository.count() == 0){
            sneakerRepository.save(new Sneaker("Air Jordan 1", 65, 1985));
            sneakerRepository.save(new Sneaker("Air Force 1", 90, 1982));
            sneakerRepository.save(new Sneaker("Yeezy Boost 250 V2", 220, 2016));
            sneakerRepository.save(new Sneaker("Adidas Superstar", 100, 1970));
            sneakerRepository.save(new Sneaker("New Balance 550", 110, 1989));
            sneakerRepository.save(new Sneaker("New Balance 990", 100, 1982));
        }
    }
}
