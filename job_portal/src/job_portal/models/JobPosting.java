package job_portal.models;

public class JobPosting 
{
	private String title;
    private String description;
    private String company;

    public JobPosting(String title, String description, String company) 
    {
        this.title = title;
        this.description = description;
        this.company = company;
    }

    public String gettitle()
    {
        return title;
    }

    public void settitle(String title) 
    {
        this.title = title;
    }

    public String getdescription() 
    {
        return description;
    }

    public void setdescription(String description) 
    {
        this.description = description;
    }

    public String getcompany()
    {
        return company;
    }

    public void setcompany(String company) 
    {
        this.company = company;
    }
}
