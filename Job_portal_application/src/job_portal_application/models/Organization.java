package job_portal_application.models;
import java.util.ArrayList;
import java.util.List;
public class Organization 
{
	private String name;
	private String owner;
    private String description;
    private List<Job> jobs;

    public Organization(String name, String description, User owner) 
    {
        this.name = name;
        this.description = description;
        this.jobs = new ArrayList<>();
    }

    public String getName() 
    {
        return name;
    }
    public void setName(String name )
    {
    	this.name = name;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description )
    {
    	this.description = description;
    }

    public String getOwner() 
    {
        return owner;
    }
    public void setOwner(String owner )
    {
    	this.owner = owner;
    }

    public List<Job> getJobs()
    {
        return jobs;
    }
    public void setJobs(List<Job> newJobs) 
    {
        this.jobs = newJobs;
    }
    public void addJob(Job job) 
    {
        jobs.add(job);
    }
}
