package com.internshipgo.controller;

import com.internshipgo.model.*;
import com.internshipgo.model.repository.*;
import com.internshipgo.view.*;
import com.internshipgo.view.LoginForm;
import com.internshipgo.view.SignUpForm;
import com.internshipgo.view.UpdateEmailForm;
import com.internshipgo.view.UpdateNameForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
//TODO: Adding a mailing service to students, if they got accepted
@Controller
public class MainController extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    InternshipOfferDao internshipOfferDao;
    @Autowired
    OfferStatusDao offerStatusDao;


    //no connected -----------

    @RequestMapping("/index")
    public String index() {
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
    public ModelAndView browseJobs(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        ModelAndView model=new ModelAndView("/index");
        if (user == null) {
            model = new ModelAndView("redirect:my-account");
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);
             model= new ModelAndView("/index-3");
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            model = new ModelAndView("/browse-jobs");
            model.addObject("lists", internshipOfferDao.findAll());
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            model= new ModelAndView("/index-4");
        }
        return model;
    }

    @RequestMapping(value = "/browse-jobs/{internshipId}")
    public ModelAndView ShowInternshipDetaillee(@PathVariable("internshipId") Long internshipId){
        ModelAndView model = new ModelAndView("job-page");
        model.addObject("internship", internshipOfferDao.findOne(internshipId));
        return model;
    }

    @RequestMapping("/add-resume")
    public String addResume(UpdateNameForm nameForm, HttpSession session, Model model) {
        model.addAttribute("nameForm", new UpdateNameForm());
        model.addAttribute("mailForm", new UpdateEmailForm());
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            return "/index-3";
        } else if( user.getClass() == Student.class) {
            return "add-resume";
        } else if ( user.getClass() == YearHead.class) {
            return "/index-4";
        }
        return "add-resume";
    }

    @RequestMapping("/edit-profile")
    public String editProfile(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            return "/index-3";
        } else if( user.getClass() == Student.class) {
            return "Edit-profile";
        } else if ( user.getClass() == YearHead.class) {
            return "/index-4";
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

    @RequestMapping("/manage-conventions2")
    public String manageConvention2(HttpSession session, Model model) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);
            return "index-3";
        } else if( user.getClass() == Student.class) {
            session.setAttribute("activeUser" , user);
            List<OfferStatus> acceptedOffers = offerStatusDao.getAcceptedOfferStatusByStudentId(user.getId());
            session.setAttribute("activeUser" , user);
            if (acceptedOffers.size() != 0)
                model.addAttribute("offer", acceptedOffers.get(0));
            else
                model.addAttribute("offer", null);

            return "manage-conventions2";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        } else {
            return "index";
        }
    }

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

    /*@RequestMapping("/add-job")
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
    }*/

    /*@RequestMapping("/manage-jobs")
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
    }*/


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
    @PostMapping("/studentconvention")
    public String studentconv(@Valid ManageConventionS nameForm, HttpSession session, BindingResult bindingResult, Model model) {
        model.addAttribute("nameForm", new UpdateNameForm());
        model.addAttribute("mailForm", new UpdateEmailForm());
        User user = (User) session.getAttribute("activeUser");
        if (user != null  && (user.getClass() == Student.class) ) {
            if ( !bindingResult.hasErrors() ) {
                return "add-resume";
            }
        } else {
            return "my-account";
        }
        return "redirect:/add-resume";
    }

    @PostMapping("/updateEmail")
    public String updateEmail(@Valid UpdateEmailForm mailForm, HttpSession session, BindingResult bindingResult, Model model) {
        List<User> users = userDao.findByEmail(mailForm.getEmail());
        model.addAttribute("nameForm", new UpdateNameForm());
        model.addAttribute("mailForm", new UpdateEmailForm());
        User user = (User) session.getAttribute("activeUser");
        if ( user != null && (user.getClass() == Student.class) ) {
            if ( !bindingResult.hasErrors() && users.size() == 0) {
                userDao.updateEmail(user.getId(), mailForm.getEmail());
                return "add-resume";
            }
        } else {
            return "my-account";
        }
        return "redirect:/add-resume";
    }

    @GetMapping("/my-account")
    public String loginRedirect(LoginForm loginForm, HttpSession session, SignUpForm signUpForm) {
        return redirectIndex(session);
    }

    @GetMapping("/disconnect")
    public String discRedirect(HttpSession session) {
        session.invalidate();
        return "/index";
    }

    @PostMapping("/login")
    public String loginAction(@Valid LoginForm loginForm, HttpSession session, SignUpForm signUpForm, BindingResult bindingResult) {
        User user = userDao.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        System.out.println("in the login action method " + loginForm.getEmail() +", " + loginForm.getPassword());
        if( user == null ) {
            bindingResult.addError(new ObjectError("loginError", "Wrong user Name or Password"));
            return "redirect:my-account#tab1";
        }else {
            session.setAttribute("activeUser", user);
            return redirectIndex(session);
        }
    }



}
