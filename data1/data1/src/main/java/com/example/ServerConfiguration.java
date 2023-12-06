package com.example;

import org.apache.commons.configuration2.INIConfiguration;

import java.io.FileReader;
import java.util.HashMap;

public class ServerConfiguration {
    //znaci napravili smo fajl u projektnom folderu za server.
    private HashMap<String, String> configStrings = new HashMap<>();

    private void setInitConfigStrings(HashMap<String, String> configStrings) {
        this.configStrings = configStrings;
    }

    public String getStringProperty(String key) {
        return configStrings.get(key);
    }

    public static ServerConfiguration load(String path) {
        //znaci kad uradimo ovaj load on treba da nam ucita hashmapu
        try {
            ServerConfiguration cfg = new ServerConfiguration();
            //formiramo objekat cfg
            INIConfiguration iniconf = new INIConfiguration();
            //konfiguracija ini fajla
            iniconf.read(new FileReader(path));
            //procitamo i posaljemo path
            HashMap<String, String> properties = new HashMap<>();
            //slisno kao u INIParser idu ova dohvacanja podataka koji nas zanimaju
            properties.put("css_dir", iniconf.getSection("static_files").getProperty("css_dir").toString());
            properties.put("js_dir", iniconf.getSection("static_files").getProperty("js_dir").toString());
            properties.put("images_dir", iniconf.getSection("static_files").getProperty("images_dir").toString());
            properties.put("templates_dir", iniconf.getSection("templates").getProperty("root_dir").toString());
            cfg.setInitConfigStrings(properties);
            return cfg;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
