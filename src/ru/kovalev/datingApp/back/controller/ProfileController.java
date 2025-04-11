package ru.kovalev.datingApp.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kovalev.datingApp.back.model.Profile;
import ru.kovalev.datingApp.back.service.ProfileService;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    private final ProfileService service = ProfileService.getInstance(); // В сервлете не может быть конструктора

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String forwardUri = "/notFound";

        if(id != null){
            Optional<Profile> optional =  service.findById(Long.parseLong(id));

            if(optional.isPresent()){

                req.setAttribute("profile", optional.get()); //поместили в атрибут profile наш профиль // это ассоц.массив, по ключу profile можем положить в него все что хотим // у всех параметров запросу и ответа есть атрибуты

                forwardUri = "/WEB-INF/jsp/profile.jsp";
            }
        }

        req.getRequestDispatcher(forwardUri).forward(req, resp);
    }
}
