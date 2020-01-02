//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.edu.shu.energy.Controller;

import cn.edu.shu.energy.Model.Device;
import cn.edu.shu.energy.Model.District;
import cn.edu.shu.energy.Model.Line;
import cn.edu.shu.energy.Model.User;
import cn.edu.shu.energy.Model.Workshop;
import cn.edu.shu.energy.Repository.DeviceRepository;
import cn.edu.shu.energy.Repository.DistrictRepository;
import cn.edu.shu.energy.Repository.LineRepository;
import cn.edu.shu.energy.Repository.UserRepository;
import cn.edu.shu.energy.Repository.WorkshopRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    WorkshopRepository workshopRepository;
    @Autowired
    LineRepository lineRepository;
    @Autowired
    DeviceRepository deviceRepository;

    public PageController() {
    }

    @RequestMapping({"/"})
    public String home(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "index");
        return "frame";
    }

    @RequestMapping(
            value = {"/login"},
            method = {RequestMethod.GET}
    )
    public String loginPage() {
        return "login";
    }

    @RequestMapping(
            value = {"/workshop/list"},
            method = {RequestMethod.GET}
    )
    public String workshopList(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/workshopList");
        return "frame";
    }

    @RequestMapping({"/workshop/edit/{id}"})
    public String workshopEdit(@PathVariable String id, HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        Workshop dbWorkshop = this.workshopRepository.findWorkshopById(id);
        if (dbWorkshop == null) {
            model.addAttribute("isNew", Boolean.TRUE);
            model.addAttribute("workshop", new Workshop());
        } else {
            model.addAttribute("isNew", Boolean.FALSE);
            model.addAttribute("workshop", dbWorkshop);
        }

        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/workshopEdit");
        return "frame";
    }

    @RequestMapping({"/workshop/edit"})
    public String workshopCreate(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("isNew", Boolean.TRUE);
        model.addAttribute("workshop", new Workshop());
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/workshopEdit");
        return "frame";
    }

    @RequestMapping({"/district/list"})
    public String districtList(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/districtList");
        return "frame";
    }

    @RequestMapping({"/district/edit/{id}"})
    public String districtEdit(@PathVariable String id, HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        District dbDistrict = this.districtRepository.findDistinctById(id);
        if (dbDistrict == null) {
            model.addAttribute("isNew", Boolean.TRUE);
            model.addAttribute("district", new District());
        } else {
            model.addAttribute("isNew", Boolean.FALSE);
            model.addAttribute("district", dbDistrict);
        }

        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/districtEdit");
        return "frame";
    }

    @RequestMapping({"/district/edit"})
    public String districtCreate(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("isNew", Boolean.TRUE);
        model.addAttribute("district", new District());
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/districtEdit");
        return "frame";
    }

    @RequestMapping({"/line/list"})
    public String lineList(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/lineList");
        return "frame";
    }

    @RequestMapping({"/line/edit/{id}"})
    public String lineEdit(@PathVariable String id, HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        Line dbLine = this.lineRepository.findLineById(id);
        if (dbLine == null) {
            model.addAttribute("isNew", Boolean.TRUE);
            model.addAttribute("line", new Line());
        } else {
            model.addAttribute("isNew", Boolean.FALSE);
            model.addAttribute("line", dbLine);
        }

        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/lineEdit");
        return "frame";
    }

    @RequestMapping({"/line/edit"})
    public String lineCreate(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("isNew", Boolean.TRUE);
        model.addAttribute("line", new Line());
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/lineEdit");
        return "frame";
    }

    @RequestMapping({"/device/list"})
    public String deviceList(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/deviceList");
        return "frame";
    }

    @RequestMapping({"/device/edit/{id}"})
    public String deviceEdit(@PathVariable String id, HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        Device dbDevice = this.deviceRepository.findDeviceById(id);
        if (dbDevice == null) {
            model.addAttribute("isNew", Boolean.TRUE);
            model.addAttribute("device", new Device());
        } else {
            model.addAttribute("isNew", Boolean.FALSE);
            model.addAttribute("device", dbDevice);
        }

        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/deviceEdit");
        return "frame";
    }

    @RequestMapping({"/device/edit"})
    public String deviceCreate(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("isNew", Boolean.TRUE);
        model.addAttribute("device", new Device());
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/deviceEdit");
        return "frame";
    }

    @RequestMapping({"/user/list"})
    public String userList(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/userList");
        return "frame";
    }

    @RequestMapping({"/user/edit/{uid}"})
    public String userEdit(@PathVariable String uid, HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        User dbUser = this.userRepository.findUserById(uid);
        if (dbUser == null) {
            model.addAttribute("isNew", Boolean.TRUE);
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("isNew", Boolean.FALSE);
            dbUser.setPassword("");
            model.addAttribute("user", dbUser);
        }

        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "manage/userEdit");
        return "frame";
    }

    @RequestMapping({"/user/edit"})
    public String userCreate(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("isNew", Boolean.TRUE);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("user", new User());
        model.addAttribute("pageName", "manage/userEdit");
        return "frame";
    }

    @RequestMapping({"/log"})
    public String log(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "log");
        return "frame";
    }

    @RequestMapping({"test"})
    public String test(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "test");
        return "frame";
    }

    @RequestMapping({"shopview"})
    public String shopview(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "shopview");
        return "frame";
    }

    @RequestMapping({"sepview"})
    public String sepview(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "sepview");
        return "frame";
    }

    @RequestMapping({"enconsum"})
    public String enconsum(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "enconsum");
        return "frame";
    }

    @RequestMapping({"powconsum"})
    public String powconsum(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "powconsum");
        return "frame";
    }

    @RequestMapping({"sameanaly"})
    public String sameanaly(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "sameanaly");
        return "frame";
    }

    @RequestMapping({"paraanaly"})
    public String paraanaly(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "paraanaly");
        return "frame";
    }

    @RequestMapping({"rank"})
    public String rank(HttpSession session, Model model) {
        User currentUser = (User)session.getAttribute(User.CURRENT_USER);
        model.addAttribute("userName", currentUser.getName());
        model.addAttribute("uid", currentUser.getId());
        model.addAttribute("pageName", "rank");
        return "frame";
    }
}
