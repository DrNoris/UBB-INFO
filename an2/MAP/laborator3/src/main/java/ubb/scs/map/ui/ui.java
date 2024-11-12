package ubb.scs.map.ui;

import ubb.scs.map.domain.Utilizator;
import ubb.scs.map.domain.Prietenie;
import ubb.scs.map.service.Service;

import java.util.*;

public class ui {
    private final Service service;
    private final Scanner scanner;

    public ui(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Add Friendship");
            System.out.println("4. Delete Friendship");
            System.out.println("5. Number of networks");
            System.out.println("6. Largest network");
            System.out.println("7. View all users");
            System.out.println("8. View all friendships");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    addFriendship();
                    break;
                case 4:
                    deleteFriendship();
                    break;
                case 5:
                    nrNetworks();
                    break;
                case 6:
                    longestRoad();
                    break;
                case 7:
                    viewAllUsers();  // New case for viewing all users
                    break;
                case 8:
                    viewAllFriendships();  // New case for viewing all users
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return; // Exit the loop and end the program
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void viewAllUsers() {
        List<Utilizator> users = service.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of all users:");
            for (Utilizator user : users) {
                System.out.println("ID: " + user.getId() + ", First Name: " + user.getFirstName() + ", Last Name: " + user.getLastName());
            }
        }
    }

    private void viewAllFriendships(){
        List<Prietenie> friendships = service.getAllFriendships();
        if (friendships.isEmpty()) {
            System.out.println("No friendships found.");
        } else {
            System.out.println("List of all users:");
            for (Prietenie friendship : friendships) {
                System.out.println(friendship.getId().getLeft() + " -> " + friendship.getId().getRight() + " " + friendship.getDate().toString());
            }
        }

    }

    private void longestRoad() {
        try {
            Map.Entry<List<Long>, Integer> result = service.longestNetwork();
            List<Long> longestPathNodes = result.getKey();
            int longestPathLength = result.getValue();

            System.out.println("Longest road length: " + longestPathLength);
            System.out.println("Nodes in longest path: " + longestPathNodes);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e);
        } catch (Throwable e) {
            System.out.println("A throwable error occurred: " + e);
        }
    }

    private void nrNetworks() {
        try{
            System.out.println("There are " + service.getNrNetworks() + " different networks");
        }catch (IllegalArgumentException e){
            System.out.println("dsadsada");
        } catch (Exception e) {
            System.out.println(e.toString());
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
    }

    private int getUserChoice() {
        while (true) {
            try {
                return scanner.nextInt(); // Get user input for menu choice
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private void addUser() {
//        System.out.print("Enter User ID: ");
//        Long id = scanner.nextLong();
        scanner.nextLine();  // Consume the newline
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        try {
            Optional<Utilizator> optionalUser = service.addUtilizator(firstName, lastName);
            if (optionalUser.isEmpty()) {
                System.out.println("User added.");
            } else {
                System.out.println("User could not be added. An existing user was found: " + optionalUser.get().getFirstName() + " " + optionalUser.get().getLastName());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        Long id = scanner.nextLong();

        try {
            Optional<Utilizator> deletedUser = service.deleteUtilizator(id);
            if (deletedUser.isPresent()) {
                System.out.println("User deleted: " + deletedUser);
            } else {
                System.out.println("User not found.");
            }
        }catch (IllegalArgumentException e){
            System.out.println("dsadsada");
        } catch (Exception e) {
            System.out.println(e.toString());
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
    }

    private void addFriendship() {
        System.out.print("Enter ID of the first user: ");
        Long id1 = scanner.nextLong();
        System.out.print("Enter ID of the second user: ");
        Long id2 = scanner.nextLong();

        try {
            Optional<Prietenie> friendship = service.addPrietenie(id1, id2);
            if (friendship.isEmpty()) {
                System.out.println("Friendship added");
            } else {
                System.out.println("Friendship could not be added (check user IDs).");
            }
        }
        catch (IllegalArgumentException | IllegalStateException e){
            System.out.println(e.toString());
        }
    }

    private void deleteFriendship() {
        System.out.print("Enter ID of the first user: ");
        Long id1 = scanner.nextLong();
        System.out.print("Enter ID of the second user: ");
        Long id2 = scanner.nextLong();

        try {
            Optional<Prietenie> success = service.deletePrietenie(id1, id2);
            if (success.isPresent()) {
                System.out.println("Friendship deleted.");
            } else {
                System.out.println("Friendship not found.");
            }
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.toString());
        }
    }


}
