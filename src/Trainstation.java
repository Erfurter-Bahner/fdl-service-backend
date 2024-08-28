public class Trainstation {
    public String name;
    public int gleise;
    public String art;

    public Trainstation[] destinations;
    public Trainstation(String name, int gleise, String art, Trainstation[] destinations){
        this.name = name;
        this.gleise = gleise;
        this.art = art;
        this.destinations = destinations;
    }
    public Trainstation(String name, int gleise, String art){
        this.name = name;
        this.gleise = gleise;
        this.art = art;
        this.destinations = null;
    }

    public void setDestinations(Trainstation[] destinations) {
        this.destinations = destinations;
    }
}
