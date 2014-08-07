package me.niketpatel.businesslogic;

import javax.ejb.Stateless;
import me.niketpatel.business.HelloWorldBeanRemote;

/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
public class HelloWorldBean implements HelloWorldBeanRemote {

    public HelloWorldBean() {
    }
    
    public String sayHello(){
    	return "Hello World, from EJB 3.0";
    }

}
