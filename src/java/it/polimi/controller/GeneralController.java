package it.polimi.controller;



import it.polimi.model.Account;
import it.polimi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;



/**
 * Created by Jacopo Magni on 24/10/2016.
 */
@Controller
public class GeneralController {

    @Autowired
    private AccountService service;

    Account loggedUser= new Account();
    boolean logVerify = false;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String formLogin(Model model) {
        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println("Tempo quando sono in login "+timestamp);
        //System.out.println("Conferma registrazione quando sono in login "+account.getConfirmReg());
        logVerify = true;
        Account account = new Account();
        model.addAttribute("Account", account);

        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginForm(Model model, @Valid @ModelAttribute("Account")Account account, BindingResult bindingResult, RedirectAttributes redirect){



        if(bindingResult.hasErrors()){
            return "/login";
            }
        else {
            if (!service.validatePassword(account)){
                return "redirect:/login";
            } else {
                account.setOid(service.retrieveAccountId(account));
                redirect.addFlashAttribute("account", account);
                return "redirect:/profilePage";
            }
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String formRegistration(Model model) {
        model.addAttribute(new Account());
        logVerify = false;
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String formRegistration(@ModelAttribute("Account")Account account, RedirectAttributes redirect) {


        service.createAccount(account);
        redirect.addFlashAttribute("Account", account);

        return "redirect:/login";
    }

    @RequestMapping(value = "/profilePage", method = RequestMethod.GET)
    public String formProfile(@ModelAttribute("account")Account account,Model model) {


        if (logVerify == true) {
            Account accountRetrived = service.retrieveAccountFromNickname(account.getNickname());
            model.addAttribute(accountRetrived);
            loggedUser = accountRetrived;
            }
         else
            model.addAttribute(loggedUser);
        logVerify = false;
        return "profilePage";
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String coursesList(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "courses";
    }

    @RequestMapping(value = "/historyCourse", method = RequestMethod.GET)
    public String historyCourseLectureList(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "historyCourse";
    }

    @RequestMapping(value = "/mathCourse", method = RequestMethod.GET)
    public String mathCourseLectureList(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "mathCourse";
    }

    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public String Info(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "aboutUs";
    }

    @RequestMapping(value = "/historyLectureOne", method = RequestMethod.GET)
    public String HistoryLecture1(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "historyLectureOne";
    }

    @RequestMapping(value = "/historyLectureTwo", method = RequestMethod.GET)
    public String HistoryLecture2(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "historyLectureTwo";
    }

    @RequestMapping(value = "/historyLectureThree", method = RequestMethod.GET)
    public String HistoryLecture3(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "historyLectureThree";
    }

    @RequestMapping(value = "/historyLectureOneQuestions", method = RequestMethod.GET)
    public String HistoryLecture1Q(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "historyLectureOneQuestions";
    }

    @RequestMapping(value = "/historyLectureTwoQuestions", method = RequestMethod.GET)
    public String HistoryLecture2Q(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "historyLectureTwoQuestions";
    }

    @RequestMapping(value = "/historyLectureThreeQuestions", method = RequestMethod.GET)
    public String HistoryLecture3Q(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "historyLectureThreeQuestions";
    }

    @RequestMapping(value = "/answers", method = RequestMethod.GET)
    public String answers(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "answers";
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public String questions(Model model) {

        logVerify = false;
        model.addAttribute("account", loggedUser);
        return "questions";
    }

}
