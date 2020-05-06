/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package end;

import java.io.File;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("dirinfo")
public class DirinfoResource implements IDirectory {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DirinfoResource
     */
    public DirinfoResource() {
    }

    @Override
    @GET
    @Path("{directory}")
    @Produces("text/html")
    public String getContent(
            @PathParam("directory") String dirName) {
        
        File folder = new File(dirName);

        StringBuilder sb = new StringBuilder();
        for (File x : folder.listFiles()) {
            sb.append("<h2>")
                    .append(x.getName())
                    .append("<small> - ")
                    .append(x.isDirectory() ? "folder" : "file")
                    .append("</small></h2>");

        }
    
        return "<h1>Список файлов директории :"+dirName+" <br></h1>"+sb.toString();
    }

    @Override
    @GET
    @Path("find/{directory}")
    @Produces(MediaType.TEXT_HTML)
    public String findFile(
            @PathParam("directory") String dirName,
            @QueryParam("file") String fileName) {
        File folder = new File(dirName);
    StringBuilder sb = new StringBuilder();    
    for(File x:folder.listFiles(new FileFinder(fileName, false))){
        sb.append("<h2>")
                    .append(x.getName())
                    .append("<small> - ")
                    .append(x.isDirectory() ? "folder" : "file")
                    .append("</small></h2>");
        
    }    String absolutePath = folder.getAbsolutePath();
        return "<h1>Поиск совпадения в директории :"+dirName+" параметр поиска : "+fileName+"<br></h1>"+sb.toString();
    }
}
