import java.util.Date;

public class SimTime extends Thread{
    int sekunden = 0;
    int minuten = 0;
    int stunden = 0;
    int tage = 0;
    @Override
    public void run(){
        while(true){
        sekunden++;
        if(sekunden==60){
            sekunden = 0;
            minuten++;
        }
        if(minuten==60){
            minuten = 0;
            stunden++;
        }
        if(stunden==24){
            stunden = 0;
            tage++;
        }
            try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        }
    }
    public String getTime(){
        return tage+":"+stunden+":"+minuten+":"+sekunden;
    }
}
