import java.sql.*;
import java.util.Scanner;

public class WorkerFinderSystem {
    private static final String url = "jdbc:mysql://localhost:3306/workersystem ";
    private static final String userName = "root";
    private static final String passWord = "@aryan@2014";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(url,userName,passWord);
            Statement statement = connection.createStatement();
            while(true){
                System.out.println("WORKER FINDERING SYSTEM");
                System.out.println("For Workers:");
                System.out.println("1. Register Worker AC.");
                System.out.println("2. View Finders");
                System.out.println("3. Get Hired By Finders");
                System.out.println("4. Update Worker Details");
                System.out.println("5. Work Done Of Worker");
                System.out.println("98. Delete Your Worker Account");
                System.out.println();
                System.out.println("For Finders:");
                System.out.println("6. Register Finder AC.");
                System.out.println("7. View Workers");
                System.out.println("8. Hire The Workers");
                System.out.println("9. Update Finder Details");
                System.out.println("10. Work Done of Finder");
                System.out.println("99. Delete Your Finder Account");
                System.out.println("0. Exits");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter Your Option : ");
                int option = scanner.nextInt();
                switch (option){
                    case 1:{
                        registerWorker(scanner,statement);
                        viewWorkers(statement);
                        break;
                    }
                    case 2:{
                        viewFinders(statement);
                        break;
                    }
                    case 3:{
                        hiredByFinders(scanner,statement);
                        break;
                    }
                    case 4:{
                        updateWorkerDetail(scanner,statement);
                        break;
                    }
                    case 5:{
                        workerworkDone(scanner,statement);
                        break;
                    }
                    case 98: {
                        WorkerAcDelete(scanner,statement);
                        break;
                    }
                    case 6:{
                        registerFinder(scanner,statement);
                        viewFinders(statement);
                        break;
                    }
                    case 7:{
                        viewWorkers(statement);
                        break;
                    }
                    case 8:{
                        hireWorker(scanner,statement);
                        break;
                    }
                    case 9:{
                        updateFinderDetail(scanner,statement);
                        break;
                    }
                    case 10:{
                        finderworkDone(scanner,statement);

                        break;
                    }
                    case 99:{
                        FindersAcDelete(scanner,statement);
                        break;
                    }
                    case 0:{
                        exit();
                        scanner.close();
                        return;
                    }
                    default:{
                        System.out.println("Invalid input..try again!!!");
                    }
                }

            }

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void registerWorker(Scanner scanner,Statement statement){
        System.out.println("Enter Your Name :");
        String workerName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Your Age :");
        int workerAge = scanner.nextInt();
        if(workerAge<18){
            System.out.println("You are Not Eligible!!!");
            System.exit(0);
        }
        scanner.nextLine();
        System.out.println("Enter Your Skill :");
        String workerSkill = scanner.nextLine();
        System.out.println("Enter Your Total Experience (In Years");
        int workerExp = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Your Phone Number :");
        String workerPhone = scanner.nextLine();
        String query = "INSERT INTO workers(worker_name, worker_age, skill, experience, worker_phone) VALUES ('"
                + workerName + "', "
                + workerAge + ", '"
                + workerSkill + "', '"
                + workerExp + "', '"
                + workerPhone + "')";
        try{
            int affectedRows = statement.executeUpdate(query);
            if(affectedRows >0){
                System.out.println("Your Worker Account Created Successfully..");
            }else {
                System.out.println("Failed to create the Account..Please try again!!!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    private static void registerFinder(Scanner scanner,Statement statement){
        System.out.println("Enter Your Name :");
        String finderName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Your Need :");
        String finderNeed = scanner.nextLine();
        System.out.println("Enter Your Phone Number :");
        String finderPhone = scanner.nextLine();
        String query = "INSERT INTO finders(finder_name, finder_need, finder_phone) VALUES ('"
                + finderName + "', '"
                + finderNeed + "', '"
                + finderPhone + "')";
        try{
            int affectedRows = statement.executeUpdate(query);
            if(affectedRows >0){
                System.out.println("Your Finder Account Created Successfully.");
            }else {
                System.out.println("Failed to create the Account..Please try again!!!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void viewWorkers(Statement statement){
        String query ="SELECT id,worker_name,worker_age,skill,experience,worker_phone FROM workers";
        try{
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("WORKER DETAILS");
            System.out.println("+-----------+---------------------+--------------+--------------------+--------------+-------------------+");
            System.out.println("| Worker ID |     Worker Name     |      Age     |        Skill       |   Experience |     Phone Number  |");
            System.out.println("+-----------+---------------------+--------------+--------------------+--------------+-------------------+");
            while (resultSet.next()){
                int workerId = resultSet.getInt("id");
                String workerName = resultSet.getString("worker_name");
                int workerAge = resultSet.getInt("worker_age");
                String workerSkill = resultSet.getString("skill");
                int workerExp = resultSet.getInt("experience");
                String workerPhone = resultSet.getString("worker_phone");
                System.out.printf("| %-9d | %-19s | %-12d | %-18s | %-12d | %-17s |\n",
                        workerId,workerName,workerAge,workerSkill,workerExp,workerPhone);

            }
            System.out.println("+-----------+---------------------+--------------+--------------------+--------------+-------------------+");


        }catch (SQLException e){
            e.printStackTrace();
        }
    } private static void viewFinders( Statement statement){
        String query ="SELECT id,finder_name,finder_need,finder_phone FROM finders";
        try{
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("FINDER DETAILS");
            System.out.println("+-----------+---------------------+------------------------+-------------------+");
            System.out.println("| Finder ID |     Finder Name     |      Finder's Need     |     Phone Number  |");
            System.out.println("+-----------+---------------------+------------------------+-------------------+");
            while (resultSet.next()){
                int finderId = resultSet.getInt("id");
                String finderName = resultSet.getString("finder_name");
                String finderNeed = resultSet.getString("finder_need");

                String finderPhone = resultSet.getString("finder_phone");
                System.out.printf("| %-9d | %-19s | %-22s | %-17s |\n",
                        finderId,finderName,finderNeed,finderPhone);

            }
            System.out.println("+-----------+---------------------+------------------------+-------------------+");


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private  static  void hireWorker(Scanner scanner,Statement statement){
        System.out.print("Enter Your FinderId :");
        int finderID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Your finder Name :");
        String finderName = scanner.nextLine();
        System.out.print("Enter The Worker Id :");
        int workerID= scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter The Worker Name :");
        String workerName = scanner.nextLine();
        if(!reserveFindersWorkers(statement,workerID,workerName,finderID,finderName)) {
            String query = "INSERT INTO workersAvailable(worker_id, name, Age, skill, experience, phone) " +
                    "SELECT id, worker_name, worker_age, skill, experience, worker_phone FROM workers";
            String query2 = "INSERT INTO findersAvailable(finder_id,name,phone)" + "SELECT id,finder_name,finder_phone FROM finders";

            try {
                int rowsAffected = statement.executeUpdate(query);
                if (rowsAffected > 0) {
                    System.out.println("You have hired Mr. " + workerName);

                } else {
                    System.out.println("Sorry Mr. " + workerName + " is not available right now..Please hire Someone Else.");
                    System.out.println("Thank You...");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
                int rowsAffected2 = statement.executeUpdate(query2);
                if(rowsAffected2>0){
                    System.out.println("Thank You Mr " + finderName + " For using this platform.");
                }else {
                    System.out.println("Sorry Mr. "+workerName+" is not available right now..Please try Anyone Else.");
                    System.out.println("Thank You...");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        } else {
            System.out.println("Sorry Mr. " + workerName + " is not available right now..Please hire Someone Else.");
            System.out.println("Thank You...");
        }



    }
    private  static  void hiredByFinders(Scanner scanner,Statement statement){
        System.out.print("Enter Your Worker ID :");
        int workerID= scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Your Worker Name :");
        String workerName = scanner.nextLine();
        System.out.print("Enter The Finder's ID :");
        int finderID= scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter The Finder's Name");
        String finderName = scanner.nextLine();
        if (!reserveFindersWorkers(statement,workerID,workerName,finderID,finderName)){
            String query = "INSERT INTO findersAvailable(finder_id,name,phone)" + "SELECT id,finder_name,finder_phone FROM finders";
            String query2 = "INSERT INTO workersAvailable(worker_id, name, Age, skill, experience, phone) " +
                    "SELECT id, worker_name, worker_age, skill, experience, worker_phone FROM workers";
            try{
                int rowsAffected = statement.executeUpdate(query);
                if(rowsAffected>0){
                    System.out.println("You are Hired by Mr. "+finderName+"...You can Contact Him.");
                }else {
                    System.out.println("Sorry Mr. "+finderName+" is not available right now..Please try Anyone Else.");
                    System.out.println("Thank You...");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                int rowsAffected2 = statement.executeUpdate(query2);
                if(rowsAffected2>0){
                    System.out.println("Thank You Mr " + workerName + " For using this platform.");
                }else {
                    System.out.println("Sorry Mr. "+finderName+" is not available right now..Please try Anyone Else.");
                    System.out.println("Thank You...");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("Sorry Mr. " + finderName + "  already hired anyone.Please try anyone Else.");
            System.out.println("Thank You...");
        }

    }

    private static void updateWorkerDetail(Scanner scanner, Statement statement) throws SQLException {
        System.out.print("Enter Your Current ID :");
        int reserveID = scanner.nextInt();
        scanner.nextLine();
        if (!workerreserveExists(statement,reserveID)){
            System.out.println("You are not registered...please register yourself");
        }else{
            System.out.println("Enter Your Name :");
            String wName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter Your Age :");
            int wAge = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Your Skill :");
            String wSkill = scanner.nextLine();
            System.out.println("Enter Your Total Experience (In Years");
            int wExp = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Your Phone Number :");
            String wPhone = scanner.nextLine();
            String query = "UPDATE workers SET worker_name = '" + wName +
                    "', worker_age = '" + wAge +
                    "', skill = '" + wSkill +
                    "', experience = " + wExp +  // wExp is an int, so no quotes
                    ", worker_phone = '" + wPhone +
                    "' WHERE id = " + reserveID;


            try {
                int rowsAffected = statement.executeUpdate(query);
                if(rowsAffected>0){
                    System.out.println("Details Updated Successfully!!!");
                }else {
                    System.out.println("Failed to Update the Details!!!");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    } private static void updateFinderDetail(Scanner scanner, Statement statement) throws SQLException {
        System.out.print("Enter Your Current ID :");
        int reserveID = scanner.nextInt();
        scanner.nextLine();

        if (!finderreserveExists(statement,reserveID)){
            System.out.println("You are not registered...please register yourself");
        }else{
            System.out.println("Enter Your Name :");
            String wName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter Your Age :");
            int wAge = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Your Phone Number :");
            String wPhone = scanner.nextLine();
            String query = "UPDATE finders SET finder_name = '" + wName +
                    "', finder_need = " + wAge +  // age is int, so no quotes
                    ", finder_phone = '" + wPhone +
                    "' WHERE id = " + reserveID;  // reserveID should also be an int

            try {
                int rowsAffected = statement.executeUpdate(query);
                if(rowsAffected>0){
                    System.out.println("Details Updated Successfully!!!");
                }else {
                    System.out.println("Failed to Update the Details!!!");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
    public static  boolean reserveFindersWorkers( Statement statement, int workerID,String workerName,int finderID,String finderName){
        try {
            String query = "SELECT * FROM workersAvailable WHERE worker_id = " + workerID + " AND name = '" + workerName + "'";
            String query2 = "SELECT * FROM findersAvailable WHERE finder_id = " + finderID + " AND name = '" + finderName + "'";
            try {
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet.next();
            } catch(SQLException e){
                e.printStackTrace();
            }
            try {
                ResultSet resultSet2 = statement.executeQuery(query2);
                return resultSet2.next();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static boolean workerreserveExists(Statement statement, int reserveID){
        try{
            String query = "SELECT id FROM workers WHERE id = "+reserveID;
            try {
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet.next();
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean finderreserveExists(Statement statement, int reserveID){
        try{
            String query = "SELECT finder_id FROM finders WHERE finder_id = "+reserveID;
            try {
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet.next();
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void workerworkDone(Scanner scanner,Statement statement){
        try {
            System.out.print("Enter Your Worker ID :");
            int workerID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Your Worker Name :");
            String workerName = scanner.nextLine();
            System.out.print("Enter the Finder's Id :");
            int finderID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the Finder's Name :");
            String finderName = scanner.nextLine();
            if(!reservefinderWorkers(statement,workerID,workerName,finderID,finderName)){
                System.out.println("You are not Hired!!!..please firsly get hired!!!");
            }else{
                String query = "DELETE FROM  workersavailable WHERE worker_id = "+workerID+" AND name = '" + workerName + "'";
                String query2 = "DELETE FROM  findersavailable WHERE finder_id = "+finderID+" AND name = '" + finderName + "'";

                int rowsAffected = statement.executeUpdate(query);
                if(rowsAffected>0){
                    System.out.println("Now You are again eligible for work");
                }else {
                    System.out.println("You are not hired..firstly get a work");
                }
                int rowsAffected2 = statement.executeUpdate(query2);
                if(rowsAffected2>0){
                    System.out.println("Thank You for using This Platform.");
                }else {
                    System.out.println("You are not hired..firstly get a work");
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void finderworkDone(Scanner scanner,Statement statement){
        try {
            System.out.print("Enter Your FinderId :");
            int finderID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Your Finder Name :");
            String finderName = scanner.nextLine();
            System.out.print("Enter the Worker's ID :");
            int workerID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the Worker's Name :");
            String workerName = scanner.nextLine();
            if(!reservefinderWorkers(statement,workerID,workerName,finderID,finderName)){
                System.out.println("You didn't hire anyone!!!..please firsly hire anyone!!!");
            }else{
                String query = "DELETE FROM  findersavailable WHERE finder_id = "+finderID+" AND name = '" + finderName + "'";
                String query2 = "DELETE FROM  workersavailable WHERE worker_id = "+workerID+" AND name = '" + workerName + "'";

                int rowsAffected = statement.executeUpdate(query);
                if(rowsAffected>0){
                    System.out.println("Now You can again hire");
                }else {
                    System.out.println("You  didn't hire someone..firstly hire someone");
                }
                int rowsAffected2 = statement.executeUpdate(query2);
                if(rowsAffected2>0){
                    System.out.println("Thank You for using This Platform.");
                }else {
                    System.out.println("You didn't hire anyone..firstly hire anyone!!!");
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i =5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("Thank You For Using Train Reservation System...");
    }


    public static  boolean reservefinderWorkers(Statement statement, int workerID,String workerName,int finderID,String finderName){
        try{
            String query = "SELECT * FROM workersAvailable WHERE worker_id = " + workerID + " AND name = '" + workerName + "'";
            String query2 = "SELECT * FROM findersAvailable WHERE finder_id = " + finderID + " AND name = '" + finderName + "'";

            try {
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet.next();
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                ResultSet resultSet2 = statement.executeQuery(query2);
                return resultSet2.next();
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private  static void WorkerAcDelete(Scanner scanner,Statement statement){
        System.out.print("Enter your Worker ID :");
        int workerID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Your Woker Name :");
        String workerName = scanner.nextLine();
        if (!deleteWorkerAc(statement,workerID,workerName)){
            String query = "DELETE FROM workers WHERE id = " + workerID + " AND worker_name = '" + workerName + "'";
            try{
                int rowsAffected = statement.executeUpdate(query);
                if(rowsAffected>0){
                    System.out.println("Your Account Deleted Successfully!!!");
                }else{
                    System.out.println("Sorry Something Wrong...AC. Deletion Failed!!!");
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
    private  static void FindersAcDelete(Scanner scanner,Statement statement){
        System.out.print("Enter your Finder ID :");
        int finderID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Your Finder Name :");
        String finderName = scanner.nextLine();
        if (!deleteFinderAc(statement,finderID,finderName)){
            String query = "DELETE FROM finders WHERE id = " + finderID + " AND finder_name = '" + finderName + "'";
            try{
                int rowsAffected = statement.executeUpdate(query);
                if(rowsAffected>0){
                    System.out.println("Your Account Deleted Successfully!!!");
                }else{
                    System.out.println("Sorry Something Wrong...AC. Deletion Failed!!!");
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
    private static boolean deleteWorkerAc(Statement statement,int workerID,String workerName){
        try {
            String query = "SELECT * FROM workers WHERE id = " + workerID + " AND worker_name = '" + workerName + "'";
            try {
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    return resultSet.next();
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private static boolean deleteFinderAc(Statement statement,int finderID,String finderName){
        try {
            String query = "SELECT * FROM finders WHERE id = " + finderID + " AND finder_name = '" + finderName + "'";
            try {
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    return resultSet.next();
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
