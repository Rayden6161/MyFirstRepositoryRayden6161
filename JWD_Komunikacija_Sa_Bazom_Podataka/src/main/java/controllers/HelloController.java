package controllers;

import model.Impression;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @GetMapping("/hello")
  public ModelAndView create() throws ClassNotFoundException {
      ModelAndView mv = new ModelAndView();
      mv.addObject("impression", new Impression());
      //impression nam je commandName u nasoj formi u hello.jsp i sluzi za povezivanje polja formi sa klasom modela tj sanjemim poljima

      mv.addObject("impressions",Impression.all());

      mv.setViewName("hello");
      return mv;
  }

@PostMapping("/hello")
    public  ModelAndView add(@ModelAttribute("impression")Impression i) throws ClassNotFoundException {
        i.insert(); //dodavamo novi utisak
    return create(); //kad ubacimo utisak opet pravimo ili ucitavamo tabelu
}


    }

