public class BahnhofsLayoutManager {
    static BahnhofsLayout ZweiGleiseDreiZiele = new BahnhofsLayout();
    static BahnhofsLayout DreiGleiseZweiZiele = new BahnhofsLayout();
    static BahnhofsLayout VierGleiseVierZiele = new BahnhofsLayout();
    static BahnhofsLayout VierGleiseDreiZiele = new BahnhofsLayout();
    static BahnhofsLayout DreiGleiseDreiZiele = new BahnhofsLayout();
//    westost-gleis: 1
//    nordsüd-gleis: 2
//    nordost-weiche: 3
//    nordwest-weiche: 4
//    südost-weiche: 5
//    südwest-weiche: 6
//      Bahnhofslayout ist in einem 10*10 layout
//      designed. Zahlen geben die Tiles an, welche
//      dann als 2D-Array verschickt werden, um im Frontend
//      gezeichnet werden können.
    public static void setLayout(){
        int[][] zwGleisedrZiele = {
                {0,0,2,0,0,0,0,0,0,0},
                {0,0,2,0,0,0,0,0,0,0},
                {0,0,2,0,0,0,0,0,0,0},
                {1,1,3,6,1,1,5,1,1,1},
                {0,0,0,2,0,0,2,0,0,0},
                {0,1,1,3,1,1,4,1,1,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };
        ZweiGleiseDreiZiele.setLayout(zwGleisedrZiele);
    }


}
