import java.lang.*;

public class Rational {
	
	// private data
	private int numerator;
	private int denominator;
	
	// public actions (methods)

	public static void main(String[] argv) {
		Rational r1 = new Rational(2, 14); // will reduce upon object creation
		Rational r2 = new Rational(3, 9); // will reduce upon object creation
		System.out.println("r1 = " + r1);
		System.out.println("r2 = " + r2);
		System.out.println("r1 + r2 = " + Rational.add(r1, r2) + " or " + Rational.add(r1, r2).toString(4));
		System.out.println("r1 - r2 = " + Rational.subtract(r1, r2) + " or " + Rational.subtract(r1, r2).toString(4));
		System.out.println("r1 * r2 = " + Rational.multiply(r1, r2) + " or " + Rational.multiply(r1, r2).toString(4));
		System.out.println("r1 / r2 = " + Rational.divide(r1, r2) + " or " + Rational.divide(r1, r2).toString(4));
	}

	public static Rational add(Rational r1, Rational r2) {
		// check
		if(r1.getDenominator() * r2.getDenominator() == 0)
			return (new Rational());
		// expansion
		int de = Rational.getLCM(r1.getDenominator(), r2.getDenominator());
		// get ratio
		int ratio1 = de / r1.getDenominator();
		int ratio2 = de / r2.getDenominator();
		// add
		int nu = r1.getNumerator() * ratio1 + r2.getNumerator() * ratio2;
		return (new Rational(nu, de)); // will reduce on object creation
	}

	public static Rational subtract(Rational r1, Rational r2) {
		// check
		if(r1.getDenominator() * r2.getDenominator() == 0)
			return (new Rational());
		// expansion
		int de = Rational.getLCM(r1.getDenominator(), r2.getDenominator());
		// get ratio
		int ratio1 = de / r1.getDenominator();
		int ratio2 = de / r2.getDenominator();
		// subtract
		int nu = r1.getNumerator() * ratio1 - r2.getNumerator() * ratio2;
		return (new Rational(nu, de)); // will reduce on object creation
	}

	public static Rational multiply(Rational r1, Rational r2) {
		// check
		if(r1.getDenominator() * r2.getDenominator() == 0)
			return (new Rational());
		int de = r1.getDenominator() * r2.getDenominator();
		int nu = r1.getNumerator() * r2.getNumerator();
		return (new Rational(nu, de)); // will reduce on object creation
	}

	public static Rational divide(Rational r1, Rational r2) {
		// check
		if(r1.getDenominator() * r2.getDenominator() * r2.getNumerator() == 0)
			return (new Rational());
		int de = r1.getDenominator() * r2.getNumerator();
		int nu = r1.getNumerator() * r2.getDenominator();
		return (new Rational(nu, de)); // will reduce on object creation
	}
	
	public static int getGCD(int a, int b) {
		int tmp;
		// make sure a > b
		if(b > a) {
			tmp = b;
			b = a;
			a = tmp;
		}
		while(a != 0 && b != 0) {
			tmp = b;
			b = a%b;
			a = tmp;
		}
		return (a + b);
	}

	public static int getLCM(int a, int b) {
		int gcd = Rational.getGCD(a, b);
		if(gcd == 0)
			return 0;
		return (a * b / gcd);
	}

	// constructor
	public Rational() {
		this.numerator = 0;
		this.denominator = 0;
	}

	// constructor 2
	public Rational(int nu, int de) {
		this.numerator = nu;
		this.denominator = de;
		this.reduce();
	}

	public int getNumerator() {
		return this.numerator;
	}

	public int getDenominator() {
		return this.denominator;
	}

	private void reduce() {
		int gcd = Rational.getGCD(this.numerator, this.denominator);
		if(gcd != 0) {
			this.numerator /= gcd;
			this.denominator /= gcd;
		}
	}


	@Override
	public String toString() {
		String s = "";
		s += (this.numerator + "/" + this.denominator);
		return s;
	}

	public String toString(int precision) {
		double res = (double)this.numerator / (double)this.denominator;
		String my_format = String.format("%%.%df", precision);
		String res_str = String.format(my_format, res);
		return res_str;
	}
}