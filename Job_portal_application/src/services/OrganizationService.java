package services;

import java.util.ArrayList;
import java.util.List;

import job_portal_application.models.Job;
import job_portal_application.models.Organization;
import job_portal_application.models.User;

public class OrganizationService 
{
    private List<Organization> organizations;

    public OrganizationService() 
    {
        this.organizations = new ArrayList<>();
    }

    public Organization createOrganization(User owner, String name, String description) 
    {
        organizations.add(new Organization(name, description, owner));
		return null;
    }

    public void addJob(Organization organization, String title, String description, String requirements) 
    {
        organization.addJob(new Job(title, description, requirements, organization));
    }

    public List<Job> getAllOrganizations()
    {
        List<Job> allJobs = new ArrayList<>();
        for (Organization organization : organizations) 
        {
            allJobs.addAll(organization.getJobs());
        }
        return allJobs;
    }

    public void addJob(Job job, String title, String description, String requirements) 
    {
       // job.getOrganization().addJob(new Job(title, description, requirements, job.getOrganization()));
    }
}
