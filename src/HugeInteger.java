import java.lang.*;

public class HugeInteger {
	
	// data
	private int[] content;
	
	// methods (actions)
	// required:
	//     parse,
	//     toString,
	//     add,
	//     subtract
	//     isEqualTo,
	//     isNotEqualTo,
	//     isGreaterThan,
	//     isLessThan,
	//     isGreaterThanOrEqualTo,
	//     isLessThanOrEqualTo
	//     isZero
	// optional:
	//     multiply,
	//     divide,
	//     remainder
	public static void main(String argv[]) {
		System.out.println("This is a HugeInteger class which can hold an integer with up to 40 digits.");
		// the following code demonstrates the member methods
		HugeInteger h1 = new HugeInteger();
		HugeInteger h2 = new HugeInteger();
		// demo parse()
		h1.parse("123456789009876543211234567890");
		h2.parse("123456789009876543211234567890");
		System.out.println("Two HugeInteger instances created and they parsed the same string:");
		// demo toString()
		System.out.println("h1 = " + h1);
		System.out.println("h2 = " + h2);
		// demo isEqualTo()
		System.out.printf("Are they equal to each other? %b\n", h1.isEqualTo(h2));
		// demo add()
		h1.add(h2);
		System.out.println("After add h2 to h1:");
		System.out.println("h1 = " + h1);
		System.out.println("h2 = " + h2);
		// demo isGreaterThan()
		System.out.printf("Is h1 larger than h2? %b\n", h1.isGreaterThan(h2));
		// demo subtract()
		h1.subtract(h2);
		System.out.println("After subtract h2 from h1:");
		System.out.println("h1 = " + h1);
		System.out.println("h2 = " + h2);
		// demo isLessThanOrEqualTo()
		System.out.printf("Is h1 <= h2? %b\n", h1.isLessThanOrEqualTo(h2));
		// demo subtract() again
		h1.subtract(h2);
		System.out.println("Subtract h2 from h1 again:");
		System.out.println("h1 = " + h1);
		System.out.println("h2 = " + h2);
		// demo isLessThan() and isZero()
		System.out.printf("Is h1 less than h2? %b\n", h1.isLessThan(h2));
		System.out.printf("Is h1 zero? %b\n", h1.isZero());
	}

	// constructor
	public HugeInteger() {
		content = new int[40];
		for(int i=0; i<40; i++)
			content[i] = 0;
	}

	public int getContent(int position) {
		if(position < 0 || position >= 40)
			return -1;
		return this.content[position];
	}

	public void parse(String s) {
		if(s.length() > 40)
			return;
		int start_pos = 40 - s.length();
		for(int i=0; i<s.length(); i++) {
			this.content[start_pos + i] = (s.charAt(i) - '0');
		}
	}

	public String toString() {
		String s = "";
		boolean start = false;
		for(int i=0; i<40; i++) {
			if(this.content[i] > 0) {
				s += this.content[i];
				start = true;
			}
			if(start && this.content[i] == 0)
				s += this.content[i];
		}
		if(s.length() == 0)
			return "0";
		else
			return s;
	}

	public void add(HugeInteger another) {
		int carry_out = 0;
		for(int i=39; i>=0; i--) {
			// sum up
			int sum = this.content[i] + (another.getContent(i)) + carry_out;
			// update
			this.content[i] = sum % 10;
			carry_out = sum / 10;
		}
		// TODO: what if overflow occurs?
	}

	public void subtract(HugeInteger another) {
		int borrow = 0; // how many to borrow
		for(int i=39; i>=0; i--) {
			// subtracting
			int result = this.content[i] - (another.getContent(i)) - borrow;
			// update
			if(result < 0) {
				borrow = 1;
				result += 10;
			} else {
				borrow = 0;
			}
			this.content[i] = result;
		}
		// TODO: what if negative exception?
	}

	public boolean isEqualTo(HugeInteger another) {
		boolean ret = true;
		for(int i=0; i<40; i++) {
			if(this.content[i] != another.getContent(i))
				ret = false;
		}
		return ret;
	}

	public boolean isNotEqualTo(HugeInteger another) {
		return !this.isEqualTo(another);
	}
	
	public boolean isGreaterThan(HugeInteger another) {
		boolean ret = false;
		for(int i=0; i<40; i++) {
			if(this.content[i] != another.getContent(i)) {
				ret = (this.content[i] > another.getContent(i)) ? true : false;
				break;
			}
		}
		return ret;
	}
	
	public boolean isLessThan(HugeInteger another) {
		return !(this.isEqualTo(another) || this.isGreaterThan(another));
	}
	
	public boolean isGreaterThanOrEqualTo(HugeInteger another) {
		return (this.isGreaterThan(another) || this.isEqualTo(another));
	}

	public boolean isLessThanOrEqualTo(HugeInteger another) {
		return !(this.isGreaterThan(another));
	}

	public boolean isZero() {
		boolean ret = true;
		for(int i=0; i<40; i++) {
			if(this.content[i] != 0)
				ret = false;
		}
		return ret;
	}
}
