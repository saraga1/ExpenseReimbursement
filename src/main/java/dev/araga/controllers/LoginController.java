package dev.araga.controllers;

import dev.araga.utils.JwtUtil;
import io.javalin.http.Handler;

public class LoginController {

    public Handler loginHandler = (ctx) -> {
        String jwt = JwtUtil.generate("Manager","");
        ctx.result(jwt);
    };

    public Handler loginEmpHandler = (ctx) -> {
        String jwt = JwtUtil.generate("Employee","");
        ctx.result(jwt);
    };
}
