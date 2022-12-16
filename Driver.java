/**
 * Description: This program is a simple implementation
 * of a social network. We have utilized the Hashed Dictionary
 * and the Undirected Graph data structure to map the
 * different profiles and their data.
 *
 * Team Name: Runtime Errors
 * @authors Aung Bo Bo, Wai Han
 * Editor: VS Code
 */

import java.util.Scanner;
import java.util.Iterator;

public class Driver {
    private HashedDictionary<String, Profile> networkOfProfiles;
    private UndirectedGraph<Profile> friendNetwork;

    public static void main(String[] args) {
        Driver network = new Driver();
        network.run();
    }

    public Driver() {
        networkOfProfiles = new HashedDictionary<String, Profile>();
        friendNetwork = new UndirectedGraph<Profile>();
    }

    public void run() {
        String userOption = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your option:");
        System.out.println("1. Join the network");
        System.out.println("2. Leave the network\n");

        userOption = scanner.next();

        while (!userOption.toUpperCase().equals("0")) {
            if (userOption.toUpperCase().equals("1")) {
                userOption = userJoinedNetwork(scanner);
            } else {
                System.out.println("Please enter a valid option.\n");

                System.out.println("Please choose an option shown below:");
                System.out.println("1. Join the network");
                System.out.println("0. Leave the network\n");

                userOption = scanner.next();
            }
        }
    }

    public String userJoinedNetwork(Scanner scanner) {
        String userOption = "";

        System.out.println("WELCOME TO OUR SIMPLE SOCIAL NETWORK!\n");
        System.out.println("These are (" + networkOfProfiles.getSize() + ") profiles in the network: ");
        DisplayAllProfiles();
        System.out.println("\nPlease choose an option shown below:");
        System.out.println("1. Add a profile");
        System.out.println("2. Edit a profile");
        System.out.println("3. Search for a profile");
        System.out.println("4. Remove a profile");
        System.out.println("0. Leave the network\n");
        // System.out.println("\nPlease choose an option shown below:");
        // System.out.println("Search for a profile - Enter S");
        // System.out.println("Edit a profile - Enter E");
        // System.out.println("Add a profile - Enter A");
        // System.out.println("Remove a profile - Enter R");
        // System.out.println("Leave the network - Enter Q\n");

        userOption = scanner.next();

        while (!userOption.toUpperCase().equals("0")) {

            // Search for a profile
            if (userOption.toUpperCase().equals("3")) {
                System.out.print("Who would you like to search for?: ");
                searchProfile(scanner);

                // Add a profile
            } else if (userOption.toUpperCase().equals("1")) {

                System.out.print("\nEnter the name of the new profile?: ");
                scanner.nextLine();
                String userName = scanner.nextLine();
                System.out.print("Enter the status of the new profile?: ");
                String userStatus = scanner.nextLine();
                Profile newProfile = new Profile(userName, userStatus);

                System.out.println("Here is the information of the new profile:");
                friendNetwork.addVertex(newProfile);
                newProfile.displayProfile(friendNetwork.getBreadthFirstTraversal(newProfile), networkOfProfiles);

                networkOfProfiles.add(newProfile.getName(), newProfile);
                System.out.println("Profile successfully added to the network.\n");

                // Remove a profile
            } else if (userOption.toUpperCase().equals("4")) {
                System.out.print("\nWhat is the name of the profile you want to remove?: ");
                scanner.nextLine();
                userOption = scanner.nextLine();

                if (networkOfProfiles.remove(userOption) != null) {
                    System.out.println("Profile successfully removed from the network.\n");
                } else {
                    System.out.println("This profile does not exist.\n");
                }

                // Edit a profile
            } else if (userOption.toUpperCase().equals("2")) {
                System.out.print("Whose profile would you like to edit?: ");
                Profile profileToEdit = searchProfile(scanner);
                if (profileToEdit != null) {
                    editProfile(profileToEdit, scanner);
                }

                // Others
            } else {
                System.out.println("Please enter a valid option.\n");
            }

            System.out.println("\nPlease choose an option shown below:");
            System.out.println("These are (" + networkOfProfiles.getSize() + ") profiles in the network: ");
            DisplayAllProfiles();
            System.out.println("\nPlease choose an option shown below:");
            System.out.println("1. Add a profile");
            System.out.println("2. Edit a profile");
            System.out.println("3. Search for a profile");
            System.out.println("4. Remove a profile");
            System.out.println("0. Leave the network\n");
            // System.out.println("\nSearch for a profile - Enter S");
            // System.out.println("Edit a profile - Enter E");
            // System.out.println("Add a profile - Enter A");
            // System.out.println("Remove a profile - Enter R");
            // System.out.println("Leave the network - Enter Q\n");

            userOption = scanner.next();
        }

        return userOption;
    }

    public void DisplayAllProfiles() {
        Iterator<String> profiles = networkOfProfiles.getKeyIterator();
        String name = "";

        while (profiles.hasNext()) {
            name = profiles.next();

            if (profiles.hasNext()) {
                System.out.print(name + ", ");
            } else {
                System.out.println(name);
            }
        }
    }

    public Profile searchProfile(Scanner scanner) {
        String userOption = "";
        scanner.nextLine();

        userOption = scanner.nextLine();

        if (networkOfProfiles.contains(userOption)) {
            networkOfProfiles.getValue(userOption).displayProfile(
                    friendNetwork.getBreadthFirstTraversal(networkOfProfiles.getValue(userOption)), networkOfProfiles);
        } else {
            System.out.println("This profile does not exist.\n");
        }
        return networkOfProfiles.getValue(userOption);
    }

    public void editProfile(Profile profileToEdit, Scanner scanner) {
        String userOption = "";

        System.out.println("\nPlease choose an option shown below:");
        System.out.println("1. Update the profile name");
        System.out.println("2. Update the profile status");
        System.out.println("3. Add a friend");
        System.out.println("4. Finish editing\n");

        userOption = scanner.next();

        while (!userOption.toUpperCase().equals("D")) {

            if (userOption.toUpperCase().equals("N")) {
                System.out.print("\nWhat is the new name of the profile?: ");
                String newName = scanner.next();
                Profile newProf = new Profile(newName, networkOfProfiles.remove(profileToEdit.getName()).getStatus());
                networkOfProfiles.add(newName, newProf);
                System.out.println("Name successfully changed.");

            } else if (userOption.toUpperCase().equals("S")) {
                System.out.print("What is the status of the new profile?: ");
                String newStatus = scanner.next();
                profileToEdit.setStatus(newStatus);
                System.out.println("Status successfully changed.");

            } else if (userOption.toUpperCase().equals("A")) {
                addFriend(profileToEdit, scanner);
                // System.out.print("Who is the friend you would like to add for this profile?: ");
                // Profile friendToAdd = searchProfile(scanner);
                // if (friendToAdd != null) {
                //     boolean success = false;
                //     success = friendNetwork.addEdge(profileToEdit, friendToAdd, 1);

                //     if (success == true)
                //         System.out.println("Friend successfully added.");
                //     elsex
                //         System.out.println("Sorry, this friend could not be added!\n");
                // }
            }

            else {
                System.out.println("Please enter a valid option.\n");
            }

            System.out.println("\nPlease choose an option shown below:");
            System.out.println("1. Update the profile name");
            System.out.println("2. Update the profile status");
            System.out.println("3. Add a friend");
            System.out.println("4. Finish editing\n");


            userOption = scanner.next();
        }
    }

    public void addFriend(Profile profileToEdit, Scanner scanner) {
        System.out.print("Who is the friend you would like to add for this profile?: ");
        Profile friendToAdd = searchProfile(scanner);
        if (friendToAdd != null) {
            boolean success = false;
            success = friendNetwork.addEdge(profileToEdit, friendToAdd, 1);

            if (success == true)
                System.out.println("Friend successfully added.");
            else
                System.out.println("Sorry, this friend could not be added!\n");
        }
    }
}
