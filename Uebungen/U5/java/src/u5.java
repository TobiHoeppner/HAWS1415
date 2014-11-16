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
        Implementation Idea by Robert Sedgewick and Kevin Wayne (c) 2000-2011
        Algorithm by Karatsuba
         */
        int n = Math.max(x.bitLength(), y.bitLength());
        if (n <= 10) return x.multiply(y);
        n = (n/2) + (n % 2);
        BigInteger b = x.shiftRight(n);
        BigInteger a = sub(x, b.shiftLeft(n));
        BigInteger d = y.shiftRight(n);
        BigInteger c = sub(y, d.shiftLeft(n));

        // subcalls
        BigInteger ac = mul(a, c);
        BigInteger bd = mul(b, d);
        BigInteger abcd = mul(add(a,b), add(c,d));

        // final operations
        BigInteger m = sub(abcd, ac);
        BigInteger h = sub(m, bd).shiftLeft(n);
        BigInteger l = add(ac, h);
        BigInteger k = add(l,bd.shiftLeft(2*n));
        return k;
    }

    public static BigInteger add(BigInteger a, BigInteger b){
        while (!b.equals(BigInteger.ZERO)){
            BigInteger carry = a.and(b);
            a = a.xor(b);
            b = carry.shiftLeft(1);
        }
        return a;
    }

    public static BigInteger sub(BigInteger a, BigInteger b){
        return a.subtract(b);
    }

    public static void test(int n){
        int A = 100;
        long start,stop, elapsed;
        long[] times = new long[A];
        BigInteger a,b,c;
        for (int i=0; i < A; i++){
            a = nextBigInt(n);
            b = nextBigInt(n);
            start = System.currentTimeMillis();
            c = mul(a, b);
            stop = System.currentTimeMillis();
            times[i] = (stop - start);
            if (c.equals(a.multiply(b))){;}else{System.out.println("FAIL! "+i);}
        }
        elapsed = 0;
        for (int k=0; k < A; k++){
            elapsed += times[k];
        }
        elapsed = elapsed / A;
        System.out.println(n+" & "+elapsed+"\\\\");
        System.out.println("\\hline");
    }

    public static void main(String[] args){
        BigInteger a = nextBigInt(150);
        BigInteger b = nextBigInt(150);
        System.out.println(a+", "+a.toString().length());
        System.out.println(b+", "+b.toString().length());
        BigInteger ab = mul(a,b);
        System.out.println(ab+", "+ab.toString().length());
        BigInteger ba = a.multiply(b);
        System.out.println(ba+", "+ba.toString().length());
        if (ab.equals(ba)) System.out.println("True!");

        for (int i = 1000; i < 100001; i += 1000){test(i);}
    }

}
