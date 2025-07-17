package uz.jahonservice.crmdemo.service.auth;

import com.auth0.jwt.JWT;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.jahonservice.crmdemo.exception.MyException;

import java.security.Key;
import java.util.Date;

@Component
public class JWTService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.access.expiry}")
    private Long accessTokenExpiry;

    @Value("${jwt.refresh.expiry}")
    private Long refreshTokenExpiry;

    public String generateAccessToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withClaim("checkerAccessToken", "check access token")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + accessTokenExpiry))
                .withIssuer("CRM-demo by JAKHONGIR JURAQULOV")
                .sign(Algorithm.HMAC256(secretKey));
    }

//    public String generateRefreshToken(String username) {
//        return JWT.create()
//                .withSubject(username)
//                .withClaim("")
//    }


    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                    .withClaim("checkerAccessToken", "check access token")
                    .withIssuer("CRM-demo by JAKHONGIR JURAQULOV")
                    .build();
            DecodedJWT jwt = verifier.verify(token);


            return jwt.getSubject();
        } catch (JWTVerificationException e) {
            throw new MyException("Token is not valid" + e.getMessage());
        }
    }

}
