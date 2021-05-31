public class Calculations {

    public static double calcCircArea(double radius){
        //Assumes no negative number is given
        return Math.PI * (radius * radius);
    }

    public static double calcCircCircumference(double area){
        return 2 * Math.PI * Math.sqrt(area/Math.PI);
    }

    public static double calcSphereVolume(double radius){
        return (4/3) * Math.PI * (radius * radius * radius);
    }

    public static double calcSphereRadius(double area){
        return (1/2) * Math.sqrt(area/Math.PI);
    }

    public static double calcCylinderArea(double radius, double height){
        //TODO: Check this for order of operations
        return 2 * Math.PI * radius * height + 2 * Math.PI * (radius * radius);
    }

    public static double calcCylinderHeight(double volume, double radius){
        return volume / (Math.PI * (radius * radius));
    }
    
}
