package projekt;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by bartosz on 30.10.15.
 *
 */


public class Main {
    private static HashMap<String, Lotnisko> lotniska;
//    private static LinkedList<Drogowskaz> drog;
//    private static void wczytaj() throws FileNotFoundException {
//        Scanner sc = new Scanner(new FileReader("miasta.txt"));
//        while (sc.hasNext()){
//            Lotnisko l = new Lotnisko(
//                    sc.nextInt(),
//                    sc.nextInt(),
//                    sc.next(),
//                    null,
//                    sc.nextInt(),
//                    sc.next().equals("C") ? TypPortu.WOJSKOWY : TypPortu.CYWILNY);
//            getLok().put(l.getPolozenie().getX() + "_" + l.getPolozenie().getY(), l);
//        }
//    }

    public static HashMap<String, Lotnisko> getLotniska() {
        return lotniska;
    }

    public static void main(String[] args) throws FileNotFoundException {
        lotniska = new HashMap<String, Lotnisko>();
        lotniska.put("80_50", new Lotnisko(80, 50, "Zielone", 5, TypPortu.CYWILNY));
        lotniska.put("50_90", new Lotnisko(50, 90, "Czerwone", 5, TypPortu.CYWILNY));
        Lotnisko l1 = new Lotnisko(10, 20, "Niebieskie", 5, TypPortu.CYWILNY);
        lotniska.put("10_20", l1);
        l1.dodajDrogowskaz(new Drogowskaz(70, 50, 90, Kierunek.PRAWO));
//        getLok().put("10_20", l1);

//        Skrzyzowanie sk1 = new Skrzyzowanie(50, 20, "SK1");
//        sk1.dodajDrogowskaz(new Drogowskaz(40, 10, 20, Kierunek.LEWO));
//        lotniska.put("50_20",sk1);


        System.out.println("Utworzone lokalizacje: ");
        for (Lokalizacja l : lotniska.values()){
            System.out.println(l.getNazwa());
        }

//        LinkedList<Lotnisko> trasa = new LinkedList<Lotnisko>();
//        for(Lotnisko l : getLotniska().values()){
//
//        }

//        SamolotWojskowy sw1 = new SamolotWojskowy(l1.getPolozenie(),2,l1,new LinkedList<Lokalizacja>(lotniska.values()));
//
//        sw1.przemiescSie();
//        sw1.przemiescSie();
//        sw1.przemiescSie();
//        for(int i=0; i<200; i++) sw1.przemiescSie();

//        System.out.println(sw1.getPolozenie().getX() + " " + sw1.getPolozenie().getY());

    }


}