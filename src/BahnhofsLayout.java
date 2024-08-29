public class BahnhofsLayout {
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
    public int[][] layout;
    public BahnhofsLayout(int[][] layout){
        this.layout = layout;
    }
    public BahnhofsLayout(){}

    public void setLayout(int[][] layout) {
        this.layout = layout;
    }
}