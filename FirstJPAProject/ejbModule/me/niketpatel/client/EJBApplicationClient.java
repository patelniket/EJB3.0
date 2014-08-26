package me.niketpatel.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;

import me.niketpatel.business.IProjectBeanRemote;
import me.niketpatel.businesslogic.ProjectBean;
import me.niketpatel.clientutility.JNDILookupClass;
import me.niketpatel.entities.Project;

public class EJBApplicationClient {

	public static void main(String[] args) {
		IProjectBeanRemote bean = doLookUp();
		
		Project p1 = new Project();
		p1.setPname("Banking App");
        p1.setPlocation("Town City");
        p1.setDeptNo(1);
         
        Project p2 = new Project();
        p2.setPname("Office Automation");
        p2.setPlocation("Downtown");
        p2.setDeptNo(2);
 
        // 4. Call business logic
        //Saving new Projects
        bean.saveProject(p1);
        bean.saveProject(p2);
        
        //Find a Project
        //p1.setPnumber(1);
        //Project p3 = bean.findProject(p1);
        //System.out.println(p3);
         
        //Retrieve all projects
        System.out.println("List of Projects:");
        List<Project> projects = bean.retrieveAllProjects();
        for(Project project : projects){
            System.out.println(project);
        }

	}

	private static IProjectBeanRemote doLookUp() {
		Context context = null;
		IProjectBeanRemote bean = null;

		try {			
			context = JNDILookupClass.getInitialContext();			
			String lookupName = getLookupName();			
			bean = (IProjectBeanRemote) context.lookup(lookupName);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}

	private static String getLookupName() {
		String appName = "";

		String moduleName = "FirstJPAProject";
		String distinctName = "";
		String beanName = ProjectBean.class.getSimpleName();
		String interfaceName = IProjectBeanRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;
		return name;
	}
}
