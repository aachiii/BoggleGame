package p2;
import java.util.ArrayList;

public class example_driver {

    public static void main(String[] args) {
        c_boggle my_boggle = new c_boggle();
        ArrayList<String> my_results;

        //I use a very big dictionary to build the trie, the words can be found at /words.txt
        my_boggle.set_legal_words();

        //test case 1
        my_results = my_boggle.solve_board(3, 3, "yoxrbaved");
        System.out.println("Result Numbers: " + my_results.size());
        System.out.println("Results: " + my_results);
        System.out.println("");

        /*
            Board "yoxbaved" returns 119 results which is different than the programming test sample for the reason of
            my large word set.

            Results:
            [yob, yox, yor, yore, obe, obed, ober, obd, oba, obad, oby, obv, oad, orv, ore,
            ored, oread, orb, orbed, orby, ory, xor, red, redby, rea, read, reb, reba, rebox,
             rev, rbe, rob, robe, robed, roby, roa, road, rox, roy, bed, bea, bead, ber, bev,
             bda, bde, bad, bade, bax, bao, baor, bae, baed, baer, boa, box, boy, bor, bore,
             bored, boread, byre, byroad, byo, bre, bred, breda, brea, bread, brev, bro, broad,
             bve, adb, ade, ader, aob, aor, abe, abed, abd, abo, abox, aby, abr, abv, aer, aero,
             aery, veda, ver, verb, verby, very, eda, edb, ead, eba, ebro, erv, erb, ery, dax,
             dao, dab, dae, daer, dbe, dba, dbo, dbv, dea, deb, debor, der, derv, derby, dero, dev]
        */


        //test case 2
        ArrayList<String> my_results_2;
        my_results_2 = my_boggle.solve_board(3, 2, "doxima");
        System.out.println("Result Numbers: " + my_results_2.size());
        System.out.println("Results: " + my_results_2);

        /*
            Board "doxima" returns 39 results

            Results:
            [dim, dma, dmi, dom, doa, doxa, doi, oma, omd, oam, oxamid, odi, odm, oid, xmi, ima,
            imo, imd, iom, iod, idm, ido, max, mao, moa, moxa, mod, modi, moi, moid, mdi, mio, mid,
            axoid, amo, amd, ami, amid, amido]
        */



    }

}
