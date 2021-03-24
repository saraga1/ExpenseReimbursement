package dev.araga.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.log4j.Logger;


public class JwtUtil {

    // never let anuone know your secret
    private static final String secret ="super secret string noone else should ever have";
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);
    private static Logger logger = Logger.getLogger(JwtUtil.class.getName());
    // put in the paramets that you want to be in the body of your jwe,
    public static String generate(String role, String employeeName){
        // builder deseign pattern

       try {
           String token = JWT.create()
                   .withClaim("role", role) // add data to the payload
                   .withClaim("empName", employeeName)
                   .sign(algorithm); // this will generate a signture based off of those claims

           if (role != null) {
               if (role.equalsIgnoreCase("manager")) {
                   logger.assertLog(true, "Manager login was a success");
               } else {
                   logger.assertLog(true, "Employee login was a success");

               }
           }
           return token;
       }catch (Exception e){
           e.printStackTrace();
           logger.error("Attempt to login was unsuccessful");
           return null;
       }
    }

    public static DecodedJWT isValidJWT(String token){
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        return  jwt;
    }
}
