package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalorieCount {
    public static void main(String[] args) {
        System.out.println("Fall 2023 - CS1083 - Section 003 - Project 1 – CalorieCount - written by LUCASHSIEH");

        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> caloriesPerDay = new HashMap<>();

        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.print("Calories burnt on Monday: ");
                    break;
                case 1:
                    System.out.print("Calories burnt on Tuesday: ");
                    break;
                case 2:
                    System.out.print("Calories burnt on Wednesday: ");
                    break;
                case 3:
                    System.out.print("Calories burnt on Thursday: ");
                    break;
                case 4:
                    System.out.print("Calories burnt on Friday: ");
                    break;
                case 5:
                    System.out.print("Calories burnt on Saturday: ");
                    break;
                case 6:
                    System.out.print("Calories burnt on Sunday: ");
                    break;
                default:
                    break;
            }
            caloriesPerDay.put(i, scanner.nextInt());
        }

        System.out.print("First day of the month (0-Mon, 1-Tue, 2-Wed, 3-Thu, 4-Fri, 5-Sat, 6-Sun): ");
        int firstDayOfMonth = scanner.nextInt();

        System.out.print("First Holiday (day of the month): ");
        int firstHoliday = scanner.nextInt();
        System.out.print("Second Holiday (day of the month): ");
        int secondHoliday = scanner.nextInt();
        System.out.print("Third Holiday (day of the month): ");
        int thirdHoliday = scanner.nextInt();
        System.out.println();

        int dayOfMonth = 0;
        int[][] calendar = new int[5][8];

        boolean LessThan30Days = true;
        System.out.print("Week            Monday          Tuesday         Wednesday       Thursday");
        System.out.println("        Friday          Saturday        Sunday          Total/Week");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./CalorieCountOutput.txt"))) {
            writer.write("Fall 2023 - CS1083 - Section 003 - Project 1 – CalorieCount - written by LUCASHSIEH");
            writer.newLine();

            writer.write(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s", "Week", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Total/Week"));

            writer.newLine();

            for (int i = 0; i < calendar.length; i++) {
                int caloriesPerWeek = 0;
                System.out.print((i + 1) + "\t\t");
                writer.write(String.format("%-15d", (i + 1)));
                for (int j = 0; j < calendar[0].length; j++) {
                    
                	if (j == 7) {
                        calendar[i][j] = caloriesPerWeek;
                        caloriesPerWeek = 0;
                    } else if ((dayOfMonth + 1 == firstHoliday) || (dayOfMonth + 1 == secondHoliday) || (dayOfMonth + 1 == thirdHoliday)) {
                    	calendar[i][j] = 0;
                    	dayOfMonth +=1;
                    } else if ((i == 0) && (j < firstDayOfMonth)) {
                        calendar[i][j] = 0;
                    } else if ((i == 4) && (firstDayOfMonth < 5)) {
                        if ((j > firstDayOfMonth + 1)) {
                            calendar[i][j] = 0;
                        } else {
                            calendar[i][j] = caloriesPerDay.get(j);
                            caloriesPerWeek += calendar[i][j];
                        }
                    } else {
                        calendar[i][j] = caloriesPerDay.get(j);
                        caloriesPerWeek += calendar[i][j];
                    }

                    if (dayOfMonth == 30) {
                        dayOfMonth = 0;
                        LessThan30Days = false;
                    }
                    if ((calendar[i][j] != 0) && (LessThan30Days) && (j != 7)) {
                        dayOfMonth += 1;
                    }
                    
                    if (j != 7) {
                        System.out.print(dayOfMonth + "-" + calendar[i][j] + "\t\t");
                        writer.write(String.format("%-15s", dayOfMonth + "-" + calendar[i][j]));
                    } else {
                        System.out.print("W" + (i + 1) + "-" + calendar[i][j] + "\t\t");
                        writer.write(String.format("%-15s", "W" + (i + 1) + "-" + calendar[i][j]));
                    }
                }
                System.out.println();
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}