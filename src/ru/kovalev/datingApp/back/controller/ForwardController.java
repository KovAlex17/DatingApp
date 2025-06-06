package ru.kovalev.datingApp.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ForwardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect("https://ya.ru"); // в редиректе - переходит браузер, в форварде - сервер сам знает куда перенаправлять и внутри себя перенаправляет
        req.getRequestDispatcher("/like").forward(req, resp); //Перенаправим на like
    }
}
