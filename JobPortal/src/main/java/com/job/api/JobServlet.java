package com.job.api;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.job.models.Job;
import com.job.service.JobService;

public class JobServlet extends HttpServlet {
    private JobService jobService = new JobService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray jsonArray = new JSONArray();
        List<Job> jobs = jobService.getAllJobs();
        for (Job job : jobs) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("jobId", job.getJobId());
                jsonObject.put("userId", job.getUserId());
                jsonObject.put("title", job.getTitle());
                jsonObject.put("description", job.getDescription());
                jsonObject.put("location", job.getLocation());
                jsonObject.put("salary", job.getSalary());
                jsonObject.put("postDate", job.getPostDate().toString());
                jsonObject.put("expiryDate", job.getExpiryDate().toString());
                jsonObject.put("otherDetails", job.getOtherDetails());
                jsonArray.put(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonRequest.toString());
            Job job = new Job();
            job.setUserId(jsonObject.getInt("userId"));
            job.setTitle(jsonObject.getString("title"));
            job.setDescription(jsonObject.getString("description"));
            job.setLocation(jsonObject.getString("location"));
            job.setSalary(jsonObject.getDouble("salary"));
            job.setPostDate(Timestamp.valueOf(jsonObject.getString("postDate")));
            job.setExpiryDate(Date.valueOf(jsonObject.getString("expiryDate")));
            job.setOtherDetails(jsonObject.getString("otherDetails"));
            jobService.addJob(job);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format or error inserting job.");
        }
    }
}
