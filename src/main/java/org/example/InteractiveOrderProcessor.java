package org.example;

import java.util.Scanner;

public class InteractiveOrderProcessor {

    public static void main(String[] args) {
        //Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        //Get Order Data from User
        System.out.println("Welcome to the Interactive Order Processor!\n");
        System.out.println("--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Is customer a member (true/false)?: ");
        boolean isMember = scanner.nextBoolean();
        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        String customerTier = scanner.next();
        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        String shippingZone = scanner.next();
        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
        String discountCode = scanner.next();

        //OrderDetails
        System.out.println("\n--- Order Details ---");
        System.out.printf("Unit Price: $%.2f%n", unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);

        //Calculate initial sub-total
        double subtotal = unitPrice * quantity;
        subtotal = Math.round(subtotal * 100.0) / 100.0;
        System.out.println("\n--- Calculation Steps ---");
        System.out.printf("Initial Subtotal: $%.2f%n", subtotal);

        double discountTierPercentage;
        //Tier-based discount
        if(customerTier.equals("Gold")){
            discountTierPercentage = 15;
        } else if (customerTier.equals("Silver")) {
            discountTierPercentage = 10;
        } else {
            discountTierPercentage = 0.0;
        }

        subtotal = subtotal - (subtotal*(discountTierPercentage/100));
        subtotal = Math.round(subtotal * 100.0) / 100.0;
        System.out.println("After Tier Discount(" + customerTier + " - " + (int)discountTierPercentage + "%): $" + subtotal);

        //Quantity Discount
        if(quantity >= 5){
            subtotal = subtotal * 0.95;
            subtotal = Math.round(subtotal * 100.0) / 100.0;
            System.out.println("After Quantity Discount (5% for >=5 items): $" + subtotal);
        }

        boolean isFreeShipping = false;
        //Promotional Code Application
        if(discountCode.equals("SAVE10") && subtotal > 75.0){
            subtotal -= 10;
            subtotal = Math.round(subtotal * 100.0) / 100.0;
            System.out.println("After Promotional Code (SAVE10 for >$75): $" + subtotal);
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            isFreeShipping = true;
        }

        //Small Order Surcharge
        double surcharge = (subtotal <= 25) ? 3.00 : 0.0;
        subtotal += surcharge;
        subtotal = Math.round(subtotal * 100.0) / 100.0;
        System.out.println("After Small Order Surcharge (if applicable): $" + subtotal);

        //Shipping Cost Calculation
        double shippingCost = 0.0;
        if(!isFreeShipping){
            switch(shippingZone){
                case "ZoneA":
                    shippingCost = 5;
                    break;
                case "ZoneB":
                    shippingCost = 12.5;
                    break;
                case "ZoneC":
                    shippingCost = 20;
                    break;
                default:
                    shippingCost = 25;
            }
            System.out.println("\nShipping Cost: $" + shippingCost + " (" + shippingZone +")");
        }

        //Final Cost Computation
        double finalOrderTotal = subtotal + shippingCost;
        System.out.printf("Final Order Total: $%.2f%n", finalOrderTotal);


        //Part 2
        System.out.println("\n--- String Equality Demo ---\n");

        //Get String from User
        System.out.print("Enter first string for comparison: ");
        String firstString = scanner.next();
        System.out.print("Enter second string for comparison: ");
        String secondString = scanner.next();

        //String Equality demonstration
        System.out.println("String 1 == String 2: " + (firstString == secondString) + " (Compares references, which are different for user input strings)");
        System.out.println("String 1 .equals() String 2: " + (firstString.equals(secondString))  + " (Content is different due to case)");
        System.out.println("String 1 .equalsIgnoreCase() String 2: " + (firstString.equalsIgnoreCase(secondString)) + " (Content is identical, ignoring case)");

        /*
        Explanation:
        Using == to compare strings could give inaccurate results since this compares the reference or pointers to the
        string value, rather than comparing the string values themselves. This is why we should use .equals() or .equalsIgnoreCase()
        when comparing Strings since that it compares the content themselves.
         */

        //Close Scanner
        scanner.close();
    }
}
