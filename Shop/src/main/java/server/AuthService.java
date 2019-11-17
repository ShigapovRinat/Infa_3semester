package server;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;

import java.io.IOException;
import java.util.Map;


public class AuthService {

    public static String getToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withSubject(user.getLogin())
                .withClaim("role", user.getRole())
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException exception) {
            System.out.println("The token doesn't right");
            return null;
        }
    }


    public static boolean isAdmin(String tokenStr) {
        DecodedJWT jwt = verifyToken(tokenStr);
        Map<String, Claim> claims = jwt.getClaims();
        Claim claim = claims.get("role");
        return claim.asInt() == 2;
    }

    public static String getLogin(String tokenStr){
        DecodedJWT jwt = verifyToken(tokenStr);
        return jwt.getSubject();
    }


}
