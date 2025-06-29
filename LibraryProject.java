import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author Danial Seyedi
 * @version 1.0
 */

public class LibraryProject {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Library> libraries = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Thesis> theses = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Staff> staffs = new ArrayList<>();
    ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();
    ArrayList<Table> tables = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        LibraryProject lib = new LibraryProject();
        while (true) {
            input = sc.nextLine();
            String[] tokens = input.split("[#|]");
            if (tokens[0].equals("add-library")) {
                lib.addLibrary(input);
            } else if (tokens[0].equals("add-category")) {
                lib.addCategory(input);
            } else if (tokens[0].equals("add-book")) {
                 lib.addBook(input);
            } else if (tokens[0].equals("edit-book")) {
                lib.editBook(input);
            } else if (tokens[0].equals("remove-book")) {
                 lib.removeBook(input);
            } else if (tokens[0].equals("add-thesis")) {
                lib.addThesis(input);
            } else if (tokens[0].equals("edit-thesis")) {
                lib.editThesis(input);
            } else if (tokens[0].equals("remove-thesis")) {
                 lib.removeThesis(input);
            } else if (tokens[0].equals("add-student")) {
                lib.addStudent(input);
            } else if (tokens[0].equals("edit-student")) {
                 lib.editStudent(input);
            } else if (tokens[0].equals("remove-student")) {
               lib.removeStudent(input);
            } else if (tokens[0].equals("add-staff")) {
                 lib.addStaff(input);
            } else if (tokens[0].equals("edit-staff")) {
                 lib.editStaff(input);
            } else if (tokens[0].equals("remove-staff")) {
                 lib.removeStaff(input);
            } else if (tokens[0].equals("borrow")) {
                 lib.borrow(input);
            } else if (tokens[0].equals("return")) {
                lib.returnBook(input);
            } else if (tokens[0].equals("search-user")) {
                lib.searchUser(input);
            } else if (tokens[0].equals("search")) {
                lib.search(input);
            } else if (tokens[0].equals("category-report")) {
                lib.categoryReport(input);
            } else if (tokens[0].equals("library-report")) {
                lib.libraryReport(input);
            } else if (tokens[0].equals("report-passed-deadline")) {
                lib.reportPassedDeadline(input);
            } else if (tokens[0].equals("report-penalties-sum")) {
                lib.reportPenaltiesSum();
            }
            else if(tokens[0].equals("reserve-seat")) {
                lib.reserveSeat(input);
            }
            else if (tokens[0].equals("finish")) {
                System.out.println();
                return;
            }
        }
    }

    /**
     * @author Danial Seyedi
     * @param input its command that we need to add library
     */
    public void addLibrary(String input) {
        String[] tokens = input.split("[#|]");
        for (Library lib : libraries) {
            if (tokens[1].equals(lib.getId())) {
                System.out.println("duplicate-id");
                return;
            }
        }
        Library newlibrary = new Library(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
        libraries.add(newlibrary);
        System.out.println("success");
    }

    public void addCategory(String input) {
        String[] tokens = input.split("[#|]");
        for (Category cat : categories) {
            if (tokens[1].equals(cat.getId())) {
                System.out.println("duplicate-id");
                return;
            }
        }
        Category newcategory = new Category(tokens[1], tokens[2]);
        categories.add(newcategory);
        System.out.println("success");
    }

    public void addBook(String input) {
        String[] tokens = input.split("[#|]");
        boolean libraryExists = false;
        for (Library lib : libraries) {
            if (tokens[8].equals(lib.getId())) {
                libraryExists = true;
                break;
            }
        }
        if (!libraryExists) {
            System.out.println("not-found");
            return;
        }
        String category = tokens[7];
        if (!category.equals("null")) {
            boolean categoryExists = false;
            for (Category cat : categories) {
                if (cat.getId().equals(category)) {
                    categoryExists = true;
                    break;
                }
            }
            if (!categoryExists){
                System.out.println("not-found");
                return;
            }
        }
        for (Book book : books) {
            if (tokens[1].equals(book.getId()) && tokens[8].equals(book.getLibraryid())) {
                System.out.println("duplicate-id");
                return;
            }
        }
        Book newBook = new Book(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]);
        books.add(newBook);
        System.out.println("success");
    }

    public void editBook(String input) {
        String[] tokens = input.split("[#|]");
        for (Book book : books) {
            if (book.getId().equals(tokens[1]) && book.getLibraryid().equals(tokens[2])) {
                if (!tokens[3].equals("-")) {
                    book.setName(tokens[3]);
                }
                if (!tokens[4].equals("-")) {
                    book.setAuthor(tokens[4]);
                }
                if (!tokens[5].equals("-")) {
                    book.setPublisher(tokens[5]);
                }
                if (!tokens[6].equals("-")) {
                    book.setYear(tokens[6]);
                }
                if (!tokens[7].equals("-")) {
                    book.setCopies(tokens[7]);
                }
                if (!tokens[8].equals("-")) {
                    if (tokens[8].equals("null")) {
                        book.setCategory("null");
                    }
                    for (Category cat : categories) {
                        if (cat.getId().equals(tokens[8])) {
                            book.setCategory(tokens[8]);
                            break;
                        }
                    }
                    if (!tokens[8].equals(book.getCategory()) && !tokens[8].equals("null")) {
                        System.out.println("not-found");
                        return;
                    }
                }
                System.out.println("success");
                return;
            }
        }
        System.out.println("not-found");
    }

    public void removeBook(String input) {
        String[] tokens = input.split("[#|]");
        boolean libraryExists = false;
        boolean bookExists = false;
        for (Library lib : libraries) {
            if (tokens[2].equals(lib.getId())) {
                libraryExists = true;
                break;
            }
        }
        for (Book book : books) {
            if (tokens[1].equals(book.getId()) && tokens[2].equals(book.getLibraryid())) {
                bookExists = true;
                break;
            }
        }
        if (!libraryExists) {
            System.out.println("not-found");
            return;
        }
        if (!bookExists) {
            System.out.println("not-found");
            return;
        }
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(tokens[1]) && book.getLibraryid().equals(tokens[2])) {
                for(BorrowRecord borrow: borrowRecords) {
                    if(borrow.getBookid().equals(book.getId()) && borrow.getLibraryname().equals(book.getLibraryid())) {
                        System.out.println("not-allowed");
                        return;
                    }
                }
                iterator.remove();
                System.out.println("success");
                return;
            }
        }
    }

    public void addThesis(String input) {
        String[] tokens = input.split("[#|]");
        boolean libraryExists = false;
        for (Library lib : libraries) {
            if (tokens[7].equals(lib.getId())) {
                libraryExists = true;
                break;
            }
        }
        if (!libraryExists) {
            System.out.println("not-found");
            return;
        }
        String category = tokens[6];
        if (!category.equals("null")) {
            boolean categoryExists = false;
            for (Category cat : categories) {
                if (cat.getId().equals(category)) {
                    categoryExists = true;
                    break;
                }
            }
            if (!categoryExists){
                System.out.println("not-found");
                return;
            }
        }
        for (Thesis th : theses) {
            if (tokens[1].equals(th.getId()) && tokens[7].equals(th.getLibraryname())) {
                System.out.println("duplicate-id");
                return;
            }
        }
        Thesis newthesis = new Thesis(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
        theses.add(newthesis);
        System.out.println("success");
    }

    public void editThesis(String input) {
        String[] tokens = input.split("[#|]");
        for (Thesis th : theses) {
            if (th.getId().equals(tokens[1]) && th.getLibraryname().equals(tokens[2])) {
                if (!tokens[3].equals("-")) {
                    th.setName(tokens[3]);
                }
                if (!tokens[4].equals("-")) {
                    th.setStudentname(tokens[4]);
                }
                if (!tokens[5].equals("-")) {
                    th.setProfessorname(tokens[5]);
                }
                if (!tokens[6].equals("-")) {
                    th.setYear(tokens[6]);
                }
                if (!tokens[7].equals("-")) {
                    if (tokens[7].equals("null")) {
                        th.setCategory("null");
                    }
                    for (Category cat : categories) {
                        if (cat.getId().equals(tokens[7])) {
                            th.setCategory(tokens[7]);
                        }
                    }
                    if (!tokens[7].equals(th.getCategory()) && !tokens[7].equals("null")) {
                        System.out.println("not-found");
                        return;
                    }
                }
                System.out.println("success");
                return;
            }
        }
        System.out.println("not-found");
    }

    public void removeThesis(String input) {
        String[] tokens = input.split("[#|]");
        boolean libraryExists = false;
        boolean thesisExists = false;
        for(Library lib: libraries) {
            if (tokens[2].equals(lib.getId())) {
                libraryExists = true;
                break;
            }
        }
        for (Thesis th : theses) {
            if (tokens[1].equals(th.getId()) && tokens[2].equals(th.getLibraryname())) {
                thesisExists = true;
                break;
            }
        }
        if(!libraryExists) {
            System.out.println("not-found");
            return;
        }
        if (!thesisExists) {
            System.out.println("not-found");
            return;
        }
        Iterator<Thesis> iterator = theses.iterator();
        while (iterator.hasNext()) {
            Thesis th = iterator.next();
            if (th.getId().equals(tokens[1]) && th.getLibraryname().equals(tokens[2])){
                for(BorrowRecord borrow : borrowRecords) {
                    if(borrow.getBookid().equals(tokens[1]) && borrow.getLibraryname().equals(tokens[2])) {
                        System.out.println("not-allowed");
                        return;
                    }
                }
                iterator.remove();
                System.out.println("success");
                return;
            }
        }
    }

    public void addStudent(String input) {
        String[] tokens=input.split("[#|]");
        for(Student student : students){
            if(tokens[1].equals(student.getId())){
                System.out.println("duplicate-id");
                return;
            }
        }
        Student newstudent = new Student(tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7]);
        students.add(newstudent);
        System.out.println("success");
    }

    public void editStudent(String input) {
        String[] tokens = input.split("[#|]");
        for(Student student : students) {
            if(student.getId().equals(tokens[1])) {
                if(!tokens[2].equals("-")){
                    student.setPassword(tokens[2]);
                    for(BorrowRecord borrow : borrowRecords) {
                        if(borrow.getIdstudent().equals(student.getId())) {
                            borrow.setPassword(tokens[2]);
                        }
                    }
                }
                if(!tokens[3].equals("-")){
                    student.setName(tokens[3]);
                }
                if(!tokens[4].equals("-")){
                    student.setLastname(tokens[4]);
                }
                if(!tokens[5].equals("-")){
                    student.setCodemeli(tokens[5]);
                }
                if(!tokens[6].equals("-")){
                    student.setYears(tokens[6]);
                }
                if(!tokens[7].equals("-")){
                    student.setAddress(tokens[7]);
                }
                System.out.println("success");
                return;
            }
        }
        System.out.println("not-found");
    }

    public void removeStudent(String input) {
        String[] tokens = input.split("[#|]");
        boolean studentExists = false;
        for(Student student : students) {
            if(tokens[1].equals(student.getId())) {
                studentExists = true;
                break;
            }
        }
        if(!studentExists) {
            System.out.println("not-found");
            return;
        }
        Iterator<Student> iterator = students.iterator();
       while (iterator.hasNext()) {
           Student student = iterator.next();
            if(student.getId().equals(tokens[1])) {
                if(student.getBorrowtime()==0 && student.getPenalties()==0){
                    iterator.remove();
                    System.out.println("success");
                    return;
                }
                else{
                    System.out.println("not-allowed");
                    return;
                }
            }
        }
    }

    public void addStaff(String input) {
        String[] tokens=input.split("[#|]");
        for(Staff staff : staffs){
            if(tokens[1].equals(staff.getId())){
                System.out.println("duplicate-id");
                return;
            }
        }
        Staff newstaff=new Staff(tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7]);
        staffs.add(newstaff);
        System.out.println("success");
    }

    public void editStaff(String input) {
        String[] tokens = input.split("[#|]");
        for(Staff staff : staffs) {
            if(staff.getId().equals(tokens[1])) {
                if(!tokens[2].equals("-")){
                    staff.setPassword(tokens[2]);
                    for (BorrowRecord borrow : borrowRecords) {
                        if(borrow.getIdstudent().equals(staff.getId())) {
                            borrow.setPassword(tokens[2]);
                        }
                    }
                }
                if(!tokens[3].equals("-")){
                    staff.setName(tokens[3]);
                }
                if(!tokens[4].equals("-")){
                    staff.setLastname(tokens[4]);
                }
                if(!tokens[5].equals("-")){
                    staff.setCodemeli(tokens[5]);
                }
                if(!tokens[6].equals("-")){
                    staff.setYears(tokens[6]);
                }
                if(!tokens[7].equals("-")){
                    staff.setAddress(tokens[7]);
                }
                System.out.println("success");
                return;
            }
        }
        System.out.println("not-found");
    }

    public void removeStaff(String input) {
        String[] tokens = input.split("[#|]");
        boolean staffExists = false;
        for(Staff staff : staffs) {
            if(tokens[1].equals(staff.getId())) {
                staffExists = true;
                break;
            }
        }
        if(!staffExists) {
            System.out.println("not-found");
            return;
        }
        Iterator<Staff> iterator = staffs.iterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if(staff.getId().equals(tokens[1])) {
                if(staff.getBorrowtime()==0 && staff.getPenalties()==0){
                  iterator.remove();
                    System.out.println("success");
                    return;
                }
                else{
                    System.out.println("not-allowed");
                    return;
                }
            }
        }
    }



    public void borrow(String input) {
        String[] tokens = input.split("[#|]");
        if(tokens.length<7){
            System.out.println("not-found");
            return;
        }
        boolean borrowedstudent=false;
        String borrowedstudentid="";
        for(Student student : students){
            if(student.getId().equals(tokens[1])){
                borrowedstudent=true;
                borrowedstudentid=student.getId();
            }
        }
        boolean borrowedstaff=false;
        String borrowedstaffid="";
        for(Staff staff : staffs){
            if(staff.getId().equals(tokens[1])){
                borrowedstaff=true;
                borrowedstaffid=staff.getId();
            }
        }
        if((!borrowedstudent) && (!borrowedstaff)){
            System.out.println("not-found");
            return;
        }
        if(borrowedstudent){
            for(Student student : students){
                if(student.getId().equals(borrowedstudentid)) {
                    if (!student.getPassword().equals(tokens[2])) {
                        System.out.println("invalid-pass");
                        return;
                    }
                    break;

                }
            }
        }
        else if(borrowedstaff){
            for(Staff staff : staffs){
                if(staff.getId().equals(borrowedstaffid)){
                    if(!staff.getPassword().equals(tokens[2])){
                        System.out.println("invalid-pass");
                        return;
                    }
                    break;
                }
            }
        }
        boolean borrowedbook=false;
        String borrowedbookid="";
        boolean borroweditem=false;
        for(Book book : books){
            if(tokens[4].equals(book.getId())){
                borrowedbook=true;
                borrowedbookid=book.getId();
                borroweditem=true;
            }
        }
        boolean borrowedthesis=false;
        String borrowedthesisid="";
        for(Thesis thesis : theses){
            if(thesis.getId().equals(tokens[4])){
                borrowedthesis=true;
                borrowedthesisid=thesis.getId();
                borroweditem=true;
            }
        }
        if(!borroweditem){
            System.out.println("not-found");
            return;
        }

        boolean borrowlibrary=false;
        String borrowlibraryid="";
        for(Library library: libraries){
            if(library.getId().equals(tokens[3])){
                borrowlibrary=true;
                borrowlibraryid=library.getId();
            }
        }
        if(!borrowlibrary){
            System.out.println("not-found");
            return;
        }
        if(borrowedstudent){
            for(Student student : students){
                if(student.getId().equals(borrowedstudentid)){
                    if(student.getBorrowtime()>=3){
                        System.out.println("not-allowed");
                        return;
                    }
                    break;
                }
            }
        }
        else if(borrowedstaff){
            for(Staff staff : staffs){
                if(staff.getId().equals(borrowedstaffid)){
                    if(staff.getBorrowtime()>=5){
                        System.out.println("not-allowed");
                        return;
                    }
                }
            }
        }

        if(borrowedbook){
            for(Book book : books){
                if(book.getId().equals(borrowedbookid) && book.getLibraryid().equals(borrowlibraryid)){
                    if(Integer.parseInt(book.getCopies())<=0){
                        System.out.println("not-allowed");
                        return;

                    }
                    else {
                        int x=Integer.parseInt(book.getCopies())-1;
                        book.setCopies(String.valueOf(x));
                    }
                }
            }
        }
        if(borrowedthesis){
            for(Thesis thesis : theses){
                if(thesis.getId().equals(borrowedthesisid) && thesis.getLibraryname().equals(borrowlibraryid)){
                    if(Integer.parseInt(thesis.getCopies())<=0){
                        System.out.println("not-allowed");
                        return;
                    }
                    else {
                        int x=Integer.parseInt(thesis.getCopies())-1;
                        thesis.setCopies(String.valueOf(x));
                    }
                }
            }
        }
        if(borrowedstudent){
            for(Student student : students){
                if(student.getId().equals(borrowedstudentid)){
                    student.setBorrowtime(student.getBorrowtime()+1);
                }
            }
        }
        if(borrowedstaff){
          for(Staff staff : staffs){
              if(staff.getId().equals(borrowedstaffid)){
                  staff.setBorrowtime(staff.getBorrowtime()+1);
              }
          }
        }
        BorrowRecord newborrowRecord= new BorrowRecord(tokens[1],tokens[2],tokens[3],tokens[4],tokens[5].trim(),tokens[6].trim());
        borrowRecords.add(newborrowRecord);
        System.out.println("success");
    }



    public void returnBook(String input) {
        String[] tokens = input.split("[#|]");
        if(tokens.length<7){
            System.out.println("not-found");
            return;
        }
        boolean borroweditmeuser=false;
        boolean borrowedstudent=false;
        String borrowedstudentid="";
        boolean borrowedstaff=false;
        String borrowedstaffid="";
        for(Student student : students) {
            if(student.getId().equals(tokens[1])){
                borrowedstudent=true;
                borrowedstudentid=student.getId();
                borroweditmeuser=true;
            }
        }
        for(Staff staff : staffs) {
            if(staff.getId().equals(tokens[1])){
                borrowedstaff=true;
                borrowedstaffid=staff.getId();
                borroweditmeuser=true;
            }
        }
        if(!borroweditmeuser){
            System.out.println("not-found");
            return;
        }
        if(borrowedstudent){
            for(Student student : students) {
                if(student.getId().equals(borrowedstudentid)){
                    if(!student.getPassword().equals(tokens[2])){
                        System.out.println("invalid-pass");
                        return;
                    }
                }
            }
        }
        if(borrowedstaff){
            for(Staff staff : staffs) {
                if(staff.getId().equals(borrowedstaffid)){
                    if(!staff.getPassword().equals(tokens[2])){
                        System.out.println("invalid-pass");
                        return;
                    }
                }
            }
        }
        boolean borrowedlibrary=false;
        String borrowedLibraryid="";
        for(Library library : libraries){
            if(library.getId().equals(tokens[3])){
               borrowedlibrary=true;
               borrowedLibraryid=tokens[3];
               break;
            }
        }
        if(!borrowedlibrary){
            System.out.println("not-found");
            return;
        }
        boolean borrowedbook=false;
        boolean borrowedthesis=false;
        boolean borroweditem=false;
        String borrowedthesisid="";
        String borrowedbookid="";
        for(Book book : books) {
            if(book.getId().equals(tokens[4])){
                borrowedbook=true;
                borroweditem=true;
                borrowedbookid=book.getId();
            }
        }
        for(Thesis thesis : theses) {
            if(thesis.getId().equals(tokens[4])){
                borrowedthesis=true;
                borrowedthesisid=thesis.getId();
                borroweditem=true;
            }
        }
        if(!borroweditem){
            System.out.println("not-found");
            return;
        }
        ArrayList<BorrowRecord> records = new ArrayList<>();
        boolean isborrowed=false;
        for(BorrowRecord borrowRecord : borrowRecords) {
            if(borrowRecord.getIdstudent().equals(borrowedstudentid) || borrowRecord.getIdstudent().equals(borrowedstaffid)){
                if(borrowRecord.getBookid().equals(borrowedbookid)||borrowRecord.getBookid().equals(borrowedthesisid)){
                    if(borrowRecord.getLibraryname().equals(borrowedLibraryid)){
                        isborrowed=true;
                        records.add(borrowRecord);
                    }
                }
            }
        }
        if(!isborrowed){
            System.out.println("not-found");
            return;
        }
        ArrayList<Long> timeslimit = new ArrayList<>();
        LocalDateTime now=LocalDateTime.parse(tokens[5].trim()+"T"+tokens[6].trim());
        for(BorrowRecord borrowRecord : records) {
            LocalDateTime past=LocalDateTime.parse(borrowRecord.getDate().trim()+"T"+borrowRecord.getTime().trim());
            Long time2=ChronoUnit.MINUTES.between(past,now);
            timeslimit.add(time2);
        }
        long min=Collections.min(timeslimit);
        min=min/60;
        String iduser="";
        String idbook="";
        boolean success=true;
        if (borrowedstudent){
            if (borrowedbook){
                for(Book book : books) {
                    if(book.getId().equals(borrowedbookid)){
                        int x=Integer.parseInt(book.getCopies())+1;
                        book.setCopies(String.valueOf(x));
                        idbook=book.getId();
                    }
                }
                if(min>10*24){
                    long penalties=(min-10*24)*50;
                    System.out.println(penalties);
                    for(Student student : students) {
                        if(student.getId().equals(borrowedstudentid)){
                            student.setPenalties((int)penalties+student.getPenalties());
                            iduser=student.getId();
                        }
                    }
                    success=false;
                }
                    for(Student student : students) {
                        if(student.getId().equals(borrowedstudentid)){
                            student.setBorrowtime(student.getBorrowtime()-1);
                        }
                    }

            }

            if (borrowedthesis){
                if(min>7*24){
                    long penalties=(min-7*24)*50;
                    System.out.println(penalties);
                    for(Student student : students) {
                        if(student.getId().equals(borrowedstudentid)){
                            student.setPenalties((int)penalties+student.getPenalties());
                            iduser=student.getId();
                        }
                    }
                    success=false;
                }
                    for(Student student : students) {
                        if(student.getId().equals(borrowedstudentid)){
                            student.setBorrowtime(student.getBorrowtime()-1);
                            iduser=student.getId();
                        }
                    }


                    for(Thesis thesis : theses) {
                        if(thesis.getId().equals(borrowedthesisid)){
                            int x=Integer.parseInt(thesis.getCopies())+1;
                            thesis.setCopies(String.valueOf(x));
                            idbook=thesis.getId();
                        }
                    }
            }
        }
        if(borrowedstaff){
            if (borrowedbook) {
                if (min > 14 * 24) {
                    long penalties = (min - 14 * 24) * 100;
                    System.out.println(penalties);
                    for (Staff staff : staffs) {
                        if (staff.getId().equals(borrowedstaffid)) {
                            staff.setPenalties((int) penalties + staff.getPenalties());
                            iduser = staff.getId();
                        }
                    }
                    success = false;
                }
                for (Staff staff : staffs) {
                    if (staff.getId().equals(borrowedstaffid)) {
                        staff.setBorrowtime(staff.getBorrowtime() - 1);
                        iduser = staff.getId();
                    }
                }

                for (Book book : books) {
                    if (book.getId().equals(borrowedbookid)) {
                        int x = Integer.parseInt(book.getCopies()) + 1;
                        book.setCopies(String.valueOf(x));
                        idbook = book.getId();
                    }
                }
            }

            if (borrowedthesis){
                if(min>10*24){
                    long penalties=(min-10*24)*100;
                    System.out.println(penalties);
                    for(Staff staff : staffs) {
                        if(staff.getId().equals(borrowedstaffid)){
                            staff.setPenalties((int)penalties+staff.getPenalties());
                            iduser=staff.getId();
                        }
                    }
                    success=false;
                }

                    for (Staff staff : staffs) {
                        if (staff.getId().equals(borrowedstaffid)) {
                            staff.setBorrowtime(staff.getBorrowtime() - 1);
                            iduser = staff.getId();
                        }
                    }
                    for(Thesis thesis : theses) {
                        if(thesis.getId().equals(borrowedthesisid)){
                            int x= Integer.parseInt(thesis.getCopies())+1;
                            thesis.setCopies(String.valueOf(x));
                            idbook=thesis.getId();
                        }
                    }
            }
        }
        Iterator<BorrowRecord> iterator = borrowRecords.iterator();
        while(iterator.hasNext()) {
            BorrowRecord borrowRecord = iterator.next();
            LocalDateTime times3=LocalDateTime.parse(borrowRecord.getDate()+"T"+borrowRecord.getTime());
            long time4= ChronoUnit.HOURS.between(times3,now);
            if(min==time4 && borrowRecord.getBookid().equals(idbook) && borrowRecord.getIdstudent().equals(iduser)){
                iterator.remove();
                break;
            }
        }
        if(success){
            System.out.println("success");
        }
        }



    public void search(String input) {
        String[] tokens = input.split("[#|]");
        String search=tokens[1];
        search=search.toLowerCase();
        HashSet<String> found=new HashSet<>();
        for(Book book: books){
            if(book.getId().toLowerCase().contains(search) ||book.getName().toLowerCase().contains(search)||
                    book.getAuthor().toLowerCase().contains(search) || book.getPublisher().toLowerCase().contains(search) || book.getCategory().toLowerCase().contains(search) || book.getLibraryid().toLowerCase().contains(search) ) {
                found.add(book.getId());
            }
        }

        for(Thesis thesis: theses) {
            if (thesis.getId().toLowerCase().contains(search) || thesis.getName().toLowerCase().contains(search) || thesis.getStudentname().toLowerCase().contains(search) || thesis.getProfessorname().toLowerCase().contains(search)
            || thesis.getLibraryname().toLowerCase().contains(search) || thesis.getCategory().toLowerCase().contains(search) ) {
                found.add(thesis.getId());
            }
        }

        if(found.isEmpty()){
            System.out.println("not-found");
            return;
        }
        List<String> list = new ArrayList<>(found);
        Collections.sort(list);
        System.out.println(String.join("|", list));
    }


    public void categoryReport(String input) {
        String[] tokens = input.split("[#|]");
        long booksamecategory=0;
        long thesiscategory=0;
        boolean found=false;
        for(Category cat :categories){
            if(cat.getId().equals(tokens[1]) || tokens[1].equals("null")){
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("not-found");
            return;
        }
        if(tokens[1].equals("null")){
            for(Book book : books) {
                if(book.getCategory().equals("null")) {
                    booksamecategory+=Integer.parseInt(book.getCopies());
                }
            }
            for(Thesis th : theses){
                if(th.getCategory().equals("null")) {
                    thesiscategory+=Integer.parseInt(th.getCopies());
                }
            }
            for(BorrowRecord borrowRecord:borrowRecords ) {
                for(Thesis thesis : theses) {
                    if(thesis.getCategory().equals("null") && borrowRecord.getBookid().equals(thesis.getId()) &&borrowRecord.getLibraryname().equals(thesis.getLibraryname())) {
                        thesiscategory++;
                    }
                }
                for(Book book : books) {
                    if(book.getCategory().equals("null") && borrowRecord.getBookid().equals(book.getId()) && borrowRecord.getLibraryname().equals(book.getLibraryid())) {
                        booksamecategory++;
                    }
                }
            }
            System.out.println(booksamecategory+" "+thesiscategory);
            return;
        }

        for(Category cat : categories) {
            if(tokens[1].equals(cat.getId())) {
                for(Book book : books) {
                    if(tokens[1].equals(book.getCategory())) {
                        booksamecategory+=Integer.parseInt(book.getCopies());
                    }
                }
                for(Thesis th : theses) {
                    if(tokens[1].equals(th.getCategory())) {
                        thesiscategory+=Integer.parseInt(th.getCopies());
                    }
                }
                for(Book book : books) {
                    for(BorrowRecord borrowRecord : borrowRecords) {
                        if (book.getCategory().equals(tokens[1]) && borrowRecord.getBookid().equals(book.getId()) && borrowRecord.getLibraryname().equals(book.getLibraryid()) ) {
                            booksamecategory++;

                        }
                    }
                }
                for(Thesis thesis : theses) {
                  for(BorrowRecord borrowRecord : borrowRecords) {
                      if (thesis.getCategory().equals(tokens[1]) && borrowRecord.getBookid().equals(thesis.getId()) && borrowRecord.getLibraryname().equals(thesis.getLibraryname()) ) {
                          thesiscategory++;
                      }
                  }
                }
                System.out.println(booksamecategory+" "+thesiscategory);
                return;
            }
        }
    }



    public void libraryReport(String input) {
        String[] tokens = input.split("[#|]");
        long booklibrary=0,thesislibrary=0,bookborrow=0,thesisborrow=0;
        boolean found=false;
        for(Library lib:libraries) {
            if(tokens[1].equals(lib.getId())) {
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("not-found");
            return;
        }
        for(Library lib : libraries) {
            if(tokens[1].equals(lib.getId())) {
                for(Book book : books) {
                    if(tokens[1].equals(book.getLibraryid())) {
                        booklibrary+=Integer.parseInt(book.getCopies());
                    }
                }
                for(Thesis th : theses) {
                    if (tokens[1].equals(th.getLibraryname())) {
                        thesislibrary+=Integer.parseInt(th.getCopies());
                    }
                }
                for(BorrowRecord borrow : borrowRecords) {
                    if(tokens[1].equals(borrow.getLibraryname())) {
                        for(Thesis thesis : theses) {
                            if(thesis.getId().equals(borrow.getBookid())) {
                                thesisborrow++;
                                thesislibrary++;
                                break;
                            }
                        }
                        for(Book book : books) {
                            if(book.getId().equals(borrow.getBookid())) {
                                bookborrow++;
                                booklibrary++;
                                break;
                            }
                        }
                    }
                }
                System.out.println(booklibrary+" "+thesislibrary+" "+bookborrow+" "+thesisborrow);
                return;
            }
        }
    }



    public void searchUser(String input) {
        String[] tokens = input.split("[#|]");
        boolean found=false;
        boolean pass =false;
        for(Student student : students){
            if(student.getId().equals(tokens[1])){
                found=true;
                if(student.getPassword().equals(tokens[2])){
                    pass=true;
                }
            }
        }
        for(Staff staff : staffs){
            if(staff.getId().equals(tokens[1])){
                found=true;
                if(staff.getPassword().equals(tokens[2])){
                    pass=true;
                }
            }
        }
        if(!found){
            System.out.println("not-found");
            return;
        }
        if(!pass){
            System.out.println("invalid-pass");
            return;
        }
        HashSet<String> people = new HashSet<>();
        for(Student student : students){
            String name=student.getName().toLowerCase();
            String name2=tokens[3].toLowerCase();
            String name3=student.getLastname().toLowerCase();
            if(name.contains(name2) || name3.contains(name2)){
                people.add(student.getId());
            }
        }
        for(Staff staff : staffs){
            String name=staff.getName().toLowerCase();
            String name2=tokens[3].toLowerCase();
            String name3=staff.getLastname().toLowerCase();
            if(name.contains(name2) || name3.contains(name2)){
                people.add(staff.getId());
            }
        }
        if(people.isEmpty()){
            System.out.println("not-found");
            return;
        }
        List<String> list = new ArrayList<>(people);
        Collections.sort(list);
        System.out.println(String.join("|", list));
    }



    public void reportPassedDeadline(String input) {
        HashSet<String> deadlines=new HashSet<>();
        String[] tokens = input.split("[#|]");
        boolean found=false;
        for (Library lib : libraries) {
            if(tokens[1].equals(lib.getId())) {
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("not-found");
            return;
        }
        long daylimit=0;
        boolean studentborrowed=false;
        boolean staffborrowed=false;
        boolean bookborrowed=false;
        boolean thesisborrowed=false;
        for(BorrowRecord borrow : borrowRecords) {
            if(tokens[1].equals(borrow.getLibraryname())) {
                staffborrowed=false;
                studentborrowed=false;
                bookborrowed=false;
                thesisborrowed=false;
                daylimit=0;
                LocalDateTime time1=LocalDateTime.parse(borrow.getDate().trim() + "T" + borrow.getTime().trim());
                LocalDateTime time2=LocalDateTime.parse(tokens[2].trim() + "T" + tokens[3].trim());
                for (Student student : students) {
                    if (student.getId().equals(borrow.getIdstudent())) {
                        studentborrowed=true;
                        break;
                    }
                }
                for (Staff staff : staffs) {
                    if(staff.getId().equals(borrow.getIdstudent())) {
                        staffborrowed=true;
                        break;
                    }
                }
                for (Book book : books) {
                    if (book.getId().equals(borrow.getBookid())){
                        bookborrowed=true;
                        break;
                    }
                }
                for (Thesis thesis : theses) {
                    if(thesis.getId().equals(borrow.getBookid())) {
                        thesisborrowed=true;
                        break;
                    }
                }
                if(studentborrowed){
                    if(bookborrowed){
                        daylimit=10;
                    }
                    else if(thesisborrowed){
                        daylimit=7;
                    }
                }
                if(staffborrowed){
                    if(bookborrowed){
                        daylimit=14;
                    }
                    else if(thesisborrowed){
                        daylimit=10;
                    }
                }
                LocalDateTime dueDate = time1.plusDays(daylimit);
                if(time2.isAfter(dueDate)) {
                    deadlines.add(borrow.getBookid());
                }
            }
        }
        if(deadlines.isEmpty()){
            System.out.println("none");
            return;
        }
        List<String> list = new ArrayList<>(deadlines);
        Collections.sort(list);
        System.out.println(String.join("|",list));
    }



    public void reportPenaltiesSum() {
        int sum=0;
        for(Student student : students) {
            if(student.getPenalties()>0) {
                sum += student.getPenalties();
            }
        }
        for(Staff staff : staffs) {
            if(staff.getPenalties()>0) {
                sum += staff.getPenalties();
            }
        }
        System.out.println(sum);
    }



    public void reserveSeat(String input) {
        String[] tokens=input.split("[#|]");
        boolean foundLib=false;
        String LibraryName="";
        for(Library lib : libraries) {
            if(tokens[3].equals(lib.getId())) {
                foundLib=true;
                LibraryName=tokens[3];
                break;
            }
        }
        if(!foundLib){
            System.out.println("not-found");
            return;
        }
        boolean foundid=false;
        boolean pass=false;
        for(Student student : students) {
            if(tokens[1].equals(student.getId())) {
                foundid=true;
                if(student.getPassword().equals(tokens[2])){
                    pass=true;
                }
            }
        }
        for(Staff staff : staffs) {
            if(tokens[1].equals(staff.getId())) {
                foundid=true;
                if(staff.getPassword().equals(tokens[2])){
                    pass=true;
                }
            }
        }
        if(!foundid){
            System.out.println("not-found");
            return;
        }
        if(!pass){
            System.out.println("invalid-pass");
            return;
        }
        LocalDateTime start = LocalDateTime.parse(tokens[4] + "T" + tokens[5]);
        LocalDateTime end = LocalDateTime.parse(tokens[4] + "T" + tokens[6]);
        long time=ChronoUnit.HOURS.between(start, end);
        if (time>8){
            System.out.println("not-allowed");
            return;
        }
        for(Student student : students) {
            if(tokens[1].equals(student.getId())) {
                for(Table table: tables) {
                    if( table.getIdstudent().equals(student.getId()) && table.getDate().equals(tokens[4])) {
                        System.out.println("not-allowed");
                        return;
                    }
                }
            }
        }
        for(Staff staff : staffs) {
            if(tokens[1].equals(staff.getId())) {
                for(Table table: tables) {
                    if ( table.getIdstudent().equals(staff.getId()) && table.getDate().equals(tokens[4])) {
                        System.out.println("not-allowed");
                        return;
                    }
                }
            }
        }
        int borrowed=0;
        for(Table table : tables) {
            if(table.getLibrary().equals(LibraryName) && table.getDate().equals(tokens[4])) {
                if(!(table.getEndtime().isBefore(start) || table.getStarttime().isAfter(end))) {
                    borrowed++;
                }
            }
        }

        for(Library lib : libraries) {
            if(tokens[3].equals(lib.getId())) {
                if(borrowed>=Integer.parseInt(lib.getTable())) {
                    System.out.println("not-available");
                    return;
                }
            }
        }
        System.out.println("success");
        Table newtable= new Table(tokens[3],tokens[1],tokens[4],tokens[5],tokens[6]);
        tables.add(newtable);
    }
}



class Library {
    private final String  id;
    private final  String name;
    private  final String year;
    private final  String table;
    private  final String address;
    public Library(String id, String name, String year, String table, String address) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.table = table;
        this.address = address;
    }
    public String getId() {
        return id;
    }
    public String getTable() {
        return table;
    }
}



class Category{
    private final String id;
    private final String name;
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
}



class Book {
    private String id;
    private String name;
    private String author;
    private String publisher;
    private String year;
    private String copies ;
    private String category;
    private String libraryid;
    public Book(String id, String name, String author, String publisher, String year, String copies, String category,String libraryid) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.copies = copies;
        this.category = category;
        this.libraryid = libraryid;

    }
    public String getId() {
        return id;
    }
    public String getLibraryid() {
        return libraryid;
    }
    public String getCategory() {
        return category;
    }
    public String getCopies() {
        return copies;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setCopies(String copies) {
        this.copies = copies;
    }
    public void setCategory(String Category) {
        this.category = Category;
    }
}





class Thesis{
    private String id;
    private String name;
    private String studentname;
    private String professorname;
    private String year;
    private String category;
    private String libraryname;
    private String Copies;
    public Thesis(String id, String name, String studentname, String professorname, String year, String category, String libraryname) {
        this.id = id;
        this.name = name;
        this.studentname = studentname;
        this.professorname = professorname;
        this.year = year;
        this.category = category;
        this.libraryname = libraryname;
        this.Copies = "1";
    }
    public String getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }
    public String getLibraryname() {
        return libraryname;
    }
    public String getCopies() {
        return Copies;
    }
    public String getName() {
        return name;
    }
    public String getStudentname() {
        return studentname;
    }
    public String getProfessorname() {
        return professorname;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
    public void setProfessorname(String professorname) {
        this.professorname = professorname;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setCopies(String Copies) {
        this.Copies = Copies;
    }

}





class Student {
    private String id;
    private String password;
    private String name;
    private String lastname;
    private String codemeli;
    private String years;
    private String address;
    private int borrowtime;
    private int penalties;
    public Student(String id, String password, String name, String lastname,String codemeli , String years, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.codemeli = codemeli;
        this.years = years;
        this.address = address;
        this.borrowtime = 0;
        this.penalties = 0;
    }
    public String getId() {
        return id;
    }
    public int getBorrowtime() {
        return this.borrowtime;
    }
    public int getPenalties() {
        return this.penalties;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setCodemeli(String codemeli) {
        this.codemeli = codemeli;
    }
    public void setYears(String years) {
        this.years = years;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBorrowtime(int borrowtime) {
        this.borrowtime = borrowtime;
    }
    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }
}





class Staff{
    private String id;
    private String password;
    private String name;
    private String lastname;
    private String codemeli;
    private String years;
    private String address;
    private int borrowtime;
    private int penalties;
    public Staff(String id, String password, String name, String lastname, String codemeli, String years, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.codemeli = codemeli;
        this.years = years;
        this.address = address;
        this.borrowtime = 0;
        this.penalties=0;
    }
    public String getId() {
        return id;
    }
    public int getBorrowtime() {
        return this.borrowtime;
    }
    public int getPenalties() {
        return this.penalties;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setCodemeli(String codemeli) {
        this.codemeli = codemeli;
    }
    public void setYears(String years) {
        this.years = years;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBorrowtime(int borrowtime) {
        this.borrowtime = borrowtime;
    }
    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

}




 class BorrowRecord{
    private String idstudent;
    private String password;
    private String libraryname;
    private String bookid;
    private String date;
    private String time;
    public BorrowRecord(String id, String password, String libraryname, String nameBook, String date, String time){
        this.idstudent = id;
        this.password = password;
        this.libraryname = libraryname;
        this.bookid = nameBook;
        this.date = date;
        this.time = time;
    }
    public String getIdstudent() {
        return idstudent;
    }
    public String getBookid() {
        return bookid;
    }
    public String getLibraryname() {
        return libraryname;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public void setPassword(String password) {this.password = password;}
 }


 class Table{
    private String Library;
    private String idstudent;
    private String date;
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    public Table(String Library, String idstudent, String date,String starttime,String endtime) {
        this.Library = Library;
        this.idstudent = idstudent;
        this.date = date;
        this.starttime = LocalDateTime.parse(date+"T"+starttime);
        this.endtime = LocalDateTime.parse(date+"T"+endtime);
    }
    public String getLibrary() {
        return Library;
    }
    public String getIdstudent() {return idstudent;}
    public String getDate() {return date;}
    public LocalDateTime getStarttime() {return starttime;}
    public LocalDateTime getEndtime() {return endtime;}
 }

