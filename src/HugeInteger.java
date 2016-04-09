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
