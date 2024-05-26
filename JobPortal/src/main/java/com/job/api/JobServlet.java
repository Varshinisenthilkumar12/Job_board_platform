package com.job.api;
import com.job.models.Jobs;
import com.job.service.JobService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class JobServlet extends HttpServlet {
    private JobService jobService;
    public void init() throws ServletException {
        jobService = new JobService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jobIdParam = req.getParameter("jobId");
        try {
        if (jobIdParam == null) {
            List<Jobs> jobsList = jobService.getAllJobs();
            JSONArray jobsJsonArray = new JSONArray();

            for (Jobs job : jobsList) {
                JSONObject jobJson = new JSONObject();
                jobJson.put("jobId", job.getJobId());
                jobJson.put("employerId", job.getEmployerId());
                jobJson.put("title", job.getTitle());
                jobJson.put("description", job.getDescription());
                jobJson.put("location", job.getLocation());
                jobJson.put("salary", job.getSalary());
				jobJson.put("contactEmail", job.getContactEmail());
                jobsJsonArray.put(jobJson);
            }

            resp.setContentType("application/json");
            resp.getWriter().write(jobsJsonArray.toString());
        } else {
            int jobId = Integer.parseInt(jobIdParam);
            Jobs job = jobService.getJobById(jobId);

            if (job != null) {
                JSONObject jobJson = new JSONObject();
                jobJson.put("jobId", job.getJobId());
                jobJson.put("employerId", job.getEmployerId());
                jobJson.put("title", job.getTitle());
                jobJson.put("description", job.getDescription());
                jobJson.put("location", job.getLocation());
                jobJson.put("salary", job.getSalary());
                jobJson.put("contactEmail", job.getContactEmail());

                resp.setContentType("application/json");
                resp.getWriter().write(jobJson.toString());
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"message\": \"Job not found\"}");
            }
        }} catch (JSONException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        try {
        JSONObject jobJson = new JSONObject(sb.toString());
        Jobs job = new Jobs();
        job.setEmployerId(jobJson.getInt("employerId"));
        job.setTitle(jobJson.getString("title"));
        job.setDescription(jobJson.getString("description"));
        job.setLocation(jobJson.getString("location"));
        job.setSalary(jobJson.getDouble("salary"));
		job.setContactEmail(jobJson.getString("contactEmail"));

        boolean isCreated = jobService.createJob(job);
        if (isCreated) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\": \"Job created successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\": \"Job creation failed\"}");
        }} catch (JSONException e) {
			e.printStackTrace();
		}
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        try {
        JSONObject jobJson = new JSONObject(sb.toString());
        Jobs job = new Jobs();
        job.setJobId(jobJson.getInt("JobId"));
        job.setEmployerId(jobJson.getInt("employerId"));
        job.setTitle(jobJson.getString("title"));
        job.setDescription(jobJson.getString("description"));
        job.setLocation(jobJson.getString("location"));
        job.setSalary(jobJson.getDouble("salary"));
		job.setContactEmail(jobJson.getString("contactEmail"));

        boolean isUpdated = jobService.updateJob(job);
        if (isUpdated) {
            resp.getWriter().write("{\"message\": \"Job updated successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\": \"Job update failed\"}");
        }
        }catch (JSONException e) {
			e.printStackTrace();
		}
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jobIdParam = req.getParameter("jobId");

        if (jobIdParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\": \"Job ID is required\"}");
        } else {
            int jobId = Integer.parseInt(jobIdParam);
            boolean isDeleted = jobService.deleteJob(jobId);
            if (isDeleted) {
                resp.getWriter().write("{\"message\": \"Job deleted successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"message\": \"Job deletion failed\"}");
            }
        }
    }
}