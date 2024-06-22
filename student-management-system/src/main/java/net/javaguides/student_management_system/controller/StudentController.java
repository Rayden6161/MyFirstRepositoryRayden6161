package net.javaguides.student_management_system.controller;

import jakarta.validation.Valid;
import net.javaguides.student_management_system.entity.Student;
import net.javaguides.student_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    //handler method to handle  list students and return mode and view

    @GetMapping("/students")
    public String  listStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "students";
    }
    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        //create  student object  to hold student form data.
        Student student = new Student();
        model.addAttribute("student",student);
        return "create_student";
    }
    @GetMapping("/students/search")
    public String searchStudents(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Student> searchResult = studentService.searchStudents(keyword); // Implementiraj ovu metodu u servisu
if(searchResult.isEmpty()){
    return "redirect:/students";
}
        model.addAttribute("students", searchResult);
        model.addAttribute("keyword", keyword); // Dodajemo keyword u model kako bismo ga mogli prikazati na front-endu

        return "students";} // Vrati istu view stranicu koja prikazuje listu studenata
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student")@Valid Student student,

                              BindingResult result,Model model) throws IOException {
    //ovo sam modifikovao jer hocu da svaki student ima svoju sliku.

        studentService.saveStudent(student);

        return "redirect:/students"; //posle addovanja studenta redirektuje nas na listu,logicno.
    }
    @GetMapping("/students/edit/{id}")
    public String  editStudentForm(@PathVariable Long id,Model model){
        model.addAttribute("student",studentService.getStudentById(id));
        return "edit_student";
        //prvo radimo ovu metodu ,pa radimo u edit_student.html pa onda pravimo jos
        //jednu metodu za update
        //unutar edit_student nasa forma salje post metodom tako da ce donja metoda ici
        //u @PostMapping ...
    }
    @PostMapping("/students/{id}")
public String updateStudent(@PathVariable Long id,
                            @ModelAttribute("student") @Valid Student student,

                            BindingResult result,
                            Model model) throws IOException {
        //preuzmi studenta iz db pomocu id-ja
        Student existingStudent=studentService.getStudentById(id);
        // Ako je postavljena nova slika, ažuriraj je

        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        //snimi update-ovanog studenta
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }
    //handler method to handle delete student request
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id ){
        //mi moramo izbrisati iz db ovog studenta ,zato idemo u service
studentService.deleteStudentById(id);
 return  "redirect:/students";

    }

}


/*
ChatGPT
Da bismo omogućili opciju dodavanja i uklanjanja slike prilikom ažuriranja studenta, potrebno je izvršiti nekoliko izmena u HTML formi za ažuriranje studenta (edit_student.html) i u StudentController klasi.

Korak 1: Ažuriranje HTML forme za ažuriranje studenta
U HTML formi edit_student.html, dodajemo polje za upload slike i checkbox za uklanjanje slike.

html
Copy code
<form th:action="@{/students/{id}(id=${student.id})}" th:object="${student}" method="post" enctype="multipart/form-data">
    <!-- Ostala polja za studenta -->
    <div class="form-group">
        <label for="image">Student Image</label>
        <input type="file" id="image" name="image" class="form-control-file">
    </div>
    <div class="form-group form-check">
        <input type="checkbox" id="removeImage" name="removeImage" class="form-check-input">
        <label class="form-check-label" for="removeImage">Remove Image</label>
    </div>
    <div class="box-footer">
        <button type="submit" class="btn btn-primary">Update</button>
    </div>
</form>
Korak 2: Ažuriranje Controller-a za obradu slike prilikom ažuriranja
U StudentController, ažuriramo metodu updateStudent tako da podržava dodavanje i uklanjanje slike.

java
Copy code
@PostMapping("/students/{id}")
public String updateStudent(@PathVariable Long id,
                            @ModelAttribute("student") Student student,
                            @RequestParam(value = "image", required = false) MultipartFile imageFile,
                            @RequestParam(value = "removeImage", required = false, defaultValue = "false") boolean removeImage,
                            Model model) throws IOException {

    Student existingStudent = studentService.getStudentById(id);

    // Ako je postavljena nova slika, ažuriraj je
    if (imageFile != null && !imageFile.isEmpty()) {
        existingStudent.setImage(imageFile.getBytes());
    }

    // Ako je označeno uklanjanje slike, postavi sliku na null
    if (removeImage) {
        existingStudent.setImage(null);
    }

    existingStudent.setFirstName(student.getFirstName());
    existingStudent.setLastName(student.getLastName());
    existingStudent.setEmail(student.getEmail());

    studentService.updateStudent(existingStudent);
    return "redirect:/students";
}
Objasnjenje:
HTML forma: Dodali smo polje za unos slike (<input type="file">) i checkbox (<input type="checkbox">) za uklanjanje slike. Ako je checkbox označen, šaljemo parametar removeImage sa vrednošću true. Ako korisnik odabere novu sliku, šaljemo imageFile kao MultipartFile.

Controller: U updateStudent metodi, dodali smo logiku za rukovanje slikom:

Ako je imageFile postavljen, učitavamo sliku i ažuriramo byte[] polje u existingStudent.
Ako je removeImage označen, postavljamo existingStudent sliku na null.
Ostala polja studenta se ažuriraju kao i prethodno.
Nakon što se student ažurira, koristimo redirect:/students kako bismo se vratili na listu studenata.
Napomena:
Prilikom ažuriranja slike, preporučuje se da se korisnicima pruži povratna informacija o uspešnom ili neuspešnom dodavanju slike kako bi se unapredilo korisničko iskustvo.

Prilagodi implementaciju prema specifičnim zahtevima tvoje aplikacije i korisničkom interfejsu kako bi se postigla najbolja upotrebljivost.

Ovako implementirana opcija omogućava korisnicima da dodaju, ažuriraju ili uklone sliku za svakog studenta prilikom ažuriranja informacija o studentu.
* */
