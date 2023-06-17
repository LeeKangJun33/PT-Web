package com.example.ptweb.controller.admin;

import com.example.ptweb.Service.packaze.PackageService;
import com.example.ptweb.Service.pass.BulkPassService;
import com.example.ptweb.Service.user.UserGroupMappingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminViewController {

    private final BulkPassService bulkPassService;
    private final PackageService packageService;
    private final UserGroupMappingService userGroupMappingService;

    public AdminViewController(BulkPassService bulkPassService, PackageService packageService, UserGroupMappingService userGroupMappingService){
        this.bulkPassService = bulkPassService;
        this.packageService = packageService;
        this.userGroupMappingService = userGroupMappingService;
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
