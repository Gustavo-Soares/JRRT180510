
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class SmallSet<T> extends java.lang.Object implements Iterable<T> {
    // Declared in Sets.jrag at line 243

    HashSet<T> set = new HashSet<T>();

    // Declared in Sets.jrag at line 244

    public String toString() { return set.toString(); }

    // Declared in Sets.jrag at line 245

    public Iterator<T> iterator() { return set.iterator(); }

    // Declared in Sets.jrag at line 246

    public int size() { return set.size(); }

    // Declared in Sets.jrag at line 247

    public boolean subsetOf(SmallSet<? extends T> that) {
        for(T x : set)
            if(!that.contains(x))
                return false;
        return true;
    }

    // Declared in Sets.jrag at line 253

    @SuppressWarnings("unchecked")
    public static <T> SmallSet<T> empty() {
      return new SmallSet<T>() {
        public boolean equals(Object o) { return o instanceof SmallSet && ((SmallSet)o).isEmpty(); }
        public SmallSet union(SmallSet set) { return set; }
        public SmallSet union(Object element) { return new SmallSet().union(element); }
        public SmallSet compl(SmallSet set) { return this; }
        public SmallSet compl(Object element) { return this; }
        public SmallSet intersect(SmallSet set) { return this; }
        public boolean isEmpty() { return true; }
        public boolean isSingleton() { return false; }
        public void add(T t) { throw new UnsupportedOperationException("emptySet.add(T)"); }
        public void add(SmallSet<T> x) { throw new UnsupportedOperationException("emptySet.add(SmallSet<T>)"); }
        public int size() { return 0; }
        public boolean subsetOf(SmallSet<? extends T> that) { return true; }
      };
    }

    // Declared in Sets.jrag at line 270

    @SuppressWarnings("unchecked")
    public static <T> SmallSet<T> full() { return fullSet; }

    // Declared in Sets.jrag at line 272

    @SuppressWarnings("unchecked")
    private static SmallSet fullSet = new SmallSet() {
      public String toString() { return "full set"; }
      public SmallSet union(SmallSet set) { return this; }
      public SmallSet union(Object element) { return this; }
      public SmallSet compl(SmallSet set) {
    	  throw new Error("compl not supported for the full set");
      }
      public SmallSet compl(Object element) {
        throw new Error("compl not supported for the full set");
      }
      public SmallSet intersect(SmallSet set) { return set; }
      public boolean isEmpty() { return false; }
      public boolean isSingleton() { return false; }
      public int size() { throw new UnsupportedOperationException("fullSet.size()"); }
      public boolean subsetOf(SmallSet that) { throw new UnsupportedOperationException("fullSet.subsetOf"); }
    };

    // Declared in Sets.jrag at line 290


    protected SmallSet() {
    }

    // Declared in Sets.jrag at line 292

    public SmallSet<T> union(SmallSet<T> set) {
      if(set.isEmpty() || this.equals(set))
        return this;
      SmallSet<T> newSet = new SmallSet<T>();
      newSet.set.addAll(this.set);
      newSet.set.addAll(set.set);
      return newSet;
    }

    // Declared in Sets.jrag at line 301

    
    public SmallSet<T> union(T element) {
      if(contains(element))
        return this;
      SmallSet<T> newSet = new SmallSet<T>();
      newSet.set.addAll(this.set);
      newSet.set.add(element);
      return newSet;
    }

    // Declared in Sets.jrag at line 310

    
    public SmallSet<T> compl(SmallSet<T> set) {
      if(set.isEmpty())
        return this;
      SmallSet<T> newSet = new SmallSet<T>();
      newSet.set.addAll(this.set);
      newSet.set.removeAll(set.set);
      return newSet;
    }

    // Declared in Sets.jrag at line 319

    
    public SmallSet<T> compl(Object element) {
      if(!set.contains(element))
        return this;
      SmallSet<T> newSet = new SmallSet<T>();
      newSet.set.addAll(this.set);
      newSet.set.remove(element);
      return newSet;
    }

    // Declared in Sets.jrag at line 328


    public SmallSet<T> intersect(SmallSet<T> set) {
      if(this.equals(set) || set == fullSet)
        return this;
      SmallSet<T> newSet = new SmallSet<T>();
      newSet.set.addAll(this.set);
      newSet.set.retainAll(set.set);
      return newSet;
    }

    // Declared in Sets.jrag at line 337


    public boolean contains(Object o) {
      return set.contains(o);
    }

    // Declared in Sets.jrag at line 341


    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
      if (o == null) return false;
      if(this == o) return true;
      if(o instanceof SmallSet) {
        SmallSet<T> set = (SmallSet)o;
        return this.set.equals(set.set);
      }
      return super.equals(o);
    }

    // Declared in Sets.jrag at line 352


    public boolean isEmpty() {
      return set.isEmpty();
    }

    // Declared in Sets.jrag at line 355

    public boolean isSingleton() {
      return set.size() == 1;
    }

    // Declared in Sets.jrag at line 358

    public void add(SmallSet<T> set) {
      this.set.addAll(set.set);
    }

    // Declared in Sets.jrag at line 361

    public void add(T o) {
      this.set.add(o);
    }

    // Declared in Sets.jrag at line 364

    public static <T> SmallSet<T> mutable() {
      return new SmallSet<T>();
    }

    // Declared in Sets.jrag at line 367

    public static <T> SmallSet<T> singleton(T elt) {
      SmallSet<T> res = new SmallSet<T>();
      res.add(elt);
      return res;
    }


}
