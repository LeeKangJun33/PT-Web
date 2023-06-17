package com.example.ptweb.controller.admin;

import com.example.ptweb.Service.packaze.PackageService;
import com.example.ptweb.Service.pass.BulkPassService;
import com.example.ptweb.Service.statistics.StatisticsService;
import com.example.ptweb.Service.user.UserGroupMappingService;
import com.example.ptweb.util.LocalDateTimeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/admin")
public class AdminViewController {

    private final BulkPassService bulkPassService;
    private final PackageService packageService;
    private final UserGroupMappingService userGroupMappingService;

    private StatisticsService  statisticsService;

    public AdminViewController(BulkPassService bulkPassService, PackageService packageService, UserGroupMappingService userGroupMappingService,StatisticsService statisticsService){
        this.bulkPassService = bulkPassService;
        this.packageService = packageService;
        this.userGroupMappingService = userGroupMappingService;
        this.statisticsService = statisticsService;
    }

    public ModelAndView home(ModelAndView modelAndView, @RequestParam("to") String toString){
        LocalDateTime to = LocalDateTimeUtils.parseDate(toString);
        modelAndView.addObject("chartData", statisticsService.makeChartData(to));
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    @GetMapping("/bulk-pass")
    public ModelAndView registerBulkPass(ModelAndView modelAndView){
        modelAndView.addObject("bulkPasses",bulkPassService.getAllBulkPasses());
        modelAndView.addObject("packages",packageService.getAllPackages());
        modelAndView.addObject("userGroupIds",userGroupMappingService.getAllUserGroupIds());
        modelAndView.addObject("request", new BulkPassRequest());
        modelAndView.setViewName("admin/bulk-pass");

        return modelAndView;
    }

    public String addBulkPass(@ModelAttribute("request") BulkPassRequest request, Model model){
        //bulk pass 생성
        bulkPassService.addBulkPass(request);
        return "redirect:/admin/bulk-pass";
    }
}
