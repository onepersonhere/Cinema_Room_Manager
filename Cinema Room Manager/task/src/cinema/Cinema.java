package cinema;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Array;
import java.util.Scanner;


public class Cinema {

    static void showCinema(int row, int column, String[][] arr){
        System.out.println("Cinema:");
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= column; j++) {
                if(j < column) {
                    System.out.print(arr[i][j] + " ");
                }else if(j == column){
                    System.out.print(arr[i][j]);
                }
            }
            System.out.println();
        }
    }
    static String[][] generateCinema(int row, int column) {
        String[][] arr = new String[row + 1][column + 1];
        for(int i = 0; i <= row; i++){
            if(i == 0){
                arr[0][0] = " ";
                for (int j = 1; j <= column; j++) {
                    String str = Integer.toString(j);
                    arr[0][j] = str;
                }
            }else{
                for (int j = 0; j <= column; j++) {
                    if(j == 0){
                        String str = Integer.toString(i);
                        arr[i][0] = str;
                    }else{
                        arr[i][j] = "S";
                    }
                }
            }
        }
        return arr;
    }
    static String[][] buyTickets(int row,int column, String[][] arr){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int chosenColumn = scanner.nextInt();
        ticketPrice(row, column, chosenRow, chosenColumn);
        if(chosenColumn > column || chosenRow > row){
            System.out.println("Wrong input!");
            return buyTickets(row, column, arr);
        }
        if(arr[chosenRow][chosenColumn] != "B") {
            arr[chosenRow][chosenColumn] = "B";
        }else{
            System.out.println("That ticket has already been purchased!");
            return buyTickets(row, column, arr);
        }
        return arr;
    }

    static void ticketPrice(int row, int column, int chosenRow, int chosenColumn){
        int price = 0;
        int total = row * column;
        if(total <= 60){
            price = 10;
        }else{
            double r = Math.floor(row/2);
            if(chosenRow <= r){
                price = 10;
            }else{
                price = 8;
            }
        }
        System.out.println("Ticket price: $" + price);
    }
    static void statistics(int row, int column, String[][] arr){
        int noOfS = 0, noOfB = 0;
        double r = Math.floor(row/2);
        int total = 0;
        int maxTotal = 0;
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= column; j++){
                if(arr[i][j] == "S"){
                    noOfS++;
                    if(row * column <= 60){
                        maxTotal += 10;
                    }else{
                        if(i <= r){
                            maxTotal += 10;
                        }else{
                            maxTotal += 8;
                        }
                    }
                }
                else if(arr[i][j] == "B"){
                    noOfB++;
                    if(row * column <= 60){
                        total += 10;
                        maxTotal += 10;
                    }else{
                        if(i <= r){
                            total += 10;
                            maxTotal += 10;
                        }else{
                            total += 8;
                            maxTotal += 8;
                        }
                    }
                }
            }
        }
        System.out.println("Number of purchased tickets: " + noOfB);
        double percentage = (noOfB/(double)(row * column)) * 100;
        if(percentage == 0){
            System.out.println("Percentage: 0.00%");
        }else {
            System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        }

        System.out.println("Current income: $" + total);
        System.out.println("Total income: $" + maxTotal);
    }
    static boolean query(int row, int column, String[][] arr){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n" +
                "1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit\n");

        int query = scanner.nextInt();

        if(query == 1){
            showCinema(row,column, arr);
        }
        else if(query == 2){
            arr = buyTickets(row, column, arr);
        }
        else if(query == 3){
            statistics(row, column, arr);
        }
        else if(query == 0){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seatsPerRow = scanner.nextInt();

        String[][] arr = new String[rows + 1][seatsPerRow + 1];

        arr = generateCinema(rows, seatsPerRow);

        boolean bool = true;
        while (bool) {
            bool = query(rows, seatsPerRow, arr);
        }
    }
}