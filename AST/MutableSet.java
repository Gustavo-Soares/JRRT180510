
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class MutableSet extends Set {
    // Declared in Sets.jrag at line 656


    private static int nbrOfCreatedSets = 0;

    // Declared in Sets.jrag at line 657

	public static int getNbrOfCreatedSets() { return nbrOfCreatedSets; }

    // Declared in Sets.jrag at line 659


	private static int nbrOfAdd = 0;

    // Declared in Sets.jrag at line 660

	public static int getNbrOfAdd() { return nbrOfAdd; }

    // Declared in Sets.jrag at line 662


	protected MutableSet(int size) {
		super(size);
		nbrOfCreatedSets++;
	}

    // Declared in Sets.jrag at line 667


	public void add(Object element) {

		nbrOfAdd++;

		int index = index(element);
		int offset = index >> 5;
		int bit = 1 << (index & 0x1f);
		if (bits.length > offset && (bits[offset] & bit) == bit) {
			return;
		}
		// Copy if needed
		if (offset >= bits.length) {
			int[] newBits = new int[offset + 1];
			for (int i = 0; i < bits.length; i++) {
				newBits[i] = bits[i];
			}
			bits = newBits;
		}
		bits[offset] |= bit;
	}

    // Declared in Sets.jrag at line 688


	public void add(Set set) {

		nbrOfAdd++;

		if (set.bits.length > bits.length) {
			int[] newBits = new int[set.bits.length];
			for(int i = 0; i < bits.length; i++) {
				newBits[i] = bits[i];
 			}
			bits = newBits;         
		}
		int i = 0;
		while (i < set.bits.length && (bits[i] & set.bits[i]) == set.bits[i]) { 
			i++;
		}
		if (i != bits.length) {
			for(; i < set.bits.length; i++)
				bits[i] |= set.bits[i];			
		}
	}

    // Declared in Sets.jrag at line 709

	
    public static MutableSet empty() {
      return new MutableSet(0);
    }


}
