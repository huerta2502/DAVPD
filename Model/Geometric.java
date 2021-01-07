public class Geometric extends Davpd{
    private double p;
    public Geometric () { }
    public Geometric (String name, String description, String function, String cumulative, String mean, String variance, double p){
        super(name, description, function, cumulative, mean, variance);
        this.p = p;
    }
    public void setP(double p){ this.p = p; }
    public double getP(){ return p; }
}
