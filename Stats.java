import src.Compression;
import java.util.*;
import src.Hash;
import src.HashCompare;

public class Stats {
    public static void main(String[] args) {
        long tmptotalrech= 0;
        long tmptotalgen= 0;
        int compt = 0;
        ArrayList<String> couleur = new ArrayList<String>();
        couleur.add("Blue");
        couleur.add("Yellow");
        couleur.add("Purple");
        for(int i = 0;i<1;i=i+1){
                long startTime = System.currentTimeMillis();
                Compression comp = new Compression(1000,couleur,10000);
                comp.comprimer();
                long endTime = System.currentTimeMillis();
                tmptotalgen = tmptotalgen + (endTime-startTime);
                Hash h = new Hash("fzr-%Q?2W'");
                HashCompare tmp = new HashCompare(h.creeHash());
                startTime = System.currentTimeMillis();
                tmp.rechercheMdp();
                endTime = System.currentTimeMillis();
                tmptotalrech = tmptotalrech + (endTime-startTime);
                compt = compt + 1;
        }
        System.out.println("temps de compression");
        System.out.println(tmptotalgen/compt+"ms");
        System.out.println((tmptotalgen/compt)/1000+"s");
        System.out.println(((tmptotalgen/compt)/1000)/60+"min");
        System.out.println("temps de recherche");
        System.out.println(tmptotalrech/compt+"ms");
        System.out.println((tmptotalrech/compt)/1000+"s");
        System.out.println(((tmptotalrech/compt)/1000)/60+"min");
        System.out.println("ratio tmprech/tmpgen : " + (tmptotalrech/tmptotalgen));
    }
}