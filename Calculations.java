public class Calculations {

    public static double calcCircArea(double radius){
        //Assumes no negative number is given
        Double val = Math.PI * (radius * radius);

        String a = String.format("Circle radius given: %f", radius);
        String b = String.format("Circle area calculated: %f", val);

        System.out.println(a);
        System.out.println(b);
        //System.out.println("TEST");
        return val;
    }

    public static double calcCircCircumference(double area){
        Double val = 2 * Math.PI * Math.sqrt((area/Math.PI));

        String a = String.format("Circle area given: %f", area);
        String b = String.format("Circle circumference calculated: %f", val);

        System.out.println(a);
        System.out.println(b);
        return val;


    }

    public static double calcSphereVolume(double radius){
        Double val = (4.0/3.0) * Math.PI * Math.pow(radius, 3);
        
        System.out.println(Math.pow(radius,3));
        String a = String.format("Sphere radius given: %f", radius);
        String b =String.format("Sphere volume calculated: %f", val);

        System.out.println(a);
        System.out.println(b);
        return val;
    }

    public static double calcSphereRadius(double area){

        Double tmp1 = Math.sqrt((area/Math.PI));
        Double val = (1.0/2.0) * Math.sqrt((area/Math.PI));

        System.out.print(tmp1);

        String a = String.format("Sphere area given: %f", area);
        String b = String.format("Sphere radius calculated: %f", val);

        System.out.println(a);
        System.out.println(b);
        return val;
    }
    

    public static double calcCylinderArea(double radius, double height){
        
        Double val =  2 * Math.PI * radius * height + 2 * Math.PI * (radius * radius);

        String a = String.format("Cylinder radius given: %f\nCylinder height given: %f", radius, height);
        String b = String.format("Cylinder area calculated: %f", val);

        System.out.println(a);
        System.out.println(b);
        return val;
    }

    public static double calcCylinderHeight(double volume, double radius){
        Double val = volume / (Math.PI * (radius * radius));
        System.out.println(String.format("Cylinder radius given: %f\nCylinder volume given: %f", radius, volume));
        System.out.println(String.format("Cylinder height calculated calculated: %f", val));
        return val;
    }
    
}
