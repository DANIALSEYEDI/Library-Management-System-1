# Library Management System (Exercise 2)

This project is a library management system designed to manage books, theses, users (students and professors), and library-related processes. It supports functionalities such as registering libraries, categorizing resources, managing users, searching, borrowing, returning, reserving study desks, and generating reports. The system is implemented in Java without using inheritance.

## Features
- **Library Management**: Register and manage library information.
- **Resource Management**: Register and categorize books and theses.
- **User Management**: Register and manage student and professor information.
- **Search**: Search for resources (books or theses) by title or author/student/professor name.
- **Borrowing and Returning**: Record borrowing and returning of resources.
- **Desk Reservation**: Reserve study desks in the library with a minimum duration of 2 hours.
- **Reporting**: Generate reports for overdue resources and user penalties.

## Prerequisites
- **Java**: Version 8 or higher
- **Tools**:
  - JDK (Java Development Kit)
  - Maven or Gradle for dependency management
  - Development environment (e.g., IntelliJ IDEA or Eclipse)

## System Commands
### Commands for Professors and Administrators
- **Register Library**: Add a new library to the system.
  ```
  add-library#L001|LibraryName|Address
  ```
- **Register Category**: Create a topical category for resources.
  ```
  add-category#C001|CategoryName
  ```
- **Register User**: Register a student or professor with specific details.
  ```
  add-user#11037845|MyPass|Name|Role
  ```
- **Register Book or Thesis**: Add a book or thesis to the library resources.
  ```
  add-book#B001|Title|Author
  add-thesis#T001|Title|Student|Supervisor
  ```

### Commands for Library Processes
- **Search Resources**: Search for books or theses by keyword.
  ```
  search#Programming
  ```
  Output: `B001|B102` or `not-found`
- **Search User**: Search users by name or surname.
  ```
  search-user#11037845|MyPass|Test
  ```
- **Borrow Resource**: Record the borrowing of a resource by a user.
  ```
  borrow#11037845|MyPass|L001|B001|2023-04-15|18:30
  ```
  Output: `success`, `not-found`, `invalid-pass`, or `fined` (if penalized)
- **Return Resource**: Record the return of a borrowed resource.
  ```
  return#11037845|MyPass|L001|B001
  ```
- **Reserve Desk**: Reserve a study desk in the library.
  ```
  reserve-seat#11037845|MyPass|L001|2023-04-15|18:30
  ```
- **Reporting**: Generate reports for overdue resources or total user penalties.
  ```
  report-passed-deadline#L001|2023-04-23|15:25
  report-penalties-sum#11037845
  ```

## Important Notes
- **Submission**: Exercises must be completed individually and submitted via the Koura platform. Submissions through other means will not be accepted.
- **Code Review**: Code will be evaluated both automatically and manually.
- **Submission Deadlines**:
  - By **Friday, 12 Esfand 1402 (March 2, 2024)** without penalty.
  - By **Sunday, 19 Esfand 1402 (March 9, 2024)** with a penalty.
- **Documentation**: JavaDoc must be provided for sections requiring documentation.
- **Plagiarism**: Any form of cheating will result in a negative score for both the cheater and the person providing the cheated material. Repeated offenses will be reviewed by the course instructors.
- **Implementation Note**: The system is implemented in Java without using inheritance.

## Support
For any questions or clarifications, contact the teaching assistants via the course Telegram group.