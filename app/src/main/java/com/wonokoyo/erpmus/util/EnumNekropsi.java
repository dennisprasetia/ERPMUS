package com.wonokoyo.erpmus.util;

import com.wonokoyo.erpmus.classes.Nekropsi;

import java.util.ArrayList;
import java.util.List;

public class EnumNekropsi {
    public final static List<String> listStringNekropsi() {
        List<String> list = new ArrayList<>();
        list.add("Conjungtivitis");
        list.add("Trachea radang");
        list.add("Airsaculitis");
        list.add("Hidropericard");
        list.add("Pengapuran pada hati");
        list.add("Perkejuan di jantung");
        list.add("Perkejuan di rongga perut");
        list.add("Selaput hati keruh");
        list.add("Usus entritis");
        list.add("Cecum radang");
        list.add("Gizard erois");
        list.add("Bursal radang");
        list.add("Ginjal bengkok (ayam mati)");
        list.add("Gout");
        list.add("Pecah pembuluh darah ke arah malaria");
        list.add("Kotoran putih di kloaka");
        list.add("kotoran hijau di kloaka");
        list.add("Kualitas litter dan kadar amonia");

        return list;
    }

    public final static List<Nekropsi> listNekropsi() {
        List<Nekropsi> list = new ArrayList<>();

        for (int a = 0; a < listStringNekropsi().size(); a++) {
            Nekropsi nekropsi = new Nekropsi();
            nekropsi.setNama(listStringNekropsi().get(a));
            nekropsi.setStatus(0);
            nekropsi.setKeterangan("");

            list.add(nekropsi);
        }

        return list;
    }
}
