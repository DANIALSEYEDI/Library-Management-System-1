
# 📘 Basic Library Management System – Exercise 2

This project is a Java-based **Library Management System**, created as part of **Exercise 2** in the Advanced Programming course.  
Unlike later versions, this system **does not use inheritance** and focuses solely on direct object-oriented design without polymorphic structures.

>  Focus: Basic Object-Oriented Programming (OOP), Class Design, Command Parsing

---

## ✅ Features

- Manage students, staff, professors, categories, and libraries
- Add / Edit / Remove books and theses
- Simple borrowing, returning, and penalty tracking system
- Command-based interactions with clear input formats
- No use of inheritance — flat class design

---

## 🧱 Structure Overview

- `Student`
- `Staff`
- `Professor`
- `Book`
- `Thesis`
- `Library`
- `Category`

All classes are implemented independently with **no inheritance** to emphasize the fundamentals of OOP and class encapsulation.

---

## ⚙️ Installation

```bash
# Java required
java -version

# Compile and run
javac Main.java
java Main
```

---

## 📥 Sample Commands

### ➕ Add Library
```
add-library#L001|Central Library|1358|38|Aut main campus
```

### ➕ Add Student
```
add-student#14010011234|PassWd|Test|Testi|0123456789|1382|Tehran, Mirdamad
```

### ➕ Add Book
```
add-book#B001|Book1|Somebody|Springer|2020|3|C001|L001
```

### 🔁 Borrow a Resource
```
borrow#14010011234|PassWd|L001|B001|2025-04-10|10:00
```

---

## 🚨 Limitations

- No class inheritance or polymorphism used
- Business rules such as max borrow count and due dates enforced manually
- Flat hierarchy can result in repetitive code

---

## 📬 Output Messages

- `success`  – Operation completed
- `duplicate-id`  – ID already exists
- `not-found`  – Item or user not found
- `invalid-pass`  – Incorrect password
- `not-allowed`  – Rule violation (e.g. overdue, duplicate borrow)

---
**© 2025 - Advanced Programming – Dr. Kalbasi & Dr. Zeynali**
