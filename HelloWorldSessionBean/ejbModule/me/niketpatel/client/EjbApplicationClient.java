package me.niketpatel.client;

import javax.naming.Context;
import javax.naming.NamingException;

import me.niketpatel.business.HelloWorldBeanRemote;
import me.niketpatel.businesslogic.HelloWorldBean;
import me.niketpatel.clientutility.ClientUtility;

public class EjbApplicationClient {

	public static void main(String[] args) {
		HelloWorldBeanRemote bean = doLookUp();
		System.out.println(bean.sayHello()); //4. Call business logic
	}
	
	private static HelloWorldBeanRemote doLookUp(){
		Context context = null;
		HelloWorldBeanRemote bean = null;
		
		try{
			//1. Obtaining Context
			context = ClientUtility.getInitialContext();
			//2. Generate JNDI Lookup Name
			String lookupName = getLookupName();
			//3. Lookup and cast
			bean = (HelloWorldBeanRemote) context.lookup(lookupName);
			
		} catch (NamingException e){
			e.printStackTrace();
		}
		return bean;
	}
	
	private static String getLookupName(){
		
/*
The app name is the EAR name of the deployed EJB without .ear suffix.
Since we haven't deployed the application as a .ear,
the app name for us will be an empty string
*/
        String appName = "";
 
/* The module name is the JAR name of the deployed EJB
without the .jar suffix.
*/
        String moduleName = "HelloWorldSessionBean";
 
/*AS7 allows each deployment to have an (optional) distinct name.
This can be an empty string if distinct name is not specified.
*/	
        String distinctName = "";
        
        //The EJB bean implementation class name
        String beanName = HelloWorldBean.class.getSimpleName();
        
        //Fully qualified remote interface name
        String interfaceName = HelloWorldBeanRemote.class.getName();
        
        //Create a lookup string name
        String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/"
        		+ beanName + "!" + interfaceName;
        
        return name;
	}

}
