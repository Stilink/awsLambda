package edu.eci.arep;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import spark.Response;
import spark.Request;

public class SparkServiceArep {

    public static void main(String[] args) {
        port(1111);
        get("/form", (req, res) -> form(req, res));
        get("/square", (req, res) -> result(req, res));

    }

    private static String result(Request req, Response res) {
        int n = Integer.parseInt(req.queryParams("value"));
        URL url = null;
        try {
            url = new URL("https://kls3n59oc8.execute-api.us-east-1.amazonaws.com/Beta" + "?value=" + n);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String out = "falle";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            

            out = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return out;
    }

    private static String form(Request req, Response res) {
        String pageContent = "<!DOCTYPE html>" + "<html>" + "<body>" + "<form action=\"/square\">" + "  Numero:<br>"
                + "  <input type=\"text\" name=\"value\"" + "  <br>"
                + "  <input type=\"submit\" value=\"Submit\">" + "</form>" + "</body>" + "</html>";
        return pageContent;
    }
}