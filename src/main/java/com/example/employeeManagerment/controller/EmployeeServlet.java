package com.example.employeeManagerment.controller;

import com.example.employeeManagerment.dao.EmployeeDao;
import com.example.employeeManagerment.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;

    public void init() {
        this.employeeDao = new EmployeeDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if(action == null) {
            action = "/";
        }


        switch (action) {
            case "/new" :
                showAddForm(request, response);
                break;
            case "/insert":
                try{
                    insertEmployee(request,response);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/delete":
                try {
                    deleteEmployee(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/edit":
                try {
                    showEditForm(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/update":
                try {
                    updateEmployee(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
//            case "/search":
//                try {
//                    searchEmployee(request,response);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            break;
            default:
                try {
                    listEmployee(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = employeeDao.selectAllEmployees();
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    private void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException,ServletException{
        String search = request.getParameter("search");
        List<Employee> listEmployee = employeeDao.searchEmployee("search");
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee existingUser = employeeDao.selectEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee.jsp");
        request.setAttribute("employee", existingUser);
        dispatcher.forward(request,response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String fullName = request.getParameter("fullName");
        String birthDay = request.getParameter("birthDay");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        Employee newEmployee = new Employee(fullName,birthDay,address,position,department);
        employeeDao.insertEmployee(newEmployee);
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDao.deleteEmployee(id);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String birthDay = request.getParameter("birthDay");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        String department = request.getParameter("department");

        Employee employee = new Employee(id, fullName, birthDay,address,position,department);
        employeeDao.updateEmployee(employee);
        response.sendRedirect("list");
    }
}
