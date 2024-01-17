package edu.kirkwood.demos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject json = JsonReader.readJsonFromUrl("https://api.deezer.com/search/artist?q=imagine+dragons");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ArtistFromJson artistFromJson = mapper.readValue(json.toString(), ArtistFromJson.class);
        List<Artist> artists = artistFromJson.getArtists();
        req.setAttribute("artist", artists.get(0));
        req.getRequestDispatcher("WEB-INF/demos/artist.jsp").forward(req, resp);
    }
}
