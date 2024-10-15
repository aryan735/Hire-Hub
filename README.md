

# HIRE HUB (Worker-Finder Management System)

## Project Overview

This **Worker-Finder Management System** allows individuals (referred to as *Finders*) to hire workers for tasks, and workers can be hired through this platform. The system supports functionalities for both workers and finders, including registering, updating details, hiring workers, and marking the work done. Additionally, both parties can delete their accounts from the system.

## Features

### 1. **Hire Worker**
   - A Finder can hire a worker by entering both their own details and the worker's details. If the worker is available, they are added to the "workersAvailable" list.

### 2. **Hired by Finder**
   - Workers can check if they've been hired by entering their and the finder's details.

### 3. **Update Worker/Finder Details**
   - Both workers and finders can update their personal details, such as name, age, skill (for workers), and phone number.

### 4. **Mark Work as Done**
   - Once the task is completed, workers and finders can mark the work as done, which removes them from the available list.

### 5. **Delete Accounts**
   - Both workers and finders can delete their accounts from the system.

## Database Structure

The project uses a relational database with the following key tables:

1. **Workers Table (`workers`)**
   - Stores details about the workers including ID, name, age, skills, experience, and phone number.
   
2. **Finders Table (`finders`)**
   - Stores details about the finders, including ID, name, and phone number.

3. **Workers Available (`workersAvailable`)**
   - Stores workers who are currently available for hire.

4. **Finders Available (`findersAvailable`)**
   - Stores finders who are looking to hire workers.

## SQL Queries

Some of the SQL queries used in this project include:

- **Inserting a worker or finder into the available list:**
    ```sql
    INSERT INTO workersAvailable (worker_id, name, age, skill, experience, phone)
    SELECT id, worker_name, worker_age, skill, experience, worker_phone FROM workers
    ```
    ```sql
    INSERT INTO findersAvailable (finder_id, name, phone)
    SELECT id, finder_name, finder_phone FROM finders
    ```

- **Updating worker details:**
    ```sql
    UPDATE workers 
    SET worker_name = 'John', worker_age = 30, skill = 'Plumbing', experience = 5, worker_phone = '1234567890' 
    WHERE id = 1;
    ```

- **Deleting a worker/finder account:**
    ```sql
    DELETE FROM workers WHERE id = 1 AND worker_name = 'John';
    ```

## Functions Overview

### `hireWorker(Scanner scanner, Statement statement)`
Allows a finder to hire a worker by entering the necessary details for both parties.

### `hiredByFinders(Scanner scanner, Statement statement)`
Checks if a worker has been hired by a finder, using both worker and finder details.

### `updateWorkerDetail(Scanner scanner, Statement statement)`
Updates the details of an existing worker in the system.

### `updateFinderDetail(Scanner scanner, Statement statement)`
Updates the details of an existing finder in the system.

### `workerworkDone(Scanner scanner, Statement statement)`
Marks a worker as available again once the work is done, removing them from the available workers list.

### `finderworkDone(Scanner scanner, Statement statement)`
Marks the work as done from the finder's side, allowing them to hire again.

### `WorkerAcDelete(Scanner scanner, Statement statement)`
Deletes the account of a worker from the system.

### `FindersAcDelete(Scanner scanner, Statement statement)`
Deletes the account of a finder from the system.

### Helper Functions
- `reserveFindersWorkers()`: Checks if the worker and finder details exist in the available lists.
- `workerreserveExists()`: Checks if a worker is already registered in the system.
- `finderreserveExists()`: Checks if a finder is already registered in the system.

## Prerequisites

- **Java**: Ensure that Java is installed.
- **MySQL or any SQL Database**: Set up a database with the required tables as specified above.

## How to Run

1. **Clone the repository:**
    ```bash
    git clone https://github.com/<your-username>/WorkerFinderManagementSystem.git
    ```
2. **Set up your database:**
   - Create the required tables in your database as mentioned in the project.
   - Modify the connection details in the `main()` function to point to your database.

3. **Compile the program:**
    ```bash
    javac WorkerFinderManagementSystem.java
    ```

4. **Run the program:**
    ```bash
    java WorkerFinderManagementSystem
    ```



