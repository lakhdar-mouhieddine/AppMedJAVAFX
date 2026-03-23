public abstract class RDV {
    private String heure;
    private String date;
    private String observation;

    public RDV(String heure, String date) {
        this.heure = heure;
        this.date = date;
        
    }

    public void updateHeure(String newHeure) {
        this.heure = newHeure;
    }

    public void updateObservation(String newObservation) {
        this.observation = newObservation;
    }

    public String getRDV() {
        return "Date: " + date + ", Heure: " + heure + ", Observation: " + observation;
    }

    public void addObservation(String observation) {
        this.observation = observation;
    }

    public String getHeure() {
        return heure;
    }

    public String getFin(){
        //TODO
        return heure;
    }

    public String getDate() {
        return date;
    }

    
}
