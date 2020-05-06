/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package end;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author user
 */
public class FileFinder implements FilenameFilter{

    private String fragment="";
    private boolean regex=false;

    public FileFinder(String fragment, boolean regex) {
        this.fragment = fragment.toLowerCase();
        this.regex = regex;
    }
    
    
    @Override
    public boolean accept(File dir, String name) {
        if(regex){
          return name.toLowerCase().matches(fragment);
        }else {
            return name.toLowerCase().contains(fragment);
        }
    
    }
    
}
