package com.example.api_course_producer.service.token;

import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
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
public class jwtService {

    @Value("${application.security.jwt.secret_key}")
    private String secretKey ;

    public String generateAccessTokenForTPS(ThirdParty_Course thirdParty_course){
        return generateAccessTokenForTPS(new HashMap<>(),thirdParty_course);
    }

    public String generateAccessTokenForTPS(Map<String,Object> claims,ThirdParty_Course thirdParty_course){
        return jwtBuilder(claims,thirdParty_course);
    }


    public String jwtBuilder(Map<String,Object> claims, ThirdParty_Course thirdParty_course){
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(thirdParty_course.getStartDate())
                .setExpiration(thirdParty_course.getEndDate())
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


    public boolean isTokenExpried(String token) {
        return extractExpiration(token).before(new Date());
    }


}