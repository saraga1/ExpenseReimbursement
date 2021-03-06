package dev.araga.utiltests;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.araga.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtTests {

    @Test
    void creates_jwt(){
        String jwt = JwtUtil.generate("employee","Mary Sue");
        System.out.println(jwt);
    }

    @Test
    void creates_jwt_manager(){
        String jwt = JwtUtil.generate("Manager","Linda Wright");
        System.out.println(jwt);
    }

    @Test
    void decode_jwt(){
        DecodedJWT jwt = JwtUtil.isValidJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiTWFuYWdlciIsImVtcE5hbWUiOiJMaW5kYSBXcmlnaHQifQ.19h28fh6Clu1cFsh5_UOkO-ZoSqcoWNADOwwsOPLcvc");
        String role = jwt.getClaim("role").asString();
        System.out.println(role);
    }

}
