package com.turkcell.tcellfinalcore.services;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseJwtService {
    private long EXPIRATION = 600000;
    private String SECRET_KEY = "Ovcwy/oYoPYH9oKyl2P+VW2eEcDs+Q0/airucLh9aeG6Lj5JzYuvPHsozxljSCRqkIYzdW24oPX2JtQruIIJACcHRH87xq/TXEWOQVqwpUtwbuL3kWi6WBOsEGRgndZE7K8gMibx7ecI85QJHIAcepYvMYX/A/+lpJuuJ0Rx6KnjhsFJovqGPbxTNtHEWHcz7c62GmFzLP8mJVfpEAo4PMZyc75YX3VTX4RS/4ORnu98ywvtMi7wm2VH0YzeDabkg2QSO39XoMnH8Yux/gc39JOg4MyvCCD+1G5IHAuo9dYp7SH17G/rMe33UjgoyhvxoquRG6aGaFWNpdWTe+4TptBxEYlbuRCZBkdiwjwpm80=";
    public String generateToken(String username, Map<String, Object> extraClaims)
    {
        String token = Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .claims(extraClaims)
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    public String generateTokenWithClaims(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map((role) -> role.getAuthority())
                .toList();
        claims.put("roles", roles);
        return generateToken(userDetails.getUsername(), claims);
    }

    public Boolean validateToken(String token)
    {
        return getTokenClaims(token).getExpiration().after(new Date()); // Kendi ürettiğim token mı?
    }
    public String extractUsername(String token)
    {
        return getTokenClaims(token).getSubject();
    }

    public List<String> extractRoles(String token)
    {
        return getTokenClaims(token).get("roles", List.class);
    }

    private Claims getTokenClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSigningKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
