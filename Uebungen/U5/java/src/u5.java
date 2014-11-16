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

    public static BigInteger mul (BigInteger x, BigInteger y){
        /*
        Idea by Robert Sedgewick and Kevin Wayne (c) 2000-2011
        Algorithm by Karatsuba
         */
        int n = Math.max(x.bitLength(), y.bitLength());
        if (n <= 10) return x.multiply(y);
        n = (n/2) + (n % 2);
        BigInteger b = x.shiftRight(n);
        BigInteger a = sub(x, b.shiftLeft(n));
        BigInteger d = y.shiftRight(n);
        BigInteger c = sub(y, d.shiftLeft(n));

        BigInteger ac = mul(a, c);
        BigInteger bd = mul(b, d);
        BigInteger abcd = mul(add(a,b), add(c,d));

        BigInteger m = sub(abcd, ac);
        BigInteger h = sub(m, bd).shiftLeft(n);
        BigInteger l = add(ac, h);
        BigInteger k = add(l,bd.shiftLeft(2*n));
        return k;
    }

    public static BigInteger add(BigInteger a, BigInteger b){
        return a.add(b);
    }

    public static BigInteger sub(BigInteger a, BigInteger b){
        return a.subtract(b);
    }

    public static void main(String[] args){
        BigInteger a = nextBigInt(90);
        BigInteger b = nextBigInt(90);
        System.out.println(a+", "+a.toString().length());
        System.out.println(b+", "+b.toString().length());
        System.out.println(mul(a, b));
        System.out.println(a.multiply(b));
    }

}
