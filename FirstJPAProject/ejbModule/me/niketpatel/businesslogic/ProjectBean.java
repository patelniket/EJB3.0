package me.niketpatel.businesslogic;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import me.niketpatel.business.IProjectBeanRemote;
import me.niketpatel.entities.Project;

@Stateless
public class ProjectBean implements IProjectBeanRemote {
	
	@PersistenceContext(unitName = "JPADB")
	private EntityManager entityManager;

    public ProjectBean() {  }
    
    @Override
    public void saveProject(Project project){
    	entityManager.persist(project);
    }
    
    @Override
    public Project findProject(Project project){
    	Project p = entityManager.find(Project.class, project.getPnumber());
    	return p;
    }
    
    @Override
    public List<Project> retrieveAllProjects (){
    	String q = "SELECT p FROM " + Project.class.getName() + " p";
    	Query query = entityManager.createQuery(q);
    	@SuppressWarnings("unchecked")
		List<Project> projects = query.getResultList();
    	return projects;
    }

}
