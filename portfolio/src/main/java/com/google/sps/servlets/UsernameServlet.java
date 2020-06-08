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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/username")
public class UsernameServlet extends HttpServlet {

  public static final String USERNAME = "username";
  public static final String TABLE_NAME = "Messages";
  public static final String ID = "id";

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      UserService userService = UserServiceFactory.getUserService();
      //TO-DO: 
      if (!userService.isUserLoggedIn()) {
          response.sendRedirect("/index.html");
          return;
      }

      String username = request.getParameter(USERNAME);
      String id = userService.getCurrentUser().getUserId();

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      
      Entity entity = new Entity(TABLE_NAME, id);
      entity.setProperty(ID, id);
      entity.setProperty(USERNAME, username);
      datastore.put(entity);

      response.sendRedirect("/index.html");
  }
}