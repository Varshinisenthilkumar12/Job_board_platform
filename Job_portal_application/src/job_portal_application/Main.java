package job_portal_application;

import java.util.List;

import job_portal_application.models.Job;
import job_portal_application.models.Organization;
import job_portal_application.models.User;
import services.AuthenticationService;
import services.JobService;
import services.OrganizationService;

public class Main {

    public static void main(String[] args) 
    {
        AuthenticationService authService = new AuthenticationService();
        OrganizationService orgService = new OrganizationService();
        JobService jobService = new JobService();

        if (!authService.registerUser("Varshini", "Varsh@12", "employer")) 
        {
            System.out.println("Failed to register employer.");
            return;
        }
        if (!authService.registerUser("Janu", "28@Janu", "jobseeker")) 
        {
            System.out.println("Failed to register jobseeker.");
            return;
        }

        User employer = authService.login("Varshini", "Varsh@12");
        if (employer == null)
        {
            System.out.println("Failed to login as employer.");
            return;
        }

        Organization organization = orgService.createOrganization(employer, "ZOHO", "Software Development Company");
        if (organization == null)
        {
            System.out.println("Failed to create organization.");
            return;
        }

        orgService.addJob(organization, "Java Developer", "Job description", "Java, Spring, Hibernate");

        List<Job> allJobs = jobService.getJobsByOrganization(organization);

        System.out.println("All Jobs:");
        for (Job job : allJobs) 
        {
            System.out.println(job.getTitle());
        }

        List<Job> filteredJobs = jobService.filterJobsByTitle(allJobs, "Java");
        System.out.println("\nFiltered Jobs:");
        for (Job job : filteredJobs)
        {
            System.out.println(job.getTitle());
        }
    }
}
