package me.niketpatel.business;

import java.util.List;
import javax.ejb.Remote;

import me.niketpatel.entities.Project;

@Remote
public interface IProjectBeanRemote {
	void saveProject(Project project);
	Project findProject(Project project);
	List<Project> retrieveAllProjects();

}
