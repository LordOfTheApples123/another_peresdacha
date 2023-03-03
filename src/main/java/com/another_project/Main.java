package com.another_project;

import com.another_project.entity.Auto;
import com.another_project.entity.Client;
import com.another_project.entity.Rent;
import com.another_project.entity.Violation;
import com.another_project.query.DBService;

public class Main {



    public static void main(String[] args) {
        try {
            DBService dbService = DBService.getInstance();
//            for(int i = 0; i < 10; i++){
//                Auto auto = new Auto("model" + i, "number" + i);
//                dbService.insertAuto(auto);
//            }
            for (int i = 0; i < 1025; i++) {
                Client client = new Client("name" + i);
                dbService.insertClient(client);
//                Violation violation = new Violation(i+1, 1, "lorem ipsum" + i);
//                Rent rent1 = new Rent(i+1, 2);
//                Rent rent2 = new Rent(i+1, 3);
//                Rent rent3 = new Rent(i+1, 4);
//                Rent rent4 = new Rent(i+1, 5);
//                dbService.insertViolation(violation);
//                dbService.insertRent(rent1);
//                dbService.insertRent(rent2);
//                dbService.insertRent(rent3);
//                dbService.insertRent(rent4);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
