// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private String hello;
  private ArrayList<String> messages = new ArrayList<String>();

  public void init() {
    messages.add("First message");
    messages.add("Second message!!~~");
    messages.add("Third message!@#$%^&");
    hello = "Hello Kofi!";
  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    String json = convertToJson(messages);

    response.setContentType("text/html;");
    response.getWriter().println("<h1>Hello world!</h1>");
    response.getWriter().println("<body>"+json+"</body>");
  }

  private String convertToJson(ArrayList<String> arrayList) {
    String json = "{ \"message\" :";
    json += "\""+arrayList.get(0)+"\""+", ";
    json += "\"message\" : "+"\""+arrayList.get(1)+"\"" + ", ";
    json += "\"message\" : "+"\""+arrayList.get(2)+"\""+" }";

    return json;
  }
}
