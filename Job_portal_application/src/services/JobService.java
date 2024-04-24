package services;

import java.util.ArrayList;
import java.util.List;

import job_portal_application.models.Job;
import job_portal_application.models.Organization;

public class JobService 
{
	public List<Job> getAllJobs(List<Organization> organizations) 
	{
        List<Job> allJobs = new ArrayList<>();
        for (Organization organization : organizations) 
        {
            allJobs.addAll(organization.getJobs());
        }
        return allJobs;
    }

    public List<Job> getJobsByOrganization(Organization organization) 
    {
        return organization.getJobs();
    }

    public List<Job> filterJobsByTitle(List<Job> jobs, String keyword)
    {
        List<Job> filteredJobs = new ArrayList<>();
        for (Job job : jobs) 
        {
            if (job.getTitle().toLowerCase().contains(keyword.toLowerCase()))
            {
                filteredJobs.add(job);
            }
        }
        return filteredJobs;
    }
    public List<Job> filterJobsByRequirements(List<Job> jobs, String keyword)
    {
        List<Job> filteredJobs = new ArrayList<>();
        for (Job job : jobs) {
            if (job.getRequirements().toLowerCase().contains(keyword.toLowerCase()))
            {
                filteredJobs.add(job);
            }
        }
        return filteredJobs;
    }

    public List<Job> getAllJobsForOrganizations(List<Organization> organizations) 
    {
        List<Job> allJobs = new ArrayList<>();
        for (Organization organization : organizations) 
        {
            allJobs.addAll(organization.getJobs());
        }
        return allJobs;
    }
}
