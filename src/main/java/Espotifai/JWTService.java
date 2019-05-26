/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Espotifai;

import Models.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cristian
 */
public class JWTService {

    private static final String SECRET_KEY = "123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678";

    public static String getJWTTokenForUser(User user) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Date expirationDate = Utils.addSeconds(Calendar.getInstance().getTime(), 3600);
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", Long.toString(user.getId()));
        
        return Jwts.builder().setSubject("").signWith(key)
                .setClaims(claims)
                .setExpiration(expirationDate)
                .compact();
    }

    public static String decodeJWTToken(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody().get("user_id").toString();
        } catch (JwtException e) {
            throw e;
        }
    }
}
