import java.util.ArrayList;
import java.util.List;

public class TrainstationManager {
    public static Trainstation grabsleben = new Trainstation("Grabsleben",2,"Haltepunkt");
    public static Trainstation huemme = new Trainstation("Hümme",4,"Haltestelle");
    public static Trainstation oberkaka = new Trainstation("Oberkaka",5,"Bahnhof");
    public static Trainstation unterkaka = new Trainstation("Unterkaka",2,"Haltepunkt");
    public static Trainstation rixen = new Trainstation("Rixen",7,"Bahnhof");
    public static Trainstation unterndorf = new Trainstation("Unterndorf",3,"Bahnhof");
    public static Trainstation oberstdorf = new Trainstation("Oberstdorf",4,"Bahnhof");
    public static Trainstation erfurt = new Trainstation("Erfurt",6,"Bahnhof");
    public static Trainstation querfurt = new Trainstation("Querfurt",3,"Bahnhof");
    public static Trainstation[] stationlist = new Trainstation[]{grabsleben,huemme,oberkaka,unterndorf,unterkaka,oberstdorf,rixen,erfurt,querfurt};

    public static Boolean[] taken = new Boolean[]{false, false, false ,false, false, false, false, false, false};
    public void generateStations(){
        grabsleben.setDestinations(new Trainstation[]{huemme,oberkaka, erfurt});
        huemme.setDestinations(new Trainstation[]{grabsleben,querfurt});
        oberkaka.setDestinations(new Trainstation[]{grabsleben,unterkaka,rixen});
        unterkaka.setDestinations(new Trainstation[]{oberkaka,unterndorf,rixen});
        rixen.setDestinations(new Trainstation[]{erfurt, querfurt, oberkaka, unterkaka});
        unterndorf.setDestinations(new Trainstation[]{unterkaka,oberstdorf,erfurt});
        oberstdorf.setDestinations(new Trainstation[]{unterndorf,erfurt,querfurt});
        erfurt.setDestinations(new Trainstation[]{grabsleben,rixen,unterndorf,oberstdorf});
        querfurt.setDestinations(new Trainstation[]{huemme, rixen, oberstdorf});
    }
    public static Trainstation[] getStationlist() {
        return stationlist;
    }

    public Trainstation getStation() {
        for(int i=0;i<taken.length;i++){
            if(!taken[i]){
                taken[i] = true;
                return stationlist[i];
            }
        }
        return null;
    }
}
