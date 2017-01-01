package com.internshipgo.controller;

import com.internshipgo.model.CompanyAgent;
import com.internshipgo.model.Student;
import com.internshipgo.model.User;
import com.internshipgo.model.YearHead;
import com.internshipgo.model.repository.CompanyAgentDao;
import com.internshipgo.model.repository.StudentDao;
import com.internshipgo.model.repository.UserDao;
import com.internshipgo.model.repository.YearHeadDao;
import com.internshipgo.view.LoginForm;
import com.internshipgo.view.SignUpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
/**
 * Created by choaib on 01/01/16.
 */

@Controller
public class MainController extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CompanyAgentDao companyAgentDao;
    @Autowired
    private YearHeadDao yearHeadDao;


    //no connected -----------


    @RequestMapping("/index")
    public String index(HttpSession session) {
        return "index";
    }

    @RequestMapping("/blog")
    public String blog() {
        return "blog";
    }
    @RequestMapping("/contact")
    public String contact(HttpSession session) {
        return "contact";
    }


    //candidate connected -----------


    @RequestMapping("/index-2")
    public String index2(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }

    @RequestMapping("/browse-jobs")
    public String browseJobs(HttpSession session) {

        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "browse-jobs";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";


    }

    @RequestMapping("/browse-categories")
    public String browseCategories(HttpSession session) {


        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "browse-categories";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";



    }

    @RequestMapping("/add-resume")
    public String addResume(HttpSession session) {

        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "/index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "add-resume";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "/index-4";
        }
        return "redirect:/index";

    }

    @RequestMapping("/manage-resumes")
    public String manageResumes(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "manage-resumes";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }

    @RequestMapping("/job-alerts")
    public String jobAlerts(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "job-alerts";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }
    @RequestMapping("/contact2")
    public String contact2(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "contact2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }
    //employer connected -----------

    @RequestMapping("/index-3")
    public String index3(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }

    @RequestMapping("/add-job")
    public String addJob(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "add-job";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }

    @RequestMapping("/manage-jobs")
    public String manageJobs(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "manage-jobs";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }

    @RequestMapping("/manage-applications")
    public String manageApplications(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);
            return "manage-applications";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";

    }

    @RequestMapping("/browse-resumes")
    public String browseResumes(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);
            return "browse-resumes";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }

    @RequestMapping("/contact3")
    public String contact3(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);
            return "contact3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }

    //year head  connected -----------

    @RequestMapping("/index-4")
    public String index4(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }




    @RequestMapping("/manage-conventions")
    public String manageConventions(HttpSession session) {

        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "manage-conventions";
        }
        return "redirect:/index";
    }

    @RequestMapping("/contact4")
    public String contact4(HttpSession session) {

        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);

            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "contact4";
        }
        return "redirect:/index";
    }

    //pages annexe  -----------

    @RequestMapping("/shortcodes")
    public String shortcodes(HttpSession session) {
        return "shortcodes";
    }

    @RequestMapping("/resume-page")
    public String resumePage(HttpSession session) {
        return "resume-page";
    }

    @RequestMapping("/job-page")
    public String jobPage(HttpSession session) {
        return "job-page";
    }
    @RequestMapping("/job-page-alt")
    public String jobPageAlt(HttpSession session) {
        return "job-page-alt";
    }


    //others

    private String redirectIndex(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);
            return "redirect:/index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            return "redirect:/index-2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "redirect:/index-4";
        }
        return "redirect:/index";
    }


    //TODO :(supprimer signup)
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/createUser")
    public String showCreateUserForm(Model model, SignUpForm signUpForm) {
        /*List<String> userTypes= new ArrayList<String>();
        userTypes.add("Student");
        userTypes.add("Company");
        model.addAttribute("userTypes", userTypes);*/
        return "my-account";
    }



    @PostMapping("/createUser")
    public String checkUserInfo(LoginForm loginForm, Model model, HttpSession session, @Valid SignUpForm signUpForm, BindingResult bindingResult) {
        System.out.println("in createUser Post method");
        System.out.println(signUpForm);
        //To check if the email is already in use
        List<User> users = userDao.findByEmail(signUpForm.getEmail());
        if(bindingResult.hasErrors() || !signUpForm.getConfPassword().equals((String) signUpForm.getPassword())
                || users.size() != 0) {
            return "my-account";
        }else{
            System.out.println(signUpForm);
            if(signUpForm.getUserType().equals("Student")) {
                User student = new Student();
                student.setEmail(signUpForm.getEmail());
                student.setPassword(signUpForm.getPassword());
                student.setField(signUpForm.getField());
                student.setUserName(signUpForm.getUserName());
                session.setAttribute("activeUser",student);
                studentDao.save((Student)student);
                return "index-2";
            } else if(signUpForm.getUserType().equals("Company")){
                User company = new CompanyAgent();
                company.setEmail(signUpForm.getEmail());
                company.setPassword(signUpForm.getPassword());
                company.setField(signUpForm.getField());
                session.setAttribute("activeUser", company);
                companyAgentDao.save((CompanyAgent) company);
                return "index-3";
            } else if(signUpForm.getUserType().equals("YearHead")){
                User yearHead = new YearHead();
                yearHead.setEmail(signUpForm.getEmail());
                yearHead.setPassword(signUpForm.getPassword());
                yearHead.setField(signUpForm.getField());
                session.setAttribute("activeUser", yearHead);
                yearHeadDao.save((YearHead)yearHead);
                return "index-4";
            }
            return "redirect:/index";
        }

    }


    @GetMapping("/my-account")
    public String loginRedirect(LoginForm loginForm, Model model, HttpSession session, SignUpForm signUpForm, BindingResult bindingResult) {
        return redirectIndex(session);
    }
    @GetMapping("/disconnect")
    public String discRedirect(LoginForm loginForm, Model model, HttpSession session, SignUpForm signUpForm, BindingResult bindingResult) {
        session.invalidate();
        return "/index";
    }

    @PostMapping("/login")
    public String loginAction(@Valid LoginForm loginForm, HttpSession session, SignUpForm signUpForm, BindingResult bindingResult) {
        User user = userDao.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        System.out.println("in the login action method " + loginForm.getEmail() +", " + loginForm.getPassword());
        if( user == null ) {
            return "/my-account";
        }else {
            session.setAttribute("activeUser", user);
            return redirectIndex(session);
        }
    }

}
