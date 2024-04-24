package job_portal_application.models;

public class Job 
{
	 private String title;
	    private String description;
	    private String requirements;
	    private Organization organization;

	    public Job(String title, String description, String requirements, Organization organization) 
	    {
	        this.title = title;
	        this.description = description;
	        this.requirements = requirements;
	        this.organization = organization;
	    }

	    public String getTitle() 
	    {
	        return title;
	    }
	    public void setTitle(String title )
	    {
	    	this.title = title;
	    }

	    public String getDescription() 
	    {
	        return description;
	    }
	    public void setDescription(String description )
	    {
	    	this.description = description;
	    }

	    public String getRequirements()
	    {
	        return requirements;
	    }
	    public void setRequirements(String requirements )
	    {
	    	this.requirements = requirements;
	    }

	    public Organization getOrganization() 
	    {
	        return organization;
	    }
	    public void setOrganization(Organization organization )
	    {
	    	this.organization = organization;
	    }
	}
