package com.codegym.thith_md3.controller;

import com.codegym.thith_md3.model.Degree;
import com.codegym.thith_md3.model.EGender;
import com.codegym.thith_md3.model.Teacher;
import com.codegym.thith_md3.service.DegreeService;
import com.codegym.thith_md3.service.IDegreeService;
import com.codegym.thith_md3.service.ITeacherService;
import com.codegym.thith_md3.service.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "TeacherServlet", urlPatterns = "/teacher")
public class TeacherServlet extends HttpServlet {
    private ITeacherService teacherService = new TeacherService();
    private IDegreeService degreeService = new DegreeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(req, resp);
                break;                  // LUÔN NHỚ Có BREAK
            case "edit":
                showEdit(req, resp);
                break;
            case "delete":
                deleteTeacher(req, resp);
                break;
            default:
                showList(req, resp);

        }
    }

    private void deleteTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        teacherService.remove(id);

        req.getSession().setAttribute("messageDelete", "Xóa thành công");
        resp.sendRedirect("/teacher");
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idteacher =Long.parseLong(req.getParameter("id"));

        Teacher teacher = teacherService.findById(idteacher);
        req.setAttribute("teacher", teacher);

        List<Degree> degrees = degreeService.findAll();

        req.setAttribute("degrees", degrees);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/teacher/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Degree> degrees = degreeService.findAll();

        req.setAttribute("degrees", degrees);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/teacher/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teacherList = teacherService.findAll();


        req.setAttribute("teachers", teacherList);
        List<Degree> degrees = degreeService.findAll();
        req.setAttribute("degrees", degrees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/teacher/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                saveTeacher(req, resp);
                break;
            case "edit":
                updateTeacher(req, resp);
                break;
        }
    }

    private void updateTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = new Teacher();
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String hobbie = req.getParameter("hobbie");
        String DateStr = req.getParameter("date");
        LocalDate Date = LocalDate.parse(DateStr);
        String gender = req.getParameter("gender");
        EGender eGender = EGender.valueOf(gender);
        long idDegree = Long.parseLong(req.getParameter("degree"));
        Degree degree = degreeService.findById(idDegree);
        teacher.setId(id);
        teacher.setName(name);
        teacher.setDateOfBirth(Date);
        teacher.setHobbie(hobbie);
        teacher.setGender(eGender);
        teacher.setDegree(degree);

        List<Degree> degrees = degreeService.findAll();
        req.setAttribute("degrees", degrees);
        req.setAttribute("teacher", teacher);
        teacherService.update(teacher.getId(), teacher);
        req.getSession().setAttribute("messageEdit", "Sửa thành công");
        resp.sendRedirect("/teacher");
    }

    private void saveTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = new Teacher();
        String name = req.getParameter("name");
        String hobbie = req.getParameter("hobbie");
        String DateStr = req.getParameter("date");
        LocalDate Date = LocalDate.parse(DateStr);
        String gender = req.getParameter("gender");
        EGender eGender = EGender.valueOf(gender);
        long idDegree = Long.parseLong(req.getParameter("degree"));
        Degree degree = degreeService.findById(idDegree);
        teacher.setName(name);
        teacher.setDateOfBirth(Date);
        teacher.setHobbie(hobbie);
        teacher.setGender(eGender);
        teacher.setDegree(degree);

        List<Degree> degrees = degreeService.findAll();
        req.setAttribute("degrees", degrees);
        req.setAttribute("teacher", teacher);
        req.setAttribute("massage", "them thanh cong");
        teacherService.save(teacher);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/teacher/create.jsp");
        requestDispatcher.forward(req, resp);



    }
}
