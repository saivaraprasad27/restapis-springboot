package com.learn.DecProjectModule;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        return "UnifyApps";
    }

    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public String sayPersonNameBasedOnId(@PathVariable("id") String id){
        if(id.equals("1")){
            return "saivaraprasad kondaka";
        }

        else if(id.equals("2")){
            return "Thanuja Kondaka";
        }

        else if(id.equals("3")){
            return "Apparao Kondaka";
        }

        return "Invalid Path Param";
    }
}
