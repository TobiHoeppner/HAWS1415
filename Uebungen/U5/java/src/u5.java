/**
 * Created by Tobias Hoeppner on 12.11.2014.
 */
import java.math.BigInteger;
import java.util.Random;

public class u5 {

    public static Random r = new Random();
    public static int high = 999999999;
    public static int low = 100000000;


    private static BigInteger nextBigInt (int n){
        String b = "";
        int a = 0;
        // generates a String with n digits
        if (n <= 9) {
            // generates 9 random digits
            a = r.nextInt(high - low) + low;
            b = Integer.toString(a);
            b = b.substring(0,n);
        } else {
            while (b.length() < n){
                a = r.nextInt(high - low) + low;
                if ((n - b.length()) <= 9){
                    b = b + Integer.toString(a).substring(0,(n - b.length()));
                } else{
                    b = b + Integer.toString(a);
                }
            }
        }
        return new BigInteger(b);
    }

    public static double tsubamul(String x, String y){
        if ((x.length() < 10) || (y.length() < 10)){
            Double r = Double.parseDouble(x) * Double.parseDouble(y);
            return r;
        }
        int m = Math.max(x.length(), y.length());
        int m2 = m/2;
        String h1 = x.substring(0,m2);
        String l1 = x.substring(m2,x.length());
        String h2 = y.substring(0,m2);
        String l2 = y.substring(m2,y.length());
        System.out.println("x: "+x+"\ty: "+y);
        System.out.println("l1:\t"+l1+", "+l1.length()+";\th1:\t"+h1+", "+h1.length()+";\tl2:\t"+l2+", "+l2.length()+";\th2:\t"+h2+", "+h2.length());
        Double z0 = tsubamul(l1, l2);
        // bignumber addition?!
        int l1int = Integer.decode(l1);
        int l2int = Integer.decode(l2);
        int h1int = Integer.decode(h1);
        int h2int = Integer.decode(h2);
        int l1h1 = l1int + h1int;
        System.out.println("l1+h1= "+l1h1 );
        int l2h2 = l2int + h2int;
        System.out.println("l2+h2= "+l2h2 );
        Double z1 = tsubamul(Integer.toString(l1h1), Integer.toString(l1h1));
        Double z2 = tsubamul(h1, h2);
/*        procedure karatsuba(num1, num2)
        if (num1 < 10) or (num2 < 10)
        return num1*num2
  *//* calculates the size of the numbers *//*
        m = max(size_base10(num1), size_base10(num2))
        m2 = m/2
  *//* split the digit sequences about the middle *//*
        high1, low1 = split_at(num1, m2)
        high2, low2 = split_at(num2, m2)
  *//* 3 calls made to numbers approximately half the size *//*
        z0 = karatsuba(low1,low2)
        z1 = karatsuba((low1+high1),(low2+high2))
        z2 = karatsuba(high1,high2)
        return (z2*10^(2*m2))+((z1-z2-z0)*10^(m2))+(z0)*/
        System.out.println(Math.pow(10,m2));
        Double k = Math.pow(10,m2);
        return (z2*k)+((z1-z2-z0)*k)+(z0);
    }

    public static void main(String[] args){
        for(int i=1; i<100; i++){
            System.out.println(nextBigInt(i));
        }
        int k = Integer.MAX_VALUE;
        System.out.println(k);
        System.out.println((double)k);
        double o = Math.sqrt((double)k);
        System.out.println(o);
        System.out.println((int)o);
        int z = (int) o;
        System.out.println(Integer.bitCount(z)+", "+Integer.toString(z).length());
        System.out.println((int)(o*o));
        System.out.println(Integer.MAX_VALUE);
    }

}
