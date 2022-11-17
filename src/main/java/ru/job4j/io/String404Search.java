package ru.job4j.io;


public class String404Search {
    public static void main(String[] args) {
        String strOK = " ABC 404 454354 4567 rrrrr 404 905";
        String strNG = " ABC 404 454354 4567 rrrrr 4444 uuutr 905";
        String strOK2 = " ABC 404 4543544567rrrrr4444uuutr905";
        String strOK3 = " ABC 404 4543544567rrrrr4444uuutr905 404 ";

        System.out.println("ok line LASTindex of 404: " + strOK.lastIndexOf("404"));
        System.out.println("ok line LASTindex of DASH: " + strOK.lastIndexOf(" "));
        System.out.println("ok line lenght " + strOK.length());

        System.out.println("*******");
        System.out.println("NG line LASTindex of 404:  " +  strNG.lastIndexOf("404"));
        System.out.println("NG line LASTindex of DASH "  + strNG.lastIndexOf(" "));
        System.out.println(strNG.length());
        System.out.println("END");

        System.out.println("OK2 line LASTindex of 404: " + strOK2.lastIndexOf("404"));
        System.out.println("OK2 line LASTindex of DASH: " + strOK2.lastIndexOf(" "));

        System.out.println("END2");

        System.out.println("OK3 line LASTindex of 404: " + strOK3.lastIndexOf("404"));
        System.out.println("OK3 line LASTindex of DASH: " + strOK3.lastIndexOf(" "));


    }
}
