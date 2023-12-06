public class Operations  {
    private double x;
    private double y;
    private double result;
    private String operations;

    public Operations(double  x,double y ,String operations){
        this.x=x;
        this.y= y;
        this.operations = operations;


    }

    public String toString() {
        return String.format("%.2f + %.2f = %.2f", x,y,result);
    }

    }

