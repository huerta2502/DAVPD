public class Poisson extends Davpd{
    private double lamda;
    public Poisson () {
        super("images/poisson.jpg", "texts/Poisson.txt");
    }
    public Poisson (double lamda){
        this();
        this.lamda = lamda;
    }
    public void setLamda(double lamda){ this.lamda = lamda; }
    public double getLamda(){ return lamda; }
    @Override
    public double getValue(){
        return Math.exp(-lamda) * Math.pow(lamda, var + 0.0) / CountingTechniques.factorial(var + 0.0);
    }
    @Override
    public double getCumulativeValue(){
        cumulativeValue = 0.0;
        int tmp = var;
        for(int i = 0; i < tmp; i++){
            var = i;
            cumulativeValue += getValue();
        }
        var = tmp;
        return cumulativeValue;
    }
    @Override
    public double getMeanValue(){ 
        return lamda;
    }
    @Override
    public double getVarianceValue(){
        return lamda;
    }
    @Override
    public String toString() {
        return String.format("X ~ P(x = %d; lamda = %d)", var, lamda);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Poisson){
            Poisson p = (Poisson) obj;
            if(p.lamda == lamda)
                return true;
        }
        return false;
    }
}
