package com.example;

public class Main {
    public static void main(String[] args) {
        OsobaParser parser = new YAMLParser(); //ovde je prvo bio JSONParser() pa CSVParser pa INIParser pa onda ovaj YAMLParser,Coko je prepravljao
        Osoba osoba = parser.parse(System.getenv("JAVA_RESOURCES") + "/data1/person.yml");
        System.out.println(osoba);

        ServerConfiguration conf = ServerConfiguration.load(System.getenv("JAVA_RESOURCES") + "/server.ini");
        System.out.println(conf.getStringProperty("css_dir"));
    }
}
