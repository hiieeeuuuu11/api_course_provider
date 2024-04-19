package com.example.api_course_producer.service.token;

import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import com.example.api_course_producer.model.user.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret_key}")
    private String secretKey ;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    public String generateAccessTokenForTPS(ThirdParty_Course thirdParty_course){
        return generateAccessTokenForTPS(new HashMap<>(),thirdParty_course);
    }

    public String generateToken(AppUser user){
        return generateToken(new HashMap<>(),user);
    }

    public String generateAccessTokenForTPS(Map<String,Object> claims,ThirdParty_Course thirdParty_course){
        return jwtBuilder(claims,thirdParty_course);
    }

    public String generateToken(Map<String,Object> claims, AppUser user){
        return jwtBuilder(claims,user,jwtExpiration);
    }

    public String jwtBuilder(Map<String,Object> claims, ThirdParty_Course thirdParty_course){
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(thirdParty_course.getStartDate())
                .setExpiration(thirdParty_course.getEndDate())
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public String jwtBuilder(Map<String,Object> claims, AppUser user, long expiration){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public int extractCourseId(String token){
        return extractClaim(token).get("course",Integer.class);
    }

    public int extractTPAId(String token){
        return extractClaim(token).get("tpa",Integer.class);
    }

    public Date extractExpiration(String token){
        Function<Claims,Date> extract = Claims::getExpiration;
        return extract.apply(extractClaim(token));
    }

    public String extractUsername(String token){
        Function<Claims,String> extract = Claims::getSubject;
        return extract.apply(extractClaim(token));
    }

    public boolean isTokenValid(String token,AppUser user){
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpried(token);
    }

    public boolean isTokenExpried(String token) {
        return extractExpiration(token).before(new Date());
    }


}