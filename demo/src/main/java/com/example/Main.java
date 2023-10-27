package com.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try{
            // Create a map for the variables
            Map<String, String> variables = new HashMap<>();
            variables.put("var1", "newvalue1");
            variables.put("var2", "newvalue2");

            // System.out.println(variables);

            // Convert the map to a JSON string using Gson
            Gson gson = new Gson();
            String jsonData = gson.toJson(variables);

            // Define the playbook path
            String playbookPath = "./rancher_deployment.yml";

            // Construct the command to execute ansible-playbook
            String command = String.format("ansible-playbook %s --extra-vars 'json_vars=%s'", playbookPath, jsonData);

            // Execute the command using ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", command);
            Process process = processBuilder.start();

            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}